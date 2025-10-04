package net.lordcambion.mod3rnmod.item.custom.Chisel;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.function.Consumer;

public class ChiselItem extends Item {

    private static final Map<Block ,Block> CHISEL_MAP=
            Map.of(
                    Blocks.STONE_BRICKS,Blocks.CHISELED_STONE_BRICKS,
                    Blocks.COPPER_BLOCK,Blocks.CHISELED_COPPER,
                    Blocks.QUARTZ_BLOCK,Blocks.CHISELED_QUARTZ_BLOCK,
                    Blocks.SANDSTONE,Blocks.CHISELED_SANDSTONE,
                    Blocks.DEEPSLATE,Blocks.CHISELED_DEEPSLATE
            );


    public ChiselItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, TooltipDisplay pTooltipDisplay,
                                Consumer<Component> pTooltipAdder, TooltipFlag pFlag) {
        if(!Screen.hasShiftDown()){
            pTooltipAdder.accept(Component.translatable("tooltip.mod3rnmod.shift_down"));
        }else{
            pTooltipAdder.accept(Component.translatable("tooltip.mod3rnmod.chisel"));
        }

        super.appendHoverText(pStack, pContext, pTooltipDisplay, pTooltipAdder, pFlag);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level =pContext.getLevel();
        Block clickedBlock =level.getBlockState(pContext.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)){
            if(!level.isClientSide()){
                level.setBlockAndUpdate(pContext.getClickedPos(),CHISEL_MAP.get(clickedBlock).defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1,((ServerLevel) level),
                        ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null,pContext.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

            }
        }


        return InteractionResult.SUCCESS;
    }

}
