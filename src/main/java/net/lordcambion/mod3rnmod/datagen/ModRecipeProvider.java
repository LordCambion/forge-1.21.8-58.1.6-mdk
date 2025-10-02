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

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ARKADIUM_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.ARKADIUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.ARKADIUM_INGOT.get()), has(ModItems.ARKADIUM_INGOT.get()))
                .save(this.output);



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

        /*SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.BEAN.get()), RecipeCategory.FOOD, ModItems.COOKED_BEAN.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.BEAN.get()), has(ModItems.BEAN.get()))
                .save(this.output);*/

        /*SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.COOKED_BEAN.get()), RecipeCategory.MISC, ModItems.CHARRED_BEAN.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.COOKED_BEAN.get()), has(ModItems.COOKED_BEAN.get()))
                .save(this.output);*/

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