package net.lordcambion.demoniacraft.worldgen;

import net.lordcambion.demoniacraft.Demoniacraft;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier>ADD_ARKADIUM_ORE =registerKey("add_arkadium_ore");
    public static final ResourceKey<BiomeModifier>ADD_NETHER_ARKADIUM_ORE =registerKey("add_nether_arkadium_ore");
    public static final ResourceKey<BiomeModifier>ADD_NETHER_PYRESTONE_ORE =registerKey("add_nether_pyrestone_ore");
    public static final ResourceKey<BiomeModifier>ADD_END_ARKADIUM_ORE =registerKey("add_end_arkadium_ore");
    public static final ResourceKey<BiomeModifier>ADD_WALNUT_TREE =registerKey("add_walnut_tree");
    public static final ResourceKey<BiomeModifier>ADD_STRAWBERRY_BUSH =registerKey("add_strawberry_bush");


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

    context.register(ADD_ARKADIUM_ORE,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.ARKADIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));


//    Individual biome
//        context.register(ADD_ARKADIUM_ORE,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
//                HolderSet.direct(biomes.getOrThrow(Biomes.BEACH)),
//                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.ARKADIUM_ORE_PLACED_KEY)),
//                GenerationStep.Decoration.UNDERGROUND_ORES));
    context.register(ADD_NETHER_ARKADIUM_ORE,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.NETHER_ARKADIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

    context.register(ADD_END_ARKADIUM_ORE,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.END_ARKADIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

    context.register(ADD_NETHER_PYRESTONE_ORE,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.NETHER_PYRESTONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

    context.register(ADD_WALNUT_TREE,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.FOREST),biomes.getOrThrow(Biomes.SAVANNA)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.WALNUT_TREE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_STRAWBERRY_BUSH,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.FOREST),biomes.getOrThrow(Biomes.FLOWER_FOREST)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.STRAWBERRY_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Demoniacraft.MOD_ID, name));
    }
}
