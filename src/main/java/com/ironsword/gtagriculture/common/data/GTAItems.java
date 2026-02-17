package com.ironsword.gtagriculture.common.data;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.FoodStats;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;
import com.ironsword.gtagriculture.GTAgriculture;
import com.ironsword.gtagriculture.Utils;
import com.ironsword.gtagriculture.api.data.nutrient.Nutrient;
import com.ironsword.gtagriculture.api.item.component.FoodNutrients;
import com.ironsword.gtagriculture.common.item.GTAFoodItem;
import com.ironsword.gtagriculture.api.item.component.GTAFoodStats;
import com.ironsword.gtagriculture.common.item.TooltipItem;
import com.ironsword.gtagriculture.common.registry.GTACreativeModeTabs;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import static com.ironsword.gtagriculture.common.registry.GTARegistries.REGISTRATE;
import static com.ironsword.gtagriculture.common.data.GTANutrients.*;

public class GTAItems {
    static {
        REGISTRATE.creativeModeTab(()-> GTACreativeModeTabs.GTA_TAB);
    }

//    public static final ItemEntry<TooltipItem>
//            CHILLY_PEPPER = foodItemWithTooltip("chilly_pepper",1,0.3f,"§7It is red and hot"),
//            LEMON = foodItemWithTooltip("lemon",1,0.3f,"§7Don't make Lemonade"),
//            TOMATO = foodItemWithTooltip("tomato",1,0.2f,"§7Solid Ketchup"),
//            MAX_TOMATO = foodItemWithTooltip("max_tomato",9,1.0f,"§7Full Health in one Tomato"),
//            GRAPE = foodItemWithTooltip("grape",2,0.3f,"§7Source of Wine"),
//            ONION = foodItemWithTooltip("onion",2,0.2f,"§7Taking over the whole Taste"),
//            CUCUMBER = foodItemWithTooltip("cucumber",1,0.2f,"§7Not a Sea Cucumber!"),
//            RAPE = tooltipItem("rape","§7Time to oil up!");

//    public static final ItemEntry<Item> TEST_BERRY = REGISTRATE.item("test_berry",Item::new)
//            .defaultModel()
//            .lang("Test Berry")
//            .register();
    private static FoodStats food(int nutrition,float saturation){
        return new FoodStats(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build());
    }

    private static FoodNutrients singleNutrient(Nutrient nutrient,int amount){
        return new FoodNutrients().nutrient(nutrient,amount);
    }

    private static ItemBuilder<ComponentItem, GTRegistrate> componentItem(String id){
        return REGISTRATE.item(id,ComponentItem::create)
                .lang(Utils.id2Name(id))
                .defaultModel();
    }

    private static ItemEntry<ComponentItem> foodItem(String id,int nutrition,float saturation,Nutrient nutrient,int amount){
        return REGISTRATE.item(id,ComponentItem::create)
                .lang(Utils.id2Name(id))
                .defaultModel()
                .onRegister(attach(food(nutrition,saturation)))
                .onRegister(attach(new FoodNutrients().nutrient(nutrient,amount)))
                .register();
    }

    public static final ItemEntry<ComponentItem>
            LEMON           = foodItem("lemon"          ,1,0.6f ,SUGAR,4),
            LEMON_SLICE     = foodItem("lemon_slice"    ,0,0.15f,SUGAR,1),
            TOMATO          = foodItem("tomato"         ,1,0.6f ,SUGAR,4),
            TOMATO_SLICE    = foodItem("tomato_slice"   ,0,0.15f,SUGAR,1),
            MAXIM_TOMATO    = foodItem("maxim_tomato"   ,9,1.0f ,SUGAR,10),
            ONION           = foodItem("onion"          ,1,1.2f ,SUGAR,4),
            ONION_SLICE     = foodItem("onion_slice"    ,0,0.3f ,SUGAR,1);


    public static final ItemEntry<ComponentItem>
            CUCUMBER = simpleComponentItem("cucumber"),
            CUCUMBER_SLICE = simpleComponentItem("cucumber_slice"),
            PICKLE = simpleComponentItem("pickle"),
            PICKLE_SLICE = simpleComponentItem("pickle_slice"),
            CHILI_PEPPER = simpleComponentItem("chili_pepper"),
            GREEN_GRAPES = simpleComponentItem("green_grapes"),
            GREEN_RAISINS = simpleComponentItem("green_raisins"),
            WHITE_GRAPES = simpleComponentItem("white_grapes"),
            WHITE_RAISINS = simpleComponentItem("white_raisins"),
            RED_GRAPES = simpleComponentItem("red_grapes"),
            RED_RAISINS = simpleComponentItem("red_raisins"),
            PURPLE_GRAPES = simpleComponentItem("purple_grapes"),
            PURPLE_RAISINS = simpleComponentItem("purple_raisins"),
            CHOCOLATE_RAISINS = simpleComponentItem("chocolate_raisins"),
    //Items.CARROT
            CARROT_SLICE = simpleComponentItem("carrot_slice"),
    //Items.POISONOUS_POTATO
            BANANA = simpleComponentItem("banana"),
            BANANA_SLICE = simpleComponentItem("banana_slice"),
            POMEGRANATE = simpleComponentItem("pomegranate"),
            POMERAISINS = simpleComponentItem("pomeraisins"),
            BLUEBERRY = simpleComponentItem("blueberry"),
            GOOSEBERRY = simpleComponentItem("gooseberry"),
            CANDLEBERRY = simpleComponentItem("candleberry"),
            CRANBERRY = simpleComponentItem("cranberry"),
            BLACK_CURRANTS = simpleComponentItem("black_currants"),
            WHITE_CURRANTS = simpleComponentItem("white_currants"),
            RED_CURRANTS = simpleComponentItem("red_currants"),
            BLACKBERRY = simpleComponentItem("blackberry"),
            RASPBERRY = simpleComponentItem("raspberry"),
            STRAWBERRY = simpleComponentItem("strawberry"),

