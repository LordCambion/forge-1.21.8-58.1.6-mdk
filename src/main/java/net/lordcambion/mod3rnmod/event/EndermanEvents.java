package net.lordcambion.mod3rnmod.event;

import net.lordcambion.mod3rnmod.Mod3rnMod;
import net.lordcambion.mod3rnmod.item.ModItems;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Mod3rnMod.MOD_ID)
public class EndermanEvents {

    @SubscribeEvent
    public static void onEndermanTarget(LivingChangeTargetEvent event) {
        if (event.getEntity() instanceof EnderMan enderman) {
            if (event.getNewTarget() instanceof Player player) {
                ItemStack helmet = player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD);
                if (helmet.is(ModItems.ENDER_HELMET.get())) {
                    // Previene completamente che l'Enderman reagisca al giocatore
                    enderman.setTarget(null);
                    resetEndermanState(enderman);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEndermanUpdate(LivingEvent.LivingTickEvent event) {
        // Gestione continua per Enderman che potrebbero aver già iniziato a reagire
        if (event.getEntity() instanceof EnderMan enderman) {
            // Controlla ogni 5 tick per efficienza
            if (enderman.tickCount % 5 == 0) {
                Player nearestPlayer = enderman.level().getNearestPlayer(enderman, 64.0);
                if (nearestPlayer != null) {
                    ItemStack helmet = nearestPlayer.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD);
                    if (helmet.is(ModItems.ENDER_HELMET.get())) {
                        // Resetta lo stato dell'Enderman
                        resetEndermanState(enderman);

                        // Previene che l'Enderman inizi a guardare il giocatore
                        enderman.setTarget(null);
                    }
                }

                // Se per qualche motivo è già arrabbiato con un giocatore con l'elmetto
                if (enderman.getTarget() instanceof Player player) {
                    ItemStack helmet = player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD);
                    if (helmet.is(ModItems.ENDER_HELMET.get())) {
                        resetEndermanState(enderman);
                    }
                }
            }
        }
    }

    private static void resetEndermanState(EnderMan enderman) {
        // Resetta tutti gli stati di reazione
        enderman.setTarget(null);
        enderman.setLastHurtByMob(null);
        //enderman.setLastHurtByPlayer(null);
        enderman.setRemainingPersistentAngerTime(0);

        // Resetta lo stato "creepy" (bocca aperta) se accessibile
        try {
            //enderman.entityData.set(EnderMan.DATA_CREEPY, false);
        } catch (Exception e) {
            // Ignora se non accessibile
        }
    }
}