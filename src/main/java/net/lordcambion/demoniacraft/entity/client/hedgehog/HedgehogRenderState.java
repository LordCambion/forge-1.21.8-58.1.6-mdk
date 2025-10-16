package net.lordcambion.demoniacraft.entity.client.hedgehog;

import net.lordcambion.demoniacraft.entity.HedgehogVariants;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HedgehogRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState(); // AGGIUNTO
    public HedgehogVariants variant;
}