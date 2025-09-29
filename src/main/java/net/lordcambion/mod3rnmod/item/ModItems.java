package net.lordcambion.mod3rnmod.item;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.item.custom.ChiselItem;
import net.lordcambion.mod3rnmod.item.custom.FuelItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraftforge.eventbus.api.bus.BusGroup;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Mod3rnMod.MOD_ID);

    //minerali
    public static final RegistryObject<Item> ARKADIUM_INGOT = ITEMS.register("arkadium_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("arkadium_ingot"))
            )
    );

    public static final RegistryObject<Item> RAW_ARKADIUM = ITEMS.register("raw_arkadium",
            () -> new Item(new Item.Properties()
                    .setId(ITEMS.key("raw_arkadium"))
            )
    );

    //fuels
    public static final RegistryObject<Item> PYRESTONE =ITEMS.register("pyrestone",
            ()->new FuelItem(new Item.Properties()
                    .setId(ITEMS.key("pyrestone")),2800));


    //utilities
    public static final RegistryObject<Item> GLUE_BOTTLE = ITEMS.register("glue_bottle",
            () -> new Item(new Item.Properties()
                    .stacksTo(16) // max_stack_size
                    .craftRemainder(Items.GLASS_BOTTLE)
                    .setId(ITEMS.key("glue_bottle"))
            )
    );

    //tools
    public static final RegistryObject<Item> CHISEL= ITEMS.register("chisel",
            ()->new ChiselItem(new Item.Properties()
                    .durability(32)
                    .setId(ITEMS.key("chisel"))));


    //foods
    public static final RegistryObject<Item> POOP = ITEMS.register("poop",
            ()->new Item(new Item.Properties()
                    .food(ModFoodProperties.POOP,ModConsumablesProperties.POOP)

                    .setId(ITEMS.key("poop"))){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, TooltipDisplay pTooltipDisplay,
                                            Consumer<Component> pTooltipAdder, TooltipFlag pFlag) {
                    if(!Screen.hasShiftDown()){
                        pTooltipAdder.accept(Component.translatable("tooltip.mod3rnmod.shift_down"));
                    }else{
                        pTooltipAdder.accept(Component.translatable("tooltip.mod3rnmod.poop"));
                    }

                    super.appendHoverText(pStack, pContext, pTooltipDisplay, pTooltipAdder, pFlag);
                }
            });


    public static void register(BusGroup eventBus){
        ITEMS.register(eventBus);
    }


}
