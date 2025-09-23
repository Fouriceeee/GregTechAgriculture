package com.ironsword.gtagriculture;

import com.ironsword.gtagriculture.common.data.GTAItemEntries;
import com.ironsword.gtagriculture.common.registry.GTACreativeModeTabs;
import com.ironsword.gtagriculture.common.registry.GTAItems;
import com.ironsword.gtagriculture.common.registry.GTARegistries;
import com.ironsword.gtagriculture.data.GTADategen;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(GTAgriculture.MODID)
public class GTAgriculture
{
    public static final String MODID = "gtagriculture";
    public static final Logger LOGGER = LogUtils.getLogger();

    public GTAgriculture()
    {
        GTAgriculture.init();
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
    }

    public static void init() {
        GTACreativeModeTabs.init();
        GTAItems.init();
        GTADategen.init();
        GTARegistries.REGISTRATE.registerRegistrate();
    }
}
