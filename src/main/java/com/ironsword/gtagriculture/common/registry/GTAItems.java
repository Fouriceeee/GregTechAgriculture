package com.ironsword.gtagriculture.common.registry;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;
import com.ironsword.gtagriculture.GTAgriculture;
import com.ironsword.gtagriculture.Utils;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import static com.ironsword.gtagriculture.common.registry.GTARegistries.REGISTRATE;

public class GTAItems {
    static {
        REGISTRATE.creativeModeTab(()->GTACreativeModeTabs.GTA_TAB);
    }

    public static ItemEntry<ComponentItem>
            CHILLY_PEPPER = foodItemWithTooltip("chilly_pepper",1,0.3f),
            LEMON = foodItemWithTooltip("lemon",1,0.3f),
            TOMATO = foodItemWithTooltip("tomato",1,0.2f),
            MAX_TOMATO = foodItemWithTooltip("max_tomato",9,1.0f),
            GRAPE = foodItemWithTooltip("grape",2,0.3f),
            ONION = foodItemWithTooltip("onion",2,0.2f),
            CUCUMBER = foodItemWithTooltip("cucumber",1,0.2f),
            RAPE = simpleTooltipItem("rape");

    public static void init() {
    }

    private static ItemEntry<ComponentItem> foodItemWithTooltip(String id,int nutrition, float saturation){
        return REGISTRATE.item(id,ComponentItem::create)
                .properties(p -> p.food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build()))
                .lang(Utils.id2Name(id))
                .onRegister(attach(new TooltipBehavior(tooltips -> tooltips.add(Component.translatable("item."+ GTAgriculture.MODID+"."+id+".tooltip")))))
                .defaultModel()
                .register();
    }

    public static ItemEntry<ComponentItem> simpleTooltipItem(String id){
        return REGISTRATE.item(id,ComponentItem::create)
                .lang(Utils.id2Name(id))
                .onRegister(attach(new TooltipBehavior(tooltips -> tooltips.add(Component.translatable("item."+ GTAgriculture.MODID+"."+id+".tooltip")))))
                .defaultModel()
                .register();
    }

    private static ItemEntry<Item> simpleItem(String id,String name,String tooltip) {
        return REGISTRATE.item(id,Item::new)
                .defaultModel()
                .lang(name)
                .register();
    }

    public static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }


}