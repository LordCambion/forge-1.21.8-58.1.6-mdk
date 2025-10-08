package net.lordcambion.mod3rnmod.datagen;

import net.lordcambion.mod3rnmod.item.ModEquipmentAssets;
import net.lordcambion.mod3rnmod.item.ModItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;



import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.Optional;

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
        //generateFlatItem(ModItems.CHISEL.get(), ModelTemplates.FLAT_ITEM);
        generateDamageableItem(ModItems.CHISEL.get(), 0.5f);
        //horse armor

        generateFlatItem(ModItems.ARKADIUM_HORSE_ARMOR.get(),ModelTemplates.FLAT_ITEM);

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

    private void generateDamageableItem(Item item, float damageThresholdNormalized) {
        ResourceLocation itemId = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(item);
        String itemPath = itemId.getPath();

        // === Definisce i percorsi texture ===
        ResourceLocation normalModelLoc = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemPath);
        ResourceLocation damagedModelLoc = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemPath + "_damaged");

        // === Crea i due modelli di base ===
        ModelTemplates.FLAT_ITEM.create(
                normalModelLoc,
                TextureMapping.layer0(normalModelLoc),
                this.modelOutput
        );

        ModelTemplates.FLAT_ITEM.create(
                damagedModelLoc,
                TextureMapping.layer0(damagedModelLoc),
                this.modelOutput
        );

        // === Proprietà numerica “damage” ===
        RangeSelectItemModelProperty damageProperty = new net.minecraft.client.renderer.item.properties.numeric.Damage(true);

        // === Crea le Entry per i diversi stati ===
        List<RangeSelectItemModel.Entry> entries = List.of(
                new RangeSelectItemModel.Entry(
                        0.0f, // da 0 fino alla soglia
                        new BlockModelWrapper.Unbaked(normalModelLoc, List.of())
                ),
                new RangeSelectItemModel.Entry(
                        damageThresholdNormalized, // da qui in poi: danneggiato
                        new BlockModelWrapper.Unbaked(damagedModelLoc, List.of())
                )
        );

        // === Crea il modello “range_dispatch” ===
        ItemModel.Unbaked dispatchModel = new RangeSelectItemModel.Unbaked(
                damageProperty, // proprietà da monitorare
                1.0f,           // scala di normalizzazione (0.0–1.0)
                entries,        // entry dei modelli
                Optional.of(new BlockModelWrapper.Unbaked(normalModelLoc, List.of())) // fallback
        );

        // === Registra il modello generato per l'item ===
        this.itemModelOutput.accept(item, dispatchModel);
    }






}