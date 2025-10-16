package net.lordcambion.demoniacraft.entity.client.hedgehog;

import net.lordcambion.demoniacraft.Demoniacraft;
import net.lordcambion.demoniacraft.entity.custom.HedgehogEntity;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HedgehogRenderer extends AgeableMobRenderer<HedgehogEntity, HedgehogRenderState, HedgehogModel> {
    private static final ResourceLocation HEDGEHOG_TEXTURE = ResourceLocation.fromNamespaceAndPath(Demoniacraft.MOD_ID, "textures/entity/hedgehog.png");

    public HedgehogRenderer(EntityRendererProvider.Context context) {
        // Nota: non hai un modello baby separato, quindi usiamo lo stesso per entrambi
        super(context, new HedgehogModel(context.bakeLayer(HedgehogModel.LAYER_LOCATION)), new HedgehogModel(context.bakeLayer(HedgehogModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(HedgehogRenderState state) {
        return HEDGEHOG_TEXTURE;
    }

    @Override
    public HedgehogRenderState createRenderState() {
        return new HedgehogRenderState();
    }

    @Override
    public void extractRenderState(HedgehogEntity entity, HedgehogRenderState renderState, float partialTick) {
        super.extractRenderState(entity, renderState, partialTick);

        renderState.idleAnimationState.copyFrom(entity.idleAnimationState);
        renderState.walkAnimationState.copyFrom(entity.walkAnimationState); // AGGIUNTO
        //renderState.variant = entity.getVariant();
    }
}