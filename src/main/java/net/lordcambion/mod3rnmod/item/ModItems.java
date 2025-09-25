package net.lordcambion.mod3rnmod.item;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.item.custom.ChiselItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.bus.BusGroup;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Mod3rnMod.MOD_ID);

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

    public static final RegistryObject<Item> CHISEL= ITEMS.register("chisel",
            ()->new ChiselItem(new Item.Properties()
                    .durability(32)
                    .setId(ITEMS.key("chisel"))));


    public static void register(BusGroup eventBus){
        ITEMS.register(eventBus);
    }


}
