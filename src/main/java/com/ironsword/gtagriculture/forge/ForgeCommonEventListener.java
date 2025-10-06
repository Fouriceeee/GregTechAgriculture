package com.ironsword.gtagriculture.forge;


import com.gregtechceu.gtceu.api.capability.forge.GTCapability;
import com.ironsword.gtagriculture.GTAgriculture;
import com.ironsword.gtagriculture.api.capability.NutrientTracker;
import com.ironsword.gtagriculture.api.capability.forge.GTACapability;
import com.ironsword.gtagriculture.common.command.NutrientCommands;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = GTAgriculture.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeCommonEventListener {

    @SubscribeEvent
    public static void registerPlayerCapabilities(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof Player player) {
            final NutrientTracker tracker = new NutrientTracker(player);
            event.addCapability(new ResourceLocation(GTAgriculture.MODID,"nutrient_tracker"), new ICapabilitySerializable<CompoundTag>() {
                @Override
                public CompoundTag serializeNBT() {
                    return tracker.serializeNBT();
                }

                @Override
                public void deserializeNBT(CompoundTag arg) {
                    tracker.deserializeNBT(arg);
                }

                @Override
                public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability,
                                                                  @Nullable Direction arg) {
                    return GTACapability.CAPABILITY_NUTRIENT_TRACKER.orEmpty(capability,LazyOptional.of(()-> tracker));
                }
            });
        }
    }

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event){
        NutrientCommands.register(event.getDispatcher(),event.getBuildContext());
    }

    @SubscribeEvent
    public static void tickPlayerNutrientEffect(TickEvent.PlayerTickEvent event){
        if (event.side == LogicalSide.CLIENT || event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        if (player.level().getGameTime() % 50 == 0){
            NutrientTracker tracker = GTACapability.getNutrientTracker(player);
            if (tracker == null) return;
            tracker.tick();
        }

    }
}
