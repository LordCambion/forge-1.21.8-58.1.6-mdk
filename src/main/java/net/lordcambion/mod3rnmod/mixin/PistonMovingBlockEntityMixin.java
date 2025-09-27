package net.lordcambion.mod3rnmod.mixin;

import net.lordcambion.mod3rnmod.block.ModBlocks; // importa il tuo ModBlocks
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Permette ai pistoni di trattare anche il tuo GlueBlock come "sticky for entities",
 * cioè di trascinare/riportare indietro le entità come fa l'Honey Block.
 */
@Mixin(PistonMovingBlockEntity.class)
public class PistonMovingBlockEntityMixin {

    @Shadow
    private BlockState movedState; // shadow della field privata nel vanilla

    /**
     * Se il movedState è il tuo GlueBlock, forza il ritorno true.
     * Inject in testa e cancellabile così eviti di chiamare la vanilla.
     */
    @Inject(method = "isStickyForEntities", at = @At("HEAD"), cancellable = true)
    private void mod3rnmod_isStickyForEntities(CallbackInfoReturnable<Boolean> cir) {
        if (this.movedState != null) {
            // Se ModBlocks.GLUE_BLOCK è un RegistryObject<Block> usa ModBlocks.GLUE_BLOCK.get()
            if (this.movedState.is(ModBlocks.GLUE_BLOCK.get())) {
                cir.setReturnValue(true);
            }
        }
    }
}
