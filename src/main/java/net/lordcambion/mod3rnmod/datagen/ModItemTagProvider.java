package net.lordcambion.mod3rnmod.datagen;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends VanillaItemTagsProvider {
    public ModItemTagProvider(PackOutput output,
                              CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags,
                              @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Tag per gli enchantment di mining (Fortune, Efficiency, etc.)
        tag(ItemTags.MINING_ENCHANTABLE)
                .add(ModItems.ARKADIUM_PICKAXE.get())
                .add(ModItems.ARKADIUM_AXE.get())
                .add(ModItems.ARKADIUM_SHOVEL.get())
                .add(ModItems.ARKADIUM_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get());

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.ARKADIUM_HELMET.get())
                .add(ModItems.ARKADIUM_CHESTPLATE.get())
                .add(ModItems.ARKADIUM_LEGGINGS.get())
                .add(ModItems.ARKADIUM_BOOTS.get())
                .add(ModItems.ENDER_HELMET.get())
                .add(ModItems.ENDER_CHESTPLATE.get())
                .add(ModItems.ENDER_LEGGINGS.get())
                .add(ModItems.ENDER_BOOTS.get());

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.ARKADIUM_HELMET.get())
                .add(ModItems.ENDER_HELMET.get());

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.ARKADIUM_CHESTPLATE.get())
                .add(ModItems.ENDER_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.ARKADIUM_LEGGINGS.get())
                .add(ModItems.ENDER_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.ARKADIUM_BOOTS.get());

        // Tag per gli enchantment di durability (Unbreaking, Mending)
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.ARKADIUM_PICKAXE.get())
                .add(ModItems.ARKADIUM_AXE.get())
                .add(ModItems.ARKADIUM_SHOVEL.get())
                .add(ModItems.ARKADIUM_HOE.get())
                .add(ModItems.ARKADIUM_SWORD.get())

                .add(ModItems.ARKADIUM_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get())

                .add(ModItems.ARKADIUM_HELMET.get())
                .add(ModItems.ARKADIUM_CHESTPLATE.get())
                .add(ModItems.ARKADIUM_LEGGINGS.get())
                .add(ModItems.ARKADIUM_BOOTS.get())
                .add(ModItems.ENDER_HELMET.get())
                .add(ModItems.ENDER_CHESTPLATE.get())
                .add(ModItems.ENDER_LEGGINGS.get())
                .add(ModItems.ENDER_BOOTS.get());

        // Tag per gli enchantment di weapon (Sharpness, Looting, etc.)
        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.ARKADIUM_SWORD.get())
                .add(ModItems.ARKADIUM_AXE.get());

        // Tag per Vanishing Curse
        tag(ItemTags.VANISHING_ENCHANTABLE)
                .add(ModItems.ARKADIUM_PICKAXE.get())
                .add(ModItems.ARKADIUM_AXE.get())
                .add(ModItems.ARKADIUM_SHOVEL.get())
                .add(ModItems.ARKADIUM_HOE.get())
                .add(ModItems.ARKADIUM_SWORD.get())

                .add(ModItems.ARKADIUM_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get())

                .add(ModItems.ARKADIUM_HELMET.get())
                .add(ModItems.ARKADIUM_CHESTPLATE.get())
                .add(ModItems.ARKADIUM_LEGGINGS.get())
                .add(ModItems.ARKADIUM_BOOTS.get())
                .add(ModItems.ENDER_HELMET.get())
                .add(ModItems.ENDER_CHESTPLATE.get())
                .add(ModItems.ENDER_LEGGINGS.get())
                .add(ModItems.ENDER_BOOTS.get());

        // Tag specifici per tipo di tool
        tag(ItemTags.AXES)
                .add(ModItems.ARKADIUM_AXE.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.ARKADIUM_PICKAXE.get())
                .add(ModItems.ARKADIUM_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.ARKADIUM_SHOVEL.get());

        tag(ItemTags.HOES)
                .add(ModItems.ARKADIUM_HOE.get());

        tag(ItemTags.SWORDS)
                .add(ModItems.ARKADIUM_SWORD.get());

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.ARKADIUM_HELMET.get())
                .add(ModItems.ENDER_HELMET.get());
        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.ARKADIUM_CHESTPLATE.get())
                .add(ModItems.ENDER_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.ARKADIUM_LEGGINGS.get())
                .add(ModItems.ENDER_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.ARKADIUM_BOOTS.get())
                .add(ModItems.ENDER_BOOTS.get());;

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ARKADIUM_HELMET.get())
                .add(ModItems.ARKADIUM_CHESTPLATE.get())
                .add(ModItems.ARKADIUM_LEGGINGS.get())
                .add(ModItems.ARKADIUM_BOOTS.get())
                .add(ModItems.ENDER_HELMET.get())
                .add(ModItems.ENDER_CHESTPLATE.get())
                .add(ModItems.ENDER_LEGGINGS.get())
                .add(ModItems.ENDER_BOOTS.get());


    }
}