package com.ironsword.gtagriculture.common.data;

import com.ironsword.gtagriculture.GTAgriculture;
import com.ironsword.gtagriculture.common.command.arguments.NutrientArgument;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GTACommandArguments {
    public static final DeferredRegister<ArgumentTypeInfo<?, ?>> COMMAND_ARGUMENT_TYPES = DeferredRegister.create(ForgeRegistries.COMMAND_ARGUMENT_TYPES, GTAgriculture.MODID);

    public static final RegistryObject<SingletonArgumentInfo<NutrientArgument>> NUTRIENT_ARGUMENT_TYPE = COMMAND_ARGUMENT_TYPES
            .register("nutrient",
                    ()-> ArgumentTypeInfos.registerByClass(NutrientArgument.class,
                            SingletonArgumentInfo.contextFree(NutrientArgument::nutrient)));

    public static void init(IEventBus modBus) {
        COMMAND_ARGUMENT_TYPES.register(modBus);
    }
}
