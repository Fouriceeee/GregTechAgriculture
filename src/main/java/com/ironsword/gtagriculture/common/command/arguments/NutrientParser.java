package com.ironsword.gtagriculture.common.command.arguments;

import com.ironsword.gtagriculture.api.data.nutrient.Nutrient;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class NutrientParser {
    private static final DynamicCommandExceptionType ERROR_UNKNOWN_ITEM = new DynamicCommandExceptionType(
            id-> Component.literal("Unknown item %s".formatted(id))
    );
    private static final Function<SuggestionsBuilder, CompletableFuture<Suggestions>> SUGGEST_NOTHING = SuggestionsBuilder::buildFuture;
    private final StringReader reader;
    private Nutrient result;

    private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggestions = SUGGEST_NOTHING;

    private NutrientParser(StringReader reader) {this.reader = reader;}

    public static Nutrient parseForNutrient(StringReader reader) throws CommandSyntaxException {
        int i = reader.getCursor();

        try {
            NutrientParser nutrientParser = new NutrientParser(reader);
            nutrientParser.parse();
            return nutrientParser.result;
        } catch (CommandSyntaxException var5) {
            reader.setCursor(i);
            throw var5;
        }
    }

    public static CompletableFuture<Suggestions> fillSuggestions(SuggestionsBuilder builder) {
        StringReader stringReader = new StringReader(builder.getInput());
        stringReader.setCursor(builder.getStart());
        NutrientParser nutrientParser = new NutrientParser(stringReader);

        try {
            nutrientParser.parse();
        }catch (CommandSyntaxException ignored) {}

        return nutrientParser.suggestions.apply(builder.createOffset(stringReader.getCursor()));
    }

    private void parse() throws CommandSyntaxException {
        this.suggestions = this::suggestNutrient;
        this.readNutrient();
    }

    private void readNutrient() throws CommandSyntaxException{
        int i = this.reader.getCursor();

        while (reader.canRead() && ResourceLocation.isAllowedInResourceLocation(reader.peek())) {
            reader.skip();
        }

        String name = reader.getString().substring(i, reader.getCursor());
        Nutrient nutrient = Nutrient.NUTRIENTS.get(name);
        this.result = Optional.ofNullable(nutrient).orElseThrow(() -> {
            this.reader.setCursor(i);
            return ERROR_UNKNOWN_ITEM.createWithContext(this.reader, name);
        });
    }

    private CompletableFuture<Suggestions> suggestNutrient(SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(Nutrient.NUTRIENTS.keySet(), builder);
    }
}
