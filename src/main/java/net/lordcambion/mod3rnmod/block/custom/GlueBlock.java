package net.lordcambion.mod3rnmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HoneyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class GlueBlock extends HoneyBlock {

    public GlueBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return true;
    }


    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier applier) {
        // Applica solo un leggero rallentamento quando l’entità è sopra il blocco
        if (!(level.getBlockEntity(pos) instanceof net.minecraft.world.level.block.piston.PistonMovingBlockEntity)) {
            entity.makeStuckInBlock(state, new Vec3(0.2, 0.05, 0.2));
        }

        super.entityInside(state, level, pos, entity, applier);
    }
}
