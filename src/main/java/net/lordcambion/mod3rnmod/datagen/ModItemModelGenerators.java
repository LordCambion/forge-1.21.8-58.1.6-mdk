package net.lordcambion.mod3rnmod.datagen;

import net.lordcambion.mod3rnmod.item.ModEquipmentAssets;
import net.lordcambion.mod3rnmod.item.ModItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

public class ModItemModelGenerators extends ItemModelGenerators {
    public ModItemModelGenerators(ItemModelOutput pItemModelOutput, BiConsumer<ResourceLocation, ModelInstance> pModelOutput) {
        super(pItemModelOutput, pModelOutput);
    }

    @Override
    public void run() {

        //ores
        generateFlatItem(ModItems.PYRESTONE.get(), ModelTemplates.FLAT_ITEM);

        //food
        generateFlatItem(ModItems.POOP.get(), ModelTemplates.FLAT_ITEM);

       //misc
        generateFlatItem(ModItems.GLUE_BOTTLE.get(), ModelTemplates.FLAT_ITEM);

        //tools
        generateFlatItem(ModItems.CHISEL.get(), ModelTemplates.FLAT_ITEM);

        //arkadium
        generateFlatItem(ModItems.RAW_ARKADIUM.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModItems.ARKADIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModItems.ARKADIUM_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(ModItems.ARKADIUM_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(ModItems.ARKADIUM_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(ModItems.ARKADIUM_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(ModItems.ARKADIUM_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateTrimmableItem(ModItems.ARKADIUM_HELMET.get(), ModEquipmentAssets.ARKADIUM, TRIM_PREFIX_HELMET, false);
        generateTrimmableItem(ModItems.ARKADIUM_CHESTPLATE.get(), ModEquipmentAssets.ARKADIUM, TRIM_PREFIX_HELMET, false);
        generateTrimmableItem(ModItems.ARKADIUM_LEGGINGS.get(), ModEquipmentAssets.ARKADIUM, TRIM_PREFIX_HELMET, false);
        generateTrimmableItem(ModItems.ARKADIUM_BOOTS.get(), ModEquipmentAssets.ARKADIUM, TRIM_PREFIX_HELMET, false);

        generateTrimmableItem(ModItems.ENDER_HELMET.get(), ModEquipmentAssets.ENDER, TRIM_PREFIX_HELMET, false);
        generateTrimmableItem(ModItems.ENDER_CHESTPLATE.get(), ModEquipmentAssets.ENDER, TRIM_PREFIX_HELMET, false);
        generateTrimmableItem(ModItems.ENDER_LEGGINGS.get(), ModEquipmentAssets.ENDER, TRIM_PREFIX_HELMET, false);
        generateTrimmableItem(ModItems.ENDER_BOOTS.get(), ModEquipmentAssets.ENDER, TRIM_PREFIX_HELMET, false);


        //hammers
        generateFlatItem(ModItems.ARKADIUM_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(ModItems.IRON_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(ModItems.GOLDEN_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(ModItems.DIAMOND_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(ModItems.NETHERITE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);


        if (this.itemModelOutput instanceof ModModelProvider.ModItemInfoCollector collector)
            collector.generateDefaultBlockModels();



    }
        private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ,0.1f);
        trimMaterials.put(TrimMaterials.IRON,0.2f);
        trimMaterials.put(TrimMaterials.NETHERITE,0.3f);
        trimMaterials.put(TrimMaterials.REDSTONE,0.4f);
        trimMaterials.put(TrimMaterials.COPPER,0.5f);
        trimMaterials.put(TrimMaterials.GOLD,0.6f);
        trimMaterials.put(TrimMaterials.EMERALD,0.7f);
        trimMaterials.put(TrimMaterials.DIAMOND,0.8f);
        trimMaterials.put(TrimMaterials.LAPIS,0.9f);
        trimMaterials.put(TrimMaterials.AMETHYST,1.0f);
    }



}