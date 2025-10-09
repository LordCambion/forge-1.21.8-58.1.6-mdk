package net.lordcambion.mod3rnmod.item.custom.Bow;


import net.lordcambion.mod3rnmod.item.ModItems;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import java.util.function.Predicate;

public class EnderBowItem extends BowItem {

    public EnderBowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        // Accetta solo le ender arrows E le frecce normali se vuoi
        return (stack) -> stack.is(ModItems.ENDER_ARROW.get()) || stack.is(Items.ARROW);
    }
}