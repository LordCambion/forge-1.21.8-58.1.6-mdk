package net.lordcambion.demoniacraft;

import com.mojang.logging.LogUtils;
import net.lordcambion.demoniacraft.block.ModBlocks;
import net.lordcambion.demoniacraft.client.renderer.entity.EnderArrowRenderer;
import net.lordcambion.demoniacraft.component.ModDataComponentTypes;
import net.lordcambion.demoniacraft.effect.ModEffects;
import net.lordcambion.demoniacraft.enchantment.ModEnchantmentEffects;
import net.lordcambion.demoniacraft.entity.ModEntities;
import net.lordcambion.demoniacraft.entity.client.hedgehog.HedgehogRenderer;
import net.lordcambion.demoniacraft.init.ModEntityTypes;
import net.lordcambion.demoniacraft.item.ModCreativeModeTabs;
import net.lordcambion.demoniacraft.item.ModItems;
import net.lordcambion.demoniacraft.potion.ModPotions;
import net.lordcambion.demoniacraft.sound.ModSound;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;


@Mod(Demoniacraft.MOD_ID)
public final class Demoniacraft {
    public static final String MOD_ID = "demoniacraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Demoniacraft(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();
        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::commonSetup);
        //MinecraftForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modBusGroup);
        ModItems.register(modBusGroup);
        ModBlocks.register(modBusGroup);
        ModDataComponentTypes.register(modBusGroup);
        ModSound.register(modBusGroup);
        ModEffects.register(modBusGroup);
        ModPotions.register(modBusGroup);
        //arrows
        ModEntityTypes.register(modBusGroup);
        //mobs
        ModEntities.register(modBusGroup);
        ModEnchantmentEffects.register(modBusGroup);

        BuildCreativeModeTabContentsEvent.getBus(modBusGroup).addListener(Demoniacraft::addCreative);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()->{
                ComposterBlock.COMPOSTABLES.put(ModItems.TOMATO.get(),0.4f);
                ComposterBlock.COMPOSTABLES.put(ModItems.TOMATO_SEEDS.get(),0.15f);
                ComposterBlock.COMPOSTABLES.put(ModItems.POOP.get(),0.4f);
                ComposterBlock.COMPOSTABLES.put(ModItems.STRAWBERRY.get(),0.13f);

        }
        );

    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.ARKADIUM_INGOT.get());
            event.accept(ModItems.RAW_ARKADIUM.get());
            event.accept(ModItems.PYRESTONE.get());
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.ARKADIUM_BLOCK.get());
        }
        if(event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS){
            event.accept(ModBlocks.GLUE_BLOCK.get());
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModItems.POOP.get());
        }
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModBlocks.ARKADIUM_ORE.get());
            event.accept(ModBlocks.ARKADIUM_DEEPSLATE_ORE.get());
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.TOMATO_CROP.get(), ChunkSectionLayer.CUTOUT);
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.STRAWBERRY_BUSH.get(), ChunkSectionLayer.CUTOUT);
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALNUT_SAPLING.get(), ChunkSectionLayer.CUTOUT);
            });

            EntityRenderers.register(ModEntities.HEDGEHOG.get(), HedgehogRenderer::new);

        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            // Registra il renderer per EnderArrowEntity
            event.registerEntityRenderer(ModEntityTypes.ENDER_ARROW.get(), EnderArrowRenderer::new);

        }
    }
}
