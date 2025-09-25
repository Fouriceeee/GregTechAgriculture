package com.ironsword.gtagriculture.api;

import com.ironsword.gtagriculture.Utils;
import com.tterrag.registrate.util.entry.ItemEntry;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import java.util.List;

public class GTAItemEntry{
    @Getter
    protected final String id;
    @Getter
    protected final String name;
    @Getter
    protected Item.Properties properties = new Item.Properties();
    @Getter
    protected String tooltip = "";

    @Getter
    @Setter
    protected ItemEntry<? extends Item> itemEntry;

    public GTAItemEntry(String id,String name){
        this.id = id;
        this.name = name;
    }

    public static GTAItemEntry create(String id,String name) {
        return new GTAItemEntry(id,name);
    }

    public static GTAItemEntry create(String id) {
        return new GTAItemEntry(id, Utils.id2Name(id));
    }

    public GTAItemEntry properties(Item.Properties properties) {
        this.properties = properties;
        return this;
    }

    public GTAItemEntry foodProperties(int nutrition,float saturation) {
        this.properties.food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build());
        return this;
    }

    public GTAItemEntry tooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public GTAItemEntry toList(List<GTAItemEntry> list){
        list.add(this);
        return this;
    }
}
