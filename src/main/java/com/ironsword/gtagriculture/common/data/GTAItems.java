package com.ironsword.gtagriculture.common.data;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;
import com.ironsword.gtagriculture.GTAgriculture;
import com.ironsword.gtagriculture.Utils;
import com.ironsword.gtagriculture.common.item.TooltipItem;
import com.ironsword.gtagriculture.common.registry.GTACreativeModeTabs;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import static com.ironsword.gtagriculture.common.registry.GTARegistries.REGISTRATE;

public class GTAItems {
    static {
        REGISTRATE.creativeModeTab(()-> GTACreativeModeTabs.GTA_TAB);
    }

    public static final ItemEntry<TooltipItem>
            CHILLY_PEPPER = foodItemWithTooltip("chilly_pepper",1,0.3f,"§7It is red and hot"),
            LEMON = foodItemWithTooltip("lemon",1,0.3f,"§7Don't make Lemonade"),
            TOMATO = foodItemWithTooltip("tomato",1,0.2f,"§7Solid Ketchup"),
            MAX_TOMATO = foodItemWithTooltip("max_tomato",9,1.0f,"§7Full Health in one Tomato"),
            GRAPE = foodItemWithTooltip("grape",2,0.3f,"§7Source of Wine"),
            ONION = foodItemWithTooltip("onion",2,0.2f,"§7Taking over the whole Taste"),
            CUCUMBER = foodItemWithTooltip("cucumber",1,0.2f,"§7Not a Sea Cucumber!"),
            RAPE = tooltipItem("rape","§7Time to oil up!");

    public static final ItemEntry<Item> TEST_BERRY = REGISTRATE.item("test_berry",Item::new)
            .defaultModel()
            .lang("Test Berry")
            .register();

    public static void init() {
    }

    private static ItemEntry<TooltipItem> foodItemWithTooltip(String id, int nutrition, float saturation, String toolTip){
        return REGISTRATE.item(id, TooltipItem::new)
                .properties(p -> p.food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build()))
                .lang(Utils.id2Name(id))
                .addMiscData(ProviderType.LANG,( prov)->prov.add(getTooltipKey(id),toolTip))
                .defaultModel()
                .register();
    }

    public static ItemEntry<TooltipItem> tooltipItem(String id, String toolTip) {
        return REGISTRATE.item(id, TooltipItem::new)
                .lang(Utils.id2Name(id))
                .addMiscData(ProviderType.LANG,( prov)->prov.add(getTooltipKey(id),toolTip))
                .defaultModel()
                .register();
    }

    public static ItemEntry<ComponentItem> simpleTooltipItem(String id, String toolTip){
        return REGISTRATE.item(id,ComponentItem::create)
                .lang(Utils.id2Name(id))
                .addMiscData(ProviderType.LANG,( prov)->prov.add(getTooltipKey(id),toolTip))
                .onRegister(attach(new TooltipBehavior(tooltips -> tooltips.add(Component.translatable(getTooltipKey(id))))))
                .defaultModel()
                .register();
    }

    private static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }

    private static String getTooltipKey(String id) {
        return "item." + GTAgriculture.MODID + "." + id + ".tooltip";
    }


}