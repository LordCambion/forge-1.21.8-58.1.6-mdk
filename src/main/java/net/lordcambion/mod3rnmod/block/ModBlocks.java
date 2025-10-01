package net.lordcambion.mod3rnmod.block;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.block.custom.GlueBlock;

import net.lordcambion.mod3rnmod.block.custom.LampBlock;
import net.lordcambion.mod3rnmod.block.custom.nonMovable.*;
import net.lordcambion.mod3rnmod.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS=
            DeferredRegister.create(ForgeRegistries.BLOCKS, Mod3rnMod.MOD_ID);

    public static final RegistryObject<Block> ARKADIUM_BLOCK =registerBlock("arkadium_block",
            ()-> new Block(BlockBehaviour.Properties.of()
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL).setId(BLOCKS.key("arkadium_block"))));


    public static final RegistryObject<Block> ARKADIUM_ORE =registerBlock("arkadium_ore",
            ()-> new DropExperienceBlock(UniformInt.of(2,4), BlockBehaviour.Properties.of()
                    .strength(2.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE).setId(BLOCKS.key("arkadium_ore"))));

    public static final RegistryObject<Block> PYRESTONE_ORE =registerBlock("pyrestone_ore",
            ()-> new DropExperienceBlock(UniformInt.of(3,5), BlockBehaviour.Properties.of()
                    .strength(3.33f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERRACK).setId(BLOCKS.key("pyrestone_ore"))));


    public static final RegistryObject<Block> ARKADIUM_DEEPSLATE_ORE =registerBlock("arkadium_deepslate_ore",
            ()-> new DropExperienceBlock(UniformInt.of(3,6), BlockBehaviour.Properties.of()
                    .strength(3.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE).setId(BLOCKS.key("arkadium_deepslate_ore"))
            ));


    public static final RegistryObject<Block> GLUE_BLOCK = registerBlockWithTooltip("glue_block",
            ()->new GlueBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)

                    .noTerrainParticles()
                    .sound(SoundType.HONEY_BLOCK)
                    .setId(BLOCKS.key("glue_block"))
            ));

    public static final RegistryObject<StairBlock>  OBSIDIAN_STAIRS =registerBlock("obsidian_stairs",
            ()-> new NonMovableStairBlock(Blocks.OBSIDIAN.defaultBlockState(),
                    BlockBehaviour.Properties.of().
                            mapColor(MapColor.COLOR_BLACK).
                            instrument(NoteBlockInstrument.BASEDRUM).
                            requiresCorrectToolForDrops().
                            strength(50.0F, 1200.0F)
                            .setId(BLOCKS.key("obsidian_stairs"))
            ));

public static final RegistryObject<SlabBlock>  OBSIDIAN_SLAB =registerBlock("obsidian_slab",
            ()-> new NonMovableSlabBlock(
                    BlockBehaviour.Properties.of().
                            mapColor(MapColor.COLOR_BLACK).
                            instrument(NoteBlockInstrument.BASEDRUM).
                            requiresCorrectToolForDrops().
                            strength(50.0F, 1200.0F)
                            .setId(BLOCKS.key("obsidian_slab"))
            ));

public static final RegistryObject<PressurePlateBlock>  OBSIDIAN_PRESSURE_PLATE =registerBlock("obsidian_pressure_plate",
            ()-> new NonMovablePressurePlateBlock(BlockSetType.STONE,
                    BlockBehaviour.Properties.of().
                            mapColor(MapColor.COLOR_BLACK).
                            instrument(NoteBlockInstrument.BASEDRUM).
                            requiresCorrectToolForDrops().
                            strength(50.0F, 1200.0F)
                            .setId(BLOCKS.key("obsidian_pressure_plate"))
            ));

public static final RegistryObject<ButtonBlock>  OBSIDIAN_BUTTON =registerBlock("obsidian_button",
            ()-> new NonMovableButtonBlock(BlockSetType.STONE,22,
                    BlockBehaviour.Properties.of().
                            mapColor(MapColor.COLOR_BLACK).
                            instrument(NoteBlockInstrument.BASEDRUM).
                            requiresCorrectToolForDrops().
                            strength(50.0F, 1200.0F)
                            .setId(BLOCKS.key("obsidian_button"))
            ));

public static final RegistryObject<WallBlock>  OBSIDIAN_WALL =registerBlock("obsidian_wall",
            ()-> new NonMovableWallBlock(
                    BlockBehaviour.Properties.of().
                            mapColor(MapColor.COLOR_BLACK).
                            instrument(NoteBlockInstrument.BASEDRUM).
                            requiresCorrectToolForDrops().
                            strength(50.0F, 1200.0F)
                            .setId(BLOCKS.key("obsidian_wall"))
            ));

    public static final RegistryObject<LampBlock> COPPER_LAMP = registerBlock("copper_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.of().strength(2f)
                    .lightLevel(state -> state.getValue(LampBlock.CLICKED) ? 15 : 0)
                    .setId(BLOCKS.key("copper_lamp"))));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registeredBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registeredBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name,()-> new BlockItem(block.get(),new Item.Properties().setId(ModItems.ITEMS.key(name))));
    }
    private static <T extends Block> RegistryObject<T> registerBlockWithTooltip(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                new Item.Properties().setId(ModItems.ITEMS.key(name))) {
            @Override
            public void appendHoverText(ItemStack pStack, TooltipContext pContext, TooltipDisplay pTooltipDisplay,
                                        Consumer<Component> pTooltipAdder, TooltipFlag pFlag) {
                if(!Screen.hasShiftDown()){
                    pTooltipAdder.accept(Component.translatable("tooltip.mod3rnmod.shift_down"));
                }else{
                    pTooltipAdder.accept(Component.translatable("tooltip.mod3rnmod."+name));
                }

                super.appendHoverText(pStack, pContext, pTooltipDisplay, pTooltipAdder, pFlag);
            }
        });

        return toReturn;
    }


    public static void register(BusGroup eventBus){
        BLOCKS.register(eventBus);
    }

}
