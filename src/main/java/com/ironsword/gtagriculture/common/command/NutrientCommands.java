package com.ironsword.gtagriculture.common.command;

import com.ironsword.gtagriculture.api.capability.NutrientTracker;
import com.ironsword.gtagriculture.api.data.nutrient.Nutrient;
import com.ironsword.gtagriculture.common.command.arguments.NutrientArgument;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

import static net.minecraft.commands.Commands.*;
import static com.ironsword.gtagriculture.api.capability.forge.GTACapability.getNutrientTracker;

public class NutrientCommands {
    private static final SimpleCommandExceptionType ERROR_GIVE_FAILED = new SimpleCommandExceptionType(
            Component.literal(" ERROR_GIVE_FAILED"));
    private static final SimpleCommandExceptionType ERROR_CLEAR_EVERYTHING_FAILED = new SimpleCommandExceptionType(
            Component.literal("ERROR_CLEAR_EVERYTHING_FAILED"));

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {
        dispatcher.register(
                literal("nutrient")
                        .then(literal("query")
                                .executes(ctx->{
                                    return queryNutrients(ctx.getSource().getPlayerOrException());
                                })
                                .then(argument("target",EntityArgument.player())
                                        .requires(source -> source.hasPermission(LEVEL_GAMEMASTERS))
                                        .executes(ctx->{
                                            return queryNutrients(EntityArgument.getPlayer(ctx,"target"));
                                        })))
                        .then(literal("clear")
                                .requires(ctx-> ctx.hasPermission(LEVEL_ADMINS))
                                .executes(ctx->{
                                    return clearNutrients(
                                            Collections.singleton(ctx.getSource().getPlayerOrException()),null);
                                })
                                .then(argument("targets",EntityArgument.players())
                                        .executes(ctx->{
                                            return clearNutrients(EntityArgument.getPlayers(ctx,"targets"),null);
                                        })
                                        .then(argument("nutrient", NutrientArgument.nutrient())
                                                .executes(ctx->{
                                                    Collection<ServerPlayer> targets = EntityArgument.getPlayers(ctx,"targets");
                                                    Nutrient nutrient = NutrientArgument.getNutrient(ctx,"nutrient");
                                                    return clearNutrients(targets,nutrient);
                                                }))))
                        .then(literal("apply")
                                .requires(ctx->ctx.hasPermission(LEVEL_GAMEMASTERS))
                                .then(argument("targets",EntityArgument.players())
                                        .then(argument("nutrient",NutrientArgument.nutrient())
                                                .executes(ctx->{
                                                    Nutrient nutrient = NutrientArgument.getNutrient(ctx,"nutrient");
                                                    Collection<ServerPlayer> players = EntityArgument.getPlayers(ctx,"targets");
                                                    return applyNutrients(players,nutrient,1);
                                                })
                                                .then(argument("amount", IntegerArgumentType.integer(0))
                                                        .executes(ctx->{
                                                            Nutrient nutrient = NutrientArgument.getNutrient(ctx,"nutrient");
                                                            Collection<ServerPlayer> players = EntityArgument.getPlayers(ctx,"targets");
                                                            int amount = IntegerArgumentType.getInteger(ctx,"amount");
                                                            return applyNutrients(players,nutrient,amount);
                                                        })))))
        );
    }

    private static int queryNutrients(ServerPlayer player) throws CommandSyntaxException {
        NutrientTracker tracker = getNutrientTracker(player);
        if (tracker == null) {
            throw EntityArgument.NO_PLAYERS_FOUND.create();
        }
        int count = tracker.getNutrients().size();
        if (count == 0) {
            player.sendSystemMessage(
                    Component.literal("Player %s has no nutrients.".formatted(player.getName()))
            );
        }else {
            player.sendSystemMessage(
                    Component.literal("Player %s has these nutrients:".formatted(player.getName()))
            );
        }

        for (var entry:tracker.getNutrients().object2IntEntrySet()) {
            player.sendSystemMessage(
                    Component.literal("%s : %d".formatted(entry.getKey().getName(),entry.getIntValue()))
            );
        }

        return count;
    }

    private static int clearNutrients(Collection<ServerPlayer> targets, @Nullable Nutrient nutrient) throws CommandSyntaxException {
        int count = 0;
        for (ServerPlayer player:targets) {
            NutrientTracker tracker = getNutrientTracker(player);
            if (tracker == null){
                continue;
            }
            if (nutrient == null){
                count+=tracker.getNutrients().keySet().size();
                for (Nutrient nutrient1: tracker.getNutrients().keySet()){
                    tracker.remove(nutrient1);
                }
            }else {
                count++;
                tracker.remove(nutrient);
            }
        }
        if (count==0){
            throw ERROR_CLEAR_EVERYTHING_FAILED.create();
        }
        return count;
    }

    private static int applyNutrients(Collection<ServerPlayer> targets, Nutrient nutrient,int amount) throws CommandSyntaxException {
        int success = 0;
        for (ServerPlayer player:targets) {
            NutrientTracker tracker = getNutrientTracker(player);
            if (tracker == null) {
                continue;
            }
            tracker.gain(nutrient,amount);
            success++;
        }
        if (success == 0){
            throw ERROR_GIVE_FAILED.create();
        }
        return success;
    }
}
