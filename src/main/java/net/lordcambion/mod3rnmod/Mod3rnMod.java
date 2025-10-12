package net.lordcambion.mod3rnmod;

import com.mojang.logging.LogUtils;
import net.lordcambion.mod3rnmod.block.ModBlocks;
import net.lordcambion.mod3rnmod.client.renderer.entity.EnderArrowRenderer;
import net.lordcambion.mod3rnmod.component.ModDataComponentTypes;
import net.lordcambion.mod3rnmod.effect.ModEffects;
import net.lordcambion.mod3rnmod.enchantment.ModEnchantmentEffects;
import net.lordcambion.mod3rnmod.entity.projectile.EnderArrowEntity;
import net.lordcambion.mod3rnmod.init.ModEntityTypes;
import net.lordcambion.mod3rnmod.item.ModCreativeModeTabs;
import net.lordcambion.mod3rnmod.item.ModItems;
import net.lordcambion.mod3rnmod.potion.ModPotions;
import net.lordcambion.mod3rnmod.sound.ModSound;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.CreativeModeTabs;
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


@Mod(Mod3rnMod.MOD_ID)
public final class Mod3rnMod {
    public static final String MOD_ID = "mod3rnmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Mod3rnMod(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();

        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::commonSetup);

        ModCreativeModeTabs.register(modBusGroup);
        ModItems.register(modBusGroup);
        ModBlocks.register(modBusGroup);
        ModDataComponentTypes.register(modBusGroup);
        ModSound.register(modBusGroup);
        ModEffects.register(modBusGroup);
        ModPotions.register(modBusGroup);
        ModEntityTypes.register(modBusGroup);
        ModEnchantmentEffects.register(modBusGroup);

        BuildCreativeModeTabContentsEvent.getBus(modBusGroup).addListener(Mod3rnMod::addCreative);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

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
            // Questo è opzionale, puoi rimuoverlo se vuoi

        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            // Registra il renderer per EnderArrowEntity
            event.registerEntityRenderer(ModEntityTypes.ENDER_ARROW.get(), EnderArrowRenderer::new);

        }
    }
}
