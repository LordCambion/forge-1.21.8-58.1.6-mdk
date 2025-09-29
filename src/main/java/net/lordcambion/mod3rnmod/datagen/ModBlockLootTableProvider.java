package net.lordcambion.mod3rnmod.datagen;

import net.lordcambion.mod3rnmod.block.ModBlocks;
import net.lordcambion.mod3rnmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagRegistry;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate(){

        dropSelf(ModBlocks.ARKADIUM_BLOCK.get());
        dropSelf(ModBlocks.GLUE_BLOCK.get());

        this.add(ModBlocks.ARKADIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.ARKADIUM_ORE.get(),ModItems.RAW_ARKADIUM.get()));
        this.add(ModBlocks.ARKADIUM_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.ARKADIUM_DEEPSLATE_ORE.get(),ModItems.RAW_ARKADIUM.get(),1,3));
        this.add(ModBlocks.PYRESTONE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.PYRESTONE_ORE.get(),ModItems.PYRESTONE.get(),1,4));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock,Item item,float minDrops,float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}


