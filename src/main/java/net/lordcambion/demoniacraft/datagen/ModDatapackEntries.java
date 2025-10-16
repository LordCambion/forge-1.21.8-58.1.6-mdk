package net.lordcambion.demoniacraft.datagen;

import net.lordcambion.demoniacraft.Demoniacraft;

import net.lordcambion.demoniacraft.enchantment.ModEnchantments;
import net.lordcambion.demoniacraft.worldgen.ModBiomeModifiers;
import net.lordcambion.demoniacraft.worldgen.ModConfiguredFeatures;
import net.lordcambion.demoniacraft.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackEntries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER=new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);
//            .add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap);


    public ModDatapackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries,BUILDER, Set.of(Demoniacraft.MOD_ID));
    }
}
