package net.lordcambion.mod3rnmod.worldgen.tree;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public  static final TreeGrower WALNUT = new TreeGrower(Mod3rnMod.MOD_ID+ ":walnut",
            Optional.empty(),Optional.of(ModConfiguredFeatures.WALNUT_TREE_KEY),Optional.empty());
}
