package net.lordcambion.mod3rnmod.block.custom;

import net.lordcambion.mod3rnmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HoneyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class GlueBlock extends HoneyBlock {

    public GlueBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity, InsideBlockEffectApplier pApplier) {
        // Blocca completamente il movimento orizzontale e verticale
        Vec3 vec3 = new Vec3(0.0, 0.0, 0.0); // Velocità ridotta a zero in tutte le direzioni

        if (pEntity instanceof LivingEntity livingentity && livingentity.hasEffect(MobEffects.WEAVING)) {
            vec3 = new Vec3(0.0, 0.0, 0.0); // Anche con l'effetto WEAVING, movimento zero
        }

        pEntity.makeStuckInBlock(pState, vec3);

        // Blocca ulteriormente il movimento
        pEntity.setDeltaMovement(0, 0, 0);
        pEntity.hurtMarked = true;

        // Per i player, blocca esplicitamente il salto
        if (pEntity instanceof Player player) {
            // Imposta la velocità del player a zero
            player.setDeltaMovement(0, player.getDeltaMovement().y, 0);

            // Previene il salto azzerando la componente verticale del movimento
            if (player.verticalCollisionBelow) {
                player.setDeltaMovement(player.getDeltaMovement().x, 0, player.getDeltaMovement().z);
            }
        }
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        // Blocca il movimento quando l'entità cammina sul blocco
        if (!pEntity.isShiftKeyDown()) {
            // Azzera completamente il movimento
            pEntity.setDeltaMovement(0, 0, 0);
            pEntity.hurtMarked = true;

            // Per i player, gestisci esplicitamente il blocco del salto
            if (pEntity instanceof Player player) {
                player.setDeltaMovement(0, 0, 0);

                // Impedisce il salto rimuovendo la capacità di saltare temporaneamente

            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, double pFallDistance) {
        pEntity.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 1.0F, 1.0F);


        if (pEntity.causeFallDamage(pFallDistance, 0.2F, pLevel.damageSources().fall())) {
            pEntity.playSound(this.soundType.getFallSound(), this.soundType.getVolume() * 0.5F, this.soundType.getPitch() * 0.75F);
        }

    }



    @Override
    public void updateEntityMovementAfterFallOn(BlockGetter pLevel, Entity pEntity) {
        if (!pEntity.isShiftKeyDown()) {
            pEntity.setDeltaMovement(0, 0, 0);
            pEntity.hurtMarked = true;
        }
        super.updateEntityMovementAfterFallOn(pLevel, pEntity);
    }


}