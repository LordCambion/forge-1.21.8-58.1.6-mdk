package net.lordcambion.mod3rnmod.datagen;

import net.lordcambion.mod3rnmod.block.ModBlocks;
import net.lordcambion.mod3rnmod.block.custom.StrawBerryBushBlock;
import net.lordcambion.mod3rnmod.block.custom.TomatoCropBlock;
import net.lordcambion.mod3rnmod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
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

        //obsidian blocks
        dropSelf(ModBlocks.OBSIDIAN_WALL.get());
        dropSelf(ModBlocks.OBSIDIAN_STAIRS.get());
        this.add(ModBlocks.OBSIDIAN_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.OBSIDIAN_SLAB.get()));
        dropSelf(ModBlocks.OBSIDIAN_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.OBSIDIAN_BUTTON.get());

        dropSelf(ModBlocks.COPPER_LAMP.get());

        //ores -minerals
        this.add(ModBlocks.ARKADIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.ARKADIUM_ORE.get(),ModItems.RAW_ARKADIUM.get()));
        this.add(ModBlocks.ARKADIUM_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.ARKADIUM_DEEPSLATE_ORE.get(),ModItems.RAW_ARKADIUM.get(),1,3));
        this.add(ModBlocks.PYRESTONE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.PYRESTONE_ORE.get(),ModItems.PYRESTONE.get(),1,4));


        //plants
        //plants LootItemCondition.Builder lootItemConditionBuilder= LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.TOMATO_CROP.get()) .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TomatoCropBlock.AGE,5)); this.add(ModBlocks.TOMATO_CROP.get(),this.createCropDrops(ModBlocks.TOMATO_CROP.get(), ModItems.TOMATO.get(),ModItems.TOMATO_SEEDS.get(),lootItemConditionBuilder));


        addCropLoot(ModBlocks.TOMATO_CROP.get(), ModItems.TOMATO.get(), ModItems.TOMATO_SEEDS.get(), TomatoCropBlock.AGE);

        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.STRAWBERRY_BUSH.get(), block -> this.applyExplosionDecay(
                block,LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.STRAWBERRY.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.STRAWBERRY.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));
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
    private void addCropLoot(Block crop, ItemLike produce, ItemLike seed, IntegerProperty ageProperty) {
        int maxAge = Collections.max(ageProperty.getPossibleValues());
        LootItemCondition.Builder ripeCondition =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, maxAge));

        this.add(crop,
                LootTable.lootTable()
                        // Pool per i semi (sempre droppati)
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(LootItem.lootTableItem(seed)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        )
                        // Pool per i pomodori (solo pianta matura)
                        .withPool(
                                LootPool.lootPool()
                                        .when(ripeCondition)
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(LootItem.lootTableItem(produce)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                        )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}


