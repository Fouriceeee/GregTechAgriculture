package com.ironsword.gtagriculture.api.capability.forge;

import com.ironsword.gtagriculture.api.capability.NutrientTracker;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class GTACapability {

    public static final Capability<NutrientTracker> CAPABILITY_NUTRIENT_TRACKER = CapabilityManager.get(new CapabilityToken<>() {});

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(NutrientTracker.class);
    }
}
