package net.lordcambion.mod3rnmod.worldgen;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature>ARKADIUM_ORE_PLACED_KEY=registerKey("arkadium_ore_placed");
    public static final ResourceKey<PlacedFeature>NETHER_ARKADIUM_ORE_PLACED_KEY=registerKey("nether_arkadium_ore_placed");
    public static final ResourceKey<PlacedFeature>END_ARKADIUM_ORE_PLACED_KEY=registerKey("end_arkadium_ore_placed");
    public static final ResourceKey<PlacedFeature>NETHER_PYRESTONE_ORE_PLACED_KEY=registerKey("nether_pyrestone_ore_placed");

    public static final ResourceKey<PlacedFeature>WALNUT_TREE_PLACED_KEY=registerKey("walnut_tree_placed");

    public static final ResourceKey<PlacedFeature>STRAWBERRY_BUSH_PLACED_KEY=registerKey("strawberry_bush_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        //cf->pf->bm

        register(context,ARKADIUM_ORE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_ARKADIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),VerticalAnchor.absolute(24))));
        register(context,NETHER_ARKADIUM_ORE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_ARKADIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(7,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),VerticalAnchor.absolute(40))));
        register(context,END_ARKADIUM_ORE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.END_ARKADIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),VerticalAnchor.absolute(80))));
        register(context,NETHER_PYRESTONE_ORE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_PYRESTONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),VerticalAnchor.absolute(80))));

        register(context,WALNUT_TREE_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.WALNUT_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.05f, 1),
                        ModBlocks.WALNUT_SAPLING.get()));

        register(context,STRAWBERRY_BUSH_PLACED_KEY,configuredFeatures.getOrThrow(ModConfiguredFeatures.STRAWBERRY_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(),PlacementUtils.HEIGHTMAP_WORLD_SURFACE,BiomeFilter.biome()));



    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Mod3rnMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}
