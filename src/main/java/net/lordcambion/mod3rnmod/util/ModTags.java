package net.lordcambion.mod3rnmod.util;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import javax.swing.text.html.HTML;

public class ModTags {
    public static class Blocks{

        public static final TagKey<Block> NOT_STICKY =createTag("not_sticky");
        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Mod3rnMod.MOD_ID,name));
        }

    }

    public static class Items{
        //public static final TagKey<Item> TRANSFORMABLE_ITEMS =createTag("transformable_items");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Mod3rnMod.MOD_ID,name));
        }
    }
}
