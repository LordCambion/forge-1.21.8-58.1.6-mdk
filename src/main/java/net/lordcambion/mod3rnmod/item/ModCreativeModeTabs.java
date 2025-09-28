package net.lordcambion.mod3rnmod.item;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Mod3rnMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ARKADIUM_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("arkadium_items_tab",
                    ()->CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.ARKADIUM_INGOT.get()))
                            .title(Component.translatable("creativetab.mod3rnmod.arkadium_items"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept(ModItems.ARKADIUM_INGOT.get());
                                pOutput.accept((ModItems.RAW_ARKADIUM.get()));

                            }).build());

    public static final RegistryObject<CreativeModeTab> TOOLS_TAB =
            CREATIVE_MODE_TABS.register("tools_tab",
                    ()->CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.CHISEL.get()))
                            .title(Component.translatable("creativetab.mod3rnmod.tools"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept((ModItems.CHISEL.get()));
                            }).build());


    public static final RegistryObject<CreativeModeTab> FOOD_TAB =
            CREATIVE_MODE_TABS.register("food_tab",
                    ()->CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.POOP.get()))
                            .title(Component.translatable("creativetab.mod3rnmod.food"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept(ModItems.POOP.get());
                            }).build());

    public static final RegistryObject<CreativeModeTab> MISCELLANEOUS_TAB =
            CREATIVE_MODE_TABS.register("miscellaneous_tab",
                    ()->CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.PYRESTONE.get()))
                            .title(Component.translatable("creativetab.mod3rnmod.miscellaneous"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept((ModItems.GLUE_BOTTLE.get()));
                                pOutput.accept((ModItems.PYRESTONE.get()));
                            }).build());

     public static final RegistryObject<CreativeModeTab> BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("blocks_tab",
                    ()->CreativeModeTab.builder().icon(()-> new ItemStack(ModBlocks.ARKADIUM_BLOCK.get()))
                            .withTabsBefore(ARKADIUM_ITEMS_TAB.getId())
                            .title(Component.translatable("creativetab.mod3rnmod.blocks"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept(ModBlocks.ARKADIUM_BLOCK.get());
                                pOutput.accept(ModBlocks.ARKADIUM_ORE.get());
                                pOutput.accept(ModBlocks.ARKADIUM_DEEPSLATE_ORE.get());
                                pOutput.accept(ModBlocks.PYRESTONE_ORE.get());
                                pOutput.accept(ModBlocks.GLUE_BLOCK.get());

                            }).build());




    public static void register(BusGroup eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
