package net.lordcambion.mod3rnmod.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.item.ModEquipmentAssets;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class ModEquipmentAssetProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public ModEquipmentAssetProvider(PackOutput output) {
        // IMPORTANTE: usa RESOURCE_PACK (assets) invece di DATA_PACK (data)
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return CompletableFuture.allOf(
                generateEquipmentAsset(cache, ModEquipmentAssets.ARKADIUM, "arkadium"),
                generateEquipmentAsset(cache, ModEquipmentAssets.ENDER, "ender"),
                generateHorseEquipmentAsset(cache,ModEquipmentAssets.ARKADIUM,"arkadium")

        );
    }

    private CompletableFuture<?> generateEquipmentAsset(CachedOutput cache, ResourceKey<EquipmentAsset> key, String textureName) {
        JsonObject root = new JsonObject();
        JsonObject layers = new JsonObject();

        // Layer per humanoid (helmet, chestplate, boots)
        JsonArray humanoidArray = new JsonArray();
        JsonObject humanoidLayer = new JsonObject();
        humanoidLayer.addProperty("texture", Mod3rnMod.MOD_ID + ":" + textureName);
        humanoidArray.add(humanoidLayer);
        layers.add("humanoid", humanoidArray);

        // Layer per humanoid_leggings (leggings)
        JsonArray leggingsArray = new JsonArray();
        JsonObject leggingsLayer = new JsonObject();
        leggingsLayer.addProperty("texture", Mod3rnMod.MOD_ID + ":" + textureName);
        leggingsArray.add(leggingsLayer);
        layers.add("humanoid_leggings", leggingsArray);

        root.add("layers", layers);

        Path path = this.pathProvider.json(key.location());
        return DataProvider.saveStable(cache, root, path);
    }

    private CompletableFuture<?> generateHorseEquipmentAsset(CachedOutput cache, ResourceKey<EquipmentAsset> key, String textureName) {
        JsonObject root = new JsonObject();
        JsonObject layers = new JsonObject();

        // Layer per humanoid (helmet, chestplate, boots)
        JsonArray horseArray = new JsonArray();
        JsonObject horseLayer = new JsonObject();
        horseLayer.addProperty("texture", Mod3rnMod.MOD_ID + ":" + textureName);
        horseArray.add(horseLayer);
        layers.add("horse_body", horseArray);



        root.add("layers", layers);

        Path path = this.pathProvider.json(key.location());
        return DataProvider.saveStable(cache, root, path);
    }


    @Override
    public String getName() {
        return "Equipment Assets: " + Mod3rnMod.MOD_ID;
    }
}