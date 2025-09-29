package net.lordcambion.mod3rnmod.datagen;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Mod3rnMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.PYRESTONE_ORE.get())
                .add(ModBlocks.ARKADIUM_ORE.get())
                .add(ModBlocks.ARKADIUM_BLOCK.get())
                .add(ModBlocks.ARKADIUM_DEEPSLATE_ORE.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PYRESTONE_ORE.get())
                .add(ModBlocks.ARKADIUM_ORE.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ARKADIUM_DEEPSLATE_ORE.get());

        //tag(BlockTags.FENCES)
                //.add(ModBlocks.BEAN_FENCE.get());
       // tag(BlockTags.FENCE_GATES)
                //.add(ModBlocks.BEAN_FENCE_GATE.get());
        //tag(BlockTags.WALLS)
                //.add(ModBlocks.BEAN_WALL.get());
    }
}