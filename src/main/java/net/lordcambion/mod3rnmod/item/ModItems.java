package net.lordcambion.mod3rnmod.item;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.item.custom.Arrow.EnderArrowItem;
import net.lordcambion.mod3rnmod.item.custom.Bow.EnderBowItem;
import net.lordcambion.mod3rnmod.item.custom.Chisel.ChiselItem;
import net.lordcambion.mod3rnmod.item.custom.FuelItem;
import net.lordcambion.mod3rnmod.item.custom.Hammer.HammerItem;
import net.lordcambion.mod3rnmod.item.custom.Helmet.EnderHelmetItem;
import net.lordcambion.mod3rnmod.item.custom.Tools.*;
import net.lordcambion.mod3rnmod.sound.ModSound;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.Enchantable;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.waypoints.Waypoint;
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

    //arkadium
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

    //armors
    //arkadium
    public static final RegistryObject<Item> ARKADIUM_HELMET = ITEMS.register("arkadium_helmet",
            ()-> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.ARKADIUM, ArmorType.HELMET).
                    setId(ITEMS.key("arkadium_helmet"))));
    public static final RegistryObject<Item> ARKADIUM_CHESTPLATE = ITEMS.register("arkadium_chestplate",
            ()-> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.ARKADIUM, ArmorType.CHESTPLATE).
                    setId(ITEMS.key("arkadium_chestplate"))));
    public static final RegistryObject<Item> ARKADIUM_LEGGINGS = ITEMS.register("arkadium_leggings",
            ()-> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.ARKADIUM, ArmorType.LEGGINGS).
                    setId(ITEMS.key("arkadium_leggings"))));
    public static final RegistryObject<Item> ARKADIUM_BOOTS = ITEMS.register("arkadium_boots",
            ()-> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.ARKADIUM, ArmorType.BOOTS).
                    setId(ITEMS.key("arkadium_boots"))));

        //horse
        public static  final RegistryObject<Item> ARKADIUM_HORSE_ARMOR =ITEMS.register("arkadium_horse_armor",
                ()-> new Item(new Item.Properties().horseArmor(ModArmorMaterials.ARKADIUM).
                        setId(ITEMS.key("arkadium_horse_armor"))));


    //ender
    public static final RegistryObject<Item> ENDER_HELMET = ITEMS.register("ender_helmet",
            () -> new EnderHelmetItem(new Item.Properties()
                    .humanoidArmor(ModArmorMaterials.ENDER, ArmorType.HELMET)

                    .component(
                            DataComponents.ATTRIBUTE_MODIFIERS,
                            ItemAttributeModifiers.builder()
                                    .add(Attributes.WAYPOINT_TRANSMIT_RANGE, Waypoint.WAYPOINT_TRANSMIT_RANGE_HIDE_MODIFIER, EquipmentSlotGroup.HEAD, ItemAttributeModifiers.Display.hidden())
                                    .build()
                    )
                    .setId(ITEMS.key("ender_helmet"))
            )
    );
    public static final RegistryObject<Item> ENDER_CHESTPLATE = ITEMS.register("ender_chestplate",
            ()-> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.ENDER, ArmorType.CHESTPLATE).
                    setId(ITEMS.key("ender_chestplate"))));
    public static final RegistryObject<Item> ENDER_LEGGINGS = ITEMS.register("ender_leggings",
            ()-> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.ENDER, ArmorType.LEGGINGS).
                    setId(ITEMS.key("ender_leggings"))));
    public static final RegistryObject<Item> ENDER_BOOTS = ITEMS.register("ender_boots",
            ()-> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.ENDER, ArmorType.BOOTS).
                    setId(ITEMS.key("ender_boots"))));


    //hammers
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

    //bow
    public static  final RegistryObject<Item> ENDER_BOW =ITEMS.register("ender_bow",
            ()-> new EnderBowItem(new Item.Properties()
                    .durability(500)
                    .enchantable(1)
                    .setId(ITEMS.key("ender_bow"))));

    //projectiles

    public static  final RegistryObject<Item> ENDER_ARROW =ITEMS.register("ender_arrow",
            ()-> new EnderArrowItem(new Item.Properties()
                    .setId(ITEMS.key("ender_arrow"))));
    //discs

    public static final RegistryObject<Item>CRYSIS_DISC=ITEMS.register("crysis_music_disc",
            ()-> new Item(new Item.Properties().rarity(Rarity.RARE).jukeboxPlayable(ModSound.CRYSIS_KEY).stacksTo(1)
                    .setId(ITEMS.key("crysis_music_disc"))));

    public static final RegistryObject<Item>DYNAMITE_DISC=ITEMS.register("dynamite_music_disc",
            ()-> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).jukeboxPlayable(ModSound.DYNAMITE_KEY).stacksTo(1)
                    .setId(ITEMS.key("dynamite_music_disc"))));

    public static final RegistryObject<Item>PANICDOX_DISC=ITEMS.register("panicdox_music_disc",
            ()-> new Item(new Item.Properties().rarity(Rarity.EPIC).jukeboxPlayable(ModSound.PANICDOX_KEY).stacksTo(1)
                    .setId(ITEMS.key("panicdox_music_disc"))));

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
