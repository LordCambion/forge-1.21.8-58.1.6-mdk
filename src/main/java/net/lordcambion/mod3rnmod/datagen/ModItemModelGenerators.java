package net.lordcambion.mod3rnmod.datagen;

import net.lordcambion.mod3rnmod.item.ModItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiConsumer;

public class ModItemModelGenerators extends ItemModelGenerators {
    public ModItemModelGenerators(ItemModelOutput pItemModelOutput, BiConsumer<ResourceLocation, ModelInstance> pModelOutput) {
        super(pItemModelOutput, pModelOutput);
    }

    @Override
    public void run() {
        generateFlatItem(ModItems.PYRESTONE.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModItems.POOP.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModItems.CHISEL.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModItems.RAW_ARKADIUM.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModItems.ARKADIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(ModItems.GLUE_BOTTLE.get(), ModelTemplates.FLAT_ITEM);

        if (this.itemModelOutput instanceof ModModelProvider.ModItemInfoCollector collector)
            collector.generateDefaultBlockModels();
    }
}