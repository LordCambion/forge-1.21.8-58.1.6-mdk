package net.lordcambion.mod3rnmod.datagen;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.enchantment.ModEnchantments;
import net.lordcambion.mod3rnmod.trim.ModTrimMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackEntries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER=new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);
//            .add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap);

    public ModDatapackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries,BUILDER, Set.of(Mod3rnMod.MOD_ID));
    }
}
