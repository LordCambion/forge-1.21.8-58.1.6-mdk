package net.lordcambion.mod3rnmod.item;

import net.lordcambion.mod3rnmod.util.ModTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public class ModToolMaterials {
    public static final ToolMaterial ARKADIUM = new ToolMaterial(
            ModTags.Blocks.INCORRECT_FOR_ARKADIUM_TOOL, // Blocks that can't be mined
            2031,                                       // Durability
            4.0F,                                       // Mining speed (deve essere float)
            3.0F,                                       // Attack damage bonus
            20,                                         // Enchantment value
            ModTags.Items.ARKADIUM_REPAIR               // Repair items tag
    );
}