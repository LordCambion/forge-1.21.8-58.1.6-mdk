package net.lordcambion.mod3rnmod.effect;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public  static  final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Mod3rnMod.MOD_ID);

    public static final RegistryObject<MobEffect> SLIMEY_EFFECT = MOB_EFFECTS.register("slimey",
            () -> new SlimeyEffect(MobEffectCategory.NEUTRAL, 0x36ebab)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(Mod3rnMod.MOD_ID, "slimey"),
                            -0.25f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public  static  void register(BusGroup eventBus){
        MOB_EFFECTS.register(eventBus);
    }

}
