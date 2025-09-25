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

     public static final RegistryObject<CreativeModeTab> ARKADIUM_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("arkadium_blocks_tab",
                    ()->CreativeModeTab.builder().icon(()-> new ItemStack(ModBlocks.ARKADIUM_BLOCK.get()))
                            .withTabsBefore(ARKADIUM_ITEMS_TAB.getId())
                            .title(Component.translatable("creativetab.mod3rnmod.arkadium_blocks"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept(ModBlocks.ARKADIUM_BLOCK.get());
                               // pOutput.accept((ModBlocks.ARKADIUM_BLOCK.get()));
                            }).build());




    public static void register(BusGroup eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
