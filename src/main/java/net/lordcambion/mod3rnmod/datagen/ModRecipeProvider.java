package net.lordcambion.mod3rnmod.datagen;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.block.ModBlocks;
import net.lordcambion.mod3rnmod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements DataProvider {
    protected RecipeOutput pOutput;
    protected ModRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
        super(pRegistries, pOutput);
    }

    @Override
    protected void buildRecipes() {
        this.output.includeRootAdvancement();
        this.generateForEnabledBlockFamilies(FeatureFlagSet.of(FeatureFlags.VANILLA));

        // Example usage of the helper functions:

        // Block compression/decompression
        compressBlock(ModBlocks.ARKADIUM_BLOCK.get(), ModItems.ARKADIUM_INGOT.get());
        decompressBlock(ModItems.ARKADIUM_INGOT.get(), ModBlocks.ARKADIUM_BLOCK.get());
        //compressBlock4(ModBlocks.GLUE_BLOCK.get(),ModItems.GLUE_BOTTLE.get());


        // Tool crafting
        craftSword(ModItems.ARKADIUM_SWORD.get(), ModItems.ARKADIUM_INGOT.get(), Items.STICK);
        craftPickaxe(ModItems.ARKADIUM_PICKAXE.get(), ModItems.ARKADIUM_INGOT.get(), Items.STICK);
        craftAxe(ModItems.ARKADIUM_AXE.get(), ModItems.ARKADIUM_INGOT.get(), Items.STICK);
        craftShovel(ModItems.ARKADIUM_SHOVEL.get(), ModItems.ARKADIUM_INGOT.get(), Items.STICK);
        craftHoe(ModItems.ARKADIUM_HOE.get(), ModItems.ARKADIUM_INGOT.get(), Items.STICK);

        craftHammer(ModItems.ARKADIUM_HAMMER.get(), ModItems.ARKADIUM_INGOT.get(), Items.STICK);
        craftHammer(ModItems.GOLDEN_HAMMER.get(), Items.GOLD_INGOT, Items.STICK);
        craftHammer(ModItems.IRON_HAMMER.get(), Items.IRON_INGOT, Items.STICK);
        craftHammer(ModItems.DIAMOND_HAMMER.get(), Items.DIAMOND, Items.STICK);
       // craftHammer(ModItems.GOLDEN_HAMMER.get(), Items.GOLD_INGOT, Items.STICK);
        this.netheriteSmithing(ModItems.DIAMOND_HAMMER.get(),RecipeCategory.TOOLS, ModItems.NETHERITE_HAMMER.get());

        // Armor crafting
        craftHelmet(ModItems.ARKADIUM_HELMET.get(), ModItems.ARKADIUM_INGOT.get());
        craftChestplate(ModItems.ARKADIUM_CHESTPLATE.get(), ModItems.ARKADIUM_INGOT.get());
        craftLeggings(ModItems.ARKADIUM_LEGGINGS.get(), ModItems.ARKADIUM_INGOT.get());
        craftBoots(ModItems.ARKADIUM_BOOTS.get(), ModItems.ARKADIUM_INGOT.get());
        craftHelmet(ModItems.ENDER_HELMET.get(), Items.ENDER_PEARL);
        craftChestplate(ModItems.ENDER_CHESTPLATE.get(), Items.ENDER_PEARL);
        craftLeggings(ModItems.ENDER_LEGGINGS.get(), Items.ENDER_PEARL);
        craftBoots(ModItems.ENDER_BOOTS.get(), Items.ENDER_PEARL);
        // Your existing recipes
//        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ARKADIUM_BLOCK.get())
//                .pattern("###")
//                .pattern("###")
//                .pattern("###")
//                .define('#', ModItems.ARKADIUM_INGOT.get())
//                .unlockedBy(getHasName(ModItems.ARKADIUM_INGOT.get()), has(ModItems.ARKADIUM_INGOT.get()))
//                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBlocks.GLUE_BLOCK.get())
                .pattern("##")
                .pattern("##")
                .define('#', ModItems.GLUE_BOTTLE.get())
                .unlockedBy(getHasName(ModItems.GLUE_BOTTLE.get()), has(ModItems.GLUE_BOTTLE.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBlocks.COPPER_LAMP.get())
                .pattern("CGC")
                .pattern("GTG")
                .pattern("CGC")
                .define('G', Items.GLASS)
                .define('T', Items.REDSTONE_TORCH)
                .define('C', Items.COPPER_INGOT)
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .unlockedBy(getHasName(Items.REDSTONE_TORCH), has(Items.REDSTONE_TORCH))
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(this.output);

        this.shaped(RecipeCategory.TOOLS, ModItems.CHISEL.get())
                .pattern("AB")
                .define('A', Items.STICK)
                .define('B', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModItems.ARKADIUM_INGOT.get(), 9)
                .requires(ModBlocks.ARKADIUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ARKADIUM_BLOCK.get()), has(ModBlocks.ARKADIUM_BLOCK.get()))
                .save(this.output, Mod3rnMod.MOD_ID + ":arkadium_ingot_from_arkadium_block");

        this.oreSmelting(List.of(ModItems.RAW_ARKADIUM.get(), ModBlocks.ARKADIUM_DEEPSLATE_ORE.get(), ModBlocks.ARKADIUM_ORE.get()), RecipeCategory.MISC, ModItems.ARKADIUM_INGOT.get(), 0.25f, 200, "arkadium");
        this.oreBlasting(List.of(ModItems.RAW_ARKADIUM.get(), ModBlocks.ARKADIUM_DEEPSLATE_ORE.get(), ModBlocks.ARKADIUM_ORE.get()), RecipeCategory.MISC, ModItems.ARKADIUM_INGOT.get(), 0.25f, 200, "arkadium");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.OBSIDIAN), RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_STAIRS.get())
                .unlockedBy(getHasName(Blocks.OBSIDIAN), has(Blocks.OBSIDIAN))
                .save(this.output, Mod3rnMod.MOD_ID + ":obsidian_stairs_from_stonecutter");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.OBSIDIAN), RecipeCategory.BUILDING_BLOCKS, ModBlocks.OBSIDIAN_SLAB.get(), 2)
                .unlockedBy(getHasName(Blocks.OBSIDIAN), has(Blocks.OBSIDIAN))
                .save(this.output, Mod3rnMod.MOD_ID + ":obsidian_slab_from_stonecutter");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.OBSIDIAN), RecipeCategory.DECORATIONS, ModBlocks.OBSIDIAN_WALL.get())
                .unlockedBy(getHasName(Blocks.OBSIDIAN), has(Blocks.OBSIDIAN))
                .save(this.output, Mod3rnMod.MOD_ID + ":obsidian_wall_from_stonecutter");
    }

    // ============ HELPER FUNCTIONS ============

    /**
     * Compress 9 items into a block (e.g., 9 ingots -> 1 block)
     */
    protected void compressBlock(ItemLike block, ItemLike ingredient) {
        this.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(this.output);
    }

    /**
     * Decompress a block into 9 items (e.g., 1 block -> 9 ingots)
     */
    protected void decompressBlock(ItemLike result, ItemLike block) {
        this.shapeless(RecipeCategory.MISC, result, 9)
                .requires(block)
                .unlockedBy(getHasName(block), has(block))
                .save(this.output);
    }

    /**
     * Compress 4 items into a block (e.g., 4 items -> 1 block)
     */
    protected void compressBlock4(ItemLike block, ItemLike ingredient) {
        this.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("##")
                .pattern("##")
                .define('#', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(this.output);
    }

    /**
     * Craft a sword
     */
    protected void craftSword(ItemLike sword, ItemLike material, ItemLike stick) {
        this.shaped(RecipeCategory.COMBAT, sword)
                .pattern("M")
                .pattern("M")
                .pattern("S")
                .define('M', material)
                .define('S', stick)
                .unlockedBy(getHasName(material), has(material))
                .save(this.output);
    }

    /**
     * Craft a pickaxe
     */
    protected void craftPickaxe(ItemLike pickaxe, ItemLike material, ItemLike stick) {
        this.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("MMM")
                .pattern(" S ")
                .pattern(" S ")
                .define('M', material)
                .define('S', stick)
                .unlockedBy(getHasName(material), has(material))
                .save(this.output);
    }

    /**
     * Craft an axe
     */
    protected void craftAxe(ItemLike axe, ItemLike material, ItemLike stick) {
        this.shaped(RecipeCategory.TOOLS, axe)
                .pattern("MM")
                .pattern("MS")
                .pattern(" S")
                .define('M', material)
                .define('S', stick)
                .unlockedBy(getHasName(material), has(material))
                .save(this.output);
    }

    /**
     * Craft a shovel
     */
    protected void craftShovel(ItemLike shovel, ItemLike material, ItemLike stick) {
        this.shaped(RecipeCategory.TOOLS, shovel)
                .pattern("M")
                .pattern("S")
                .pattern("S")
                .define('M', material)
                .define('S', stick)
                .unlockedBy(getHasName(material), has(material))
                .save(this.output);
    }

    /**
     * Craft a hoe
     */
    protected void craftHoe(ItemLike hoe, ItemLike material, ItemLike stick) {
        this.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("MM")
                .pattern(" S")
                .pattern(" S")
                .define('M', material)
                .define('S', stick)
                .unlockedBy(getHasName(material), has(material))
                .save(this.output);
    }

    /**
     * Craft a Hammer
     */
    protected void craftHammer(ItemLike hammer, ItemLike material, ItemLike stick) {
        this.shaped(RecipeCategory.TOOLS, hammer)
                .pattern(" M ")
                .pattern(" SM")
                .pattern("S  ")
                .define('M', material)
                .define('S', stick)
                .unlockedBy(getHasName(material), has(material))
                .save(this.output);
    }

    /**
     * Craft a helmet
     */
    protected void craftHelmet(ItemLike helmet, ItemLike material ) {
        this.shaped(RecipeCategory.COMBAT, helmet)
                .pattern("MMM")
                .pattern("M M")
                .define('M', material)
                .unlockedBy(getHasName(material), has(material))
                .save(this.output );
    }

    /**
     * Craft a chestplate
     */
    protected void craftChestplate(ItemLike chestplate, ItemLike material) {
        this.shaped(RecipeCategory.COMBAT, chestplate)
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', material)
                .unlockedBy(getHasName(material), has(material))
                .save(this.output );
    }

    /**
     * Craft leggings
     */
    protected void craftLeggings(ItemLike leggings, ItemLike material) {
        this.shaped(RecipeCategory.COMBAT, leggings)
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .define('M', material)
                .unlockedBy(getHasName(material), has(material))
                .save(this.output);
    }

    /**
     * Craft boots
     */
    protected void craftBoots(ItemLike boots, ItemLike material) {
        this.shaped(RecipeCategory.COMBAT, boots)
                .pattern("M M")
                .pattern("M M")
                .define('M', material)
                .unlockedBy(getHasName(material), has(material))
                .save(this.output);
    }

    // ============ EXISTING METHODS ============

    @Override
    protected void generateForEnabledBlockFamilies(FeatureFlagSet pEnabledFeatures) {
        ModBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach(p_358446_ -> this.generateRecipes(p_358446_, pEnabledFeatures));
    }

    protected void oreSmelting(List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        this.oreCooking(RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected void oreBlasting(List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        this.oreCooking(RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    private <T extends AbstractCookingRecipe> void oreCooking(
            RecipeSerializer<T> pSerializer,
            AbstractCookingRecipe.Factory<T> pRecipeFactory,
            List<ItemLike> pIngredients,
            RecipeCategory pCategory,
            ItemLike pResult,
            float pExperience,
            int pCookingTime,
            String pGroup,
            String pSuffix
    ) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer, pRecipeFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), this.has(itemlike))
                    .save(this.output, Mod3rnMod.MOD_ID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }
    }

    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput p_365932_, CompletableFuture<HolderLookup.Provider> p_363203_) {
            super(p_365932_, p_363203_);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
            return new ModRecipeProvider(pRegistries, pOutput);
        }

        @Override
        public String getName() {
            return "Bean Machine Recipes";
        }
    }
}