package com.ironsword.gtagriculture;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.ironsword.gtagriculture.common.registry.GTARegistries;

public class GTAgricultureAddon implements IGTAddon {
    @Override
    public GTRegistrate getRegistrate() {
        return GTARegistries.REGISTRATE;
    }

    @Override
    public void initializeAddon() {

    }

    @Override
    public String addonModId() {
        return GTAgriculture.MODID;
    }
}
