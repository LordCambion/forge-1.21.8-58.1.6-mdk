package net.lordcambion.mod3rnmod.enchantment;

import com.mojang.serialization.MapCodec;
import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS=
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Mod3rnMod.MOD_ID);

    public static final RegistryObject<MapCodec<?extends EnchantmentEntityEffect>> LIGHTNING_STRIKER=
            ENTITY_ENCHANTMENT_EFFECTS.register("lightning_striker",
                    ()-> LightningStrikerEnchantmentEffect.CODEC);

    public static void register(BusGroup modBus){
        ENTITY_ENCHANTMENT_EFFECTS.register(modBus);
    }
}
