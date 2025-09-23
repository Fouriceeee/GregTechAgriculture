package com.ironsword.gtagriculture;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.ironsword.gtagriculture.common.registry.GTARegistries;

@GTAddon
public class GTAgricultureAddon implements IGTAddon {
    @Override
    public GTRegistrate getRegistrate() {
        return GTARegistries.REGISTRATE;
    }

    @Override
    public void initializeAddon() {
        GTAgriculture.LOGGER.info("GTAgricultureAddon has loaded!");
    }

    @Override
    public String addonModId() {
        return GTAgriculture.MODID;
    }
}
