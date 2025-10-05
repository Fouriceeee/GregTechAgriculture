package com.ironsword.gtagriculture.common.item;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.ironsword.gtagriculture.api.item.component.GTAFoodStats;
import net.minecraft.world.item.Item;

public class GTAFoodItem extends ComponentItem {
    protected GTAFoodItem(Properties properties) {
        super(properties);
    }

    public static GTAFoodItem create(Item.Properties properties) {
        return new GTAFoodItem(properties);
    }

    public GTAFoodStats getFoodStats() {
        for (IItemComponent component:components){
            if (component instanceof GTAFoodStats foodStats)
                return foodStats;
        }
        return null;
    }
}
