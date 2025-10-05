package com.ironsword.gtagriculture.common.command.arguments;

import com.ironsword.gtagriculture.api.data.nutrient.Nutrient;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.concurrent.CompletableFuture;

public class NutrientArgument implements ArgumentType<Nutrient> {
    public static NutrientArgument nutrient() {
        return new NutrientArgument();
    }

    @Override
    public Nutrient parse(StringReader reader) throws CommandSyntaxException {
        return NutrientParser.parseForNutrient(reader);
    }

    public static <S> Nutrient getNutrient(CommandContext<S> context, String name) {
        return context.getArgument(name, Nutrient.class);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return NutrientParser.fillSuggestions(builder);
    }
}
