package net.lordcambion.mod3rnmod.block;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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


    public static final RegistryObject<Block> ARKADIUM_DEEPSLATE_ORE =registerBlock("arkadium_deepslate_ore",
            ()-> new DropExperienceBlock(UniformInt.of(3,6), BlockBehaviour.Properties.of()
                    .strength(3.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE).setId(BLOCKS.key("arkadium_deepslate_ore"))));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T>block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registeredBlockItem(name,toReturn);
        return toReturn;

    }

    private static <T extends Block> void registeredBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name,()-> new BlockItem(block.get(),new Item.Properties().setId(ModItems.ITEMS.key(name))));
    }

    public static void register(BusGroup eventBus){
        BLOCKS.register(eventBus);
    }

}
