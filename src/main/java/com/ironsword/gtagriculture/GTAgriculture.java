package com.ironsword.gtagriculture;

import com.ironsword.gtagriculture.common.data.GTABlocks;
import com.ironsword.gtagriculture.common.data.GTAItems;
import com.ironsword.gtagriculture.common.registry.*;
import com.ironsword.gtagriculture.data.GTADategen;
import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(GTAgriculture.MODID)
public class GTAgriculture
{
    public static final String MODID = "gtagriculture";
    public static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("unused")
    public GTAgriculture()
    {
        GTAgriculture.init();
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
    }

    public static void init() {
        GTACreativeModeTabs.init();
        GTAItems.init();
        GTABlocks.init();
        GTADategen.init();
        GTARegistries.REGISTRATE.registerRegistrate();
    }
}
