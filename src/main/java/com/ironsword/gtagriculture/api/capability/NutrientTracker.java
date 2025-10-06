package com.ironsword.gtagriculture.api.capability;

import com.ironsword.gtagriculture.api.data.nutrient.Nutrient;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lombok.Getter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

@Getter
public class NutrientTracker implements INBTSerializable<CompoundTag>{
    private final Object2IntMap<Nutrient> nutrients = new Object2IntOpenHashMap<>();

    private final Player player;

    public NutrientTracker(Player player) {
        this.player = player;
    }

    public void tick(){
        if (player.isCreative()) return;

        for (Nutrient nutrient: nutrients.keySet()){
            nutrient.applyEffect(player,nutrients.getInt(nutrient));
        }
    }

    public void gain(@NotNull Nutrient nutrient, int amount) {
        nutrients.put(nutrient,nutrients.getOrDefault(nutrient,0)+amount);
    }

    public void remove(Nutrient nutrient) {
        nutrients.remove(nutrient);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        ListTag effectsTag = new ListTag();
        for (var entry:nutrients.object2IntEntrySet()){
            CompoundTag nutrientTag = new CompoundTag();
            nutrientTag.putString("nutrient",entry.getKey().getName());
            nutrientTag.putInt("amount",entry.getIntValue());
            effectsTag.add(nutrientTag);
        }
        tag.put("nutrients",effectsTag);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag arg){
        ListTag nutrientsTag = arg.getList("nutrients", ListTag.TAG_COMPOUND);
        for (int i = 0; i < nutrientsTag.size(); ++i) {
            CompoundTag compoundTag = nutrientsTag.getCompound(i);
            Nutrient nutrient = Nutrient.NUTRIENTS.get(compoundTag.getString("nutrient"));
            int amount = compoundTag.getInt("amount");

            nutrients.put(nutrient,amount);
        }
    }
}
