package net.lordcambion.mod3rnmod.item;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.item.custom.Chisel.ChiselItem;
import net.lordcambion.mod3rnmod.item.custom.FuelItem;
import net.lordcambion.mod3rnmod.item.custom.Hammer.HammerItem;
import net.lordcambion.mod3rnmod.item.custom.Tools.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.Enchantable;
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
                    .repairable(Items.IRON_INGOT)
                    .setId(ITEMS.key("chisel"))));

    public static final RegistryObject<Item> ARKADIUM_SWORD= ITEMS.register("arkadium_sword",
            ()->new ModSwordItem(new Item.Properties()
                    .sword(ModToolMaterials.ARKADIUM,3,-2.4f)
                    .repairable(ModItems.ARKADIUM_INGOT.get())
                    .component(DataComponents.ENCHANTABLE,new Enchantable(15))
                    .setId(ITEMS.key("arkadium_sword"))));

    public static final RegistryObject<Item> ARKADIUM_PICKAXE= ITEMS.register("arkadium_pickaxe",
            ()->new ModPickaxeItem(new Item.Properties()
                    .pickaxe(ModToolMaterials.ARKADIUM,1,-2.8f)
                    .repairable(ModItems.ARKADIUM_INGOT.get())
                    .setId(ITEMS.key("arkadium_pickaxe"))));

public static final RegistryObject<Item> ARKADIUM_AXE= ITEMS.register("arkadium_axe",
            ()->new ModAxeItem(new Item.Properties()
                    .axe(ModToolMaterials.ARKADIUM,5,-3.0f)
                    .repairable(ModItems.ARKADIUM_INGOT.get())
                    .component(DataComponents.ENCHANTABLE,new Enchantable(15))
                    .setId(ITEMS.key("arkadium_axe"))));

    public static final RegistryObject<Item> ARKADIUM_SHOVEL= ITEMS.register("arkadium_shovel",
            ()->new ModShovelItem(new Item.Properties()
                    .shovel(ModToolMaterials.ARKADIUM,1.5f,-3.0f)
                    .component(DataComponents.ENCHANTABLE,new Enchantable(15))
                    .repairable(ModItems.ARKADIUM_INGOT.get())

                    .setId(ITEMS.key("arkadium_shovel"))));

    public static final RegistryObject<Item> ARKADIUM_HOE= ITEMS.register("arkadium_hoe",
            ()->new ModHoeItem(new Item.Properties()
                    .hoe(ModToolMaterials.ARKADIUM,-3.0f,0.0f)
                    .repairable(ModItems.ARKADIUM_INGOT.get())
                    .component(DataComponents.ENCHANTABLE,new Enchantable(15))
                    .setId(ITEMS.key("arkadium_hoe"))));

    public static final RegistryObject<Item> ARKADIUM_HAMMER= ITEMS.register("arkadium_hammer",
            ()->new HammerItem(new Item.Properties()
                    .pickaxe(ModToolMaterials.ARKADIUM,7,-3.5f)
                    .repairable(ModItems.ARKADIUM_INGOT.get())
                    .component(DataComponents.ENCHANTABLE,new Enchantable(15))
                    .setId(ITEMS.key("arkadium_hammer"))));

    public static final RegistryObject<Item> IRON_HAMMER= ITEMS.register("iron_hammer",
            ()->new HammerItem(new Item.Properties()
                    .pickaxe(ToolMaterial.IRON,7,-3.5f)
                    .repairable(Items.IRON_INGOT)
                    .component(DataComponents.ENCHANTABLE,new Enchantable(15))
                    .setId(ITEMS.key("iron_hammer"))));

    public static final RegistryObject<Item> GOLDEN_HAMMER= ITEMS.register("golden_hammer",
            ()->new HammerItem(new Item.Properties()
                    .pickaxe(ToolMaterial.GOLD,7,-3.5f)
                    .repairable(Items.GOLD_INGOT)
                    .component(DataComponents.ENCHANTABLE,new Enchantable(15))
                    .setId(ITEMS.key("golden_hammer"))));

    public static final RegistryObject<Item> DIAMOND_HAMMER= ITEMS.register("diamond_hammer",
            ()->new HammerItem(new Item.Properties()
                    .pickaxe(ToolMaterial.DIAMOND,7,-3.5f)
                    .repairable(Items.DIAMOND)
                    .component(DataComponents.ENCHANTABLE,new Enchantable(15))
                    .setId(ITEMS.key("diamond_hammer"))));

    public static final RegistryObject<Item> NETHERITE_HAMMER= ITEMS.register("netherite_hammer",
            ()->new HammerItem(new Item.Properties()
                    .pickaxe(ToolMaterial.NETHERITE,7,-3.5f)
                    .repairable(Items.NETHERITE_INGOT)
                    .component(DataComponents.ENCHANTABLE,new Enchantable(15))
                    .setId(ITEMS.key("netherite_hammer"))));




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