            GREEN_APPLE = simpleComponentItem("green_apple"),
            GREEN_APPLE_SLICE = simpleComponentItem("green_apple_slice"),
            GREEN_APPLE_CORE = simpleComponentItem("green_apple_core"),
            YELLOW_APPLE = simpleComponentItem("yellow_apple"),
            YELLOW_APPLE_SLICE = simpleComponentItem("yellow_apple_slice"),
            YELLOW_APPLE_CORE = simpleComponentItem("yellow_apple_core"),
    //Items.APPLE
            RED_APPLE_SLICE = simpleComponentItem("red_apple_slice"),
            RED_APPLE_CORE = simpleComponentItem("red_apple_core"),
            DARKRED_APPLE = simpleComponentItem("darkred_apple"),
            DARKRED_APPLE_SLICE = simpleComponentItem("darkred_apple_slice"),
            DARKRED_APPLE_CORE = simpleComponentItem("darkred_apple_core"),

            PEANUT = simpleComponentItem("peanut"),
            HAZELNUT = simpleComponentItem("hazelnut"),
            ANANAS = simpleComponentItem("ananas"),
            ANANAS_SLICE = simpleComponentItem("ananas_slice"),
            CINNAMON_BARK = simpleComponentItem("cinnamon_bark"),
            COCONUT = simpleComponentItem("coconut");



    //Nutrient Test Item
    public static final ItemEntry<ComponentItem> ALCOHOL_ITEM = REGISTRATE.item("alcohol_test_item",ComponentItem::create)
            .defaultModel()
            .lang("Alcohol Test Item")
            .onRegister(attach(new FoodStats(new FoodProperties.Builder().nutrition(1).saturationMod(1.0f).build())))
            .onRegister(attach(new FoodNutrients().nutrient(GTANutrients.ALCOHOL,1)))
            .register();

    public static final ItemEntry<ComponentItem> CAFFEINE_ITEM = REGISTRATE.item("caffeine_test_item",ComponentItem::create)
            .defaultModel()
            .lang("Caffeine Test Item")
            .onRegister(attach(new FoodStats(new FoodProperties.Builder().nutrition(1).saturationMod(1.0f).build())))
            .onRegister(attach(new FoodNutrients().nutrient(GTANutrients.CAFFEINE,1)))
            .register();

    public static final ItemEntry<ComponentItem> DEHYDRATION_ITEM = REGISTRATE.item("dehydration_test_item",ComponentItem::create)
            .defaultModel()
            .lang("Dehydration Test Item")
            .onRegister(attach(new FoodStats(new FoodProperties.Builder().nutrition(1).saturationMod(1.0f).build())))
            .onRegister(attach(new FoodNutrients().nutrient(GTANutrients.DEHYDRATION,1)))
            .register();

    public static final ItemEntry<ComponentItem> SUGAR_ITEM = REGISTRATE.item("sugar_test_item",ComponentItem::create)
            .defaultModel()
            .lang("Sugar Test Item")
            .onRegister(attach(new FoodStats(new FoodProperties.Builder().nutrition(1).saturationMod(1.0f).build())))
            .onRegister(attach(new FoodNutrients().nutrient(GTANutrients.SUGAR,1)))
            .register();

    public static final ItemEntry<ComponentItem> FAT_ITEM = REGISTRATE.item("fat_test_item",ComponentItem::create)
            .defaultModel()
            .lang("Fat Test Item")
            .onRegister(attach(new FoodStats(new FoodProperties.Builder().nutrition(1).saturationMod(1.0f).build())))
            .onRegister(attach(new FoodNutrients().nutrient(GTANutrients.FAT,1)))
            .register();

    public static final ItemEntry<ComponentItem> RADIATION_ITEM = REGISTRATE.item("radiation_test_item",ComponentItem::create)
            .defaultModel()
            .lang("Radiation Test Item")
            .onRegister(attach(new FoodStats(new FoodProperties.Builder().nutrition(1).saturationMod(1.0f).build())))
            .onRegister(attach(new FoodNutrients().nutrient(GTANutrients.RADIATION,1)))
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

    public static ItemEntry<ComponentItem> simpleComponentItem(String id) {
        return REGISTRATE.item(id,ComponentItem::create)
                .lang(Utils.id2Name(id))
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