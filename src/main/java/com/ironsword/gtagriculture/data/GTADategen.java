package com.ironsword.gtagriculture.data;

import com.ironsword.gtagriculture.common.registry.GTARegistries;
import com.ironsword.gtagriculture.data.lang.LangHandler;
import com.ironsword.gtagriculture.data.loot.LootLoader;
import com.tterrag.registrate.providers.ProviderType;

public class GTADategen {
    public static void init() {
        GTARegistries.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
        GTARegistries.REGISTRATE.addDataGenerator(ProviderType.LOOT, LootLoader::init);
    }
}
