package com.art5019.afrobrazilities.events;
import com.art5019.afrobrazilities.data.FortuneRecord;
import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.particles.DandelionSeed;
import com.art5019.afrobrazilities.utils.FortuneHelper;
import com.art5019.afrobrazilities.utils.ItemStackUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.PARTICLE_TYPES;
import static com.art5019.afrobrazilities.block.Simple_Plants.GROWN_DANDELION_ITEM;
import static com.art5019.afrobrazilities.data.DataAttachments.FORTUNE;
import static com.art5019.afrobrazilities.items.BuzioConch.BUZIO_CONCH;
import static com.art5019.afrobrazilities.particles.ParticleTypes.DANDELION_SEED;
import static net.minecraft.world.item.Items.EMERALD;

@Mod(MODID)
@EventBusSubscriber
public class GrownDandelionRightClicked {

    @SubscribeEvent
    private static void onGrownDandelionRightClick(PlayerInteractEvent.RightClickItem event) {
        if(!event.getLevel().isClientSide) {
            if(event.getItemStack().is(GROWN_DANDELION_ITEM)) {
                Player player = event.getEntity();
                Random r = new Random();
                ItemStackUtils.remove(player,GROWN_DANDELION_ITEM.asItem(),1);
                Inventory i = player.getInventory();
                i.clearOrCountMatchingItems(p -> GROWN_DANDELION_ITEM.asItem() == p.getItem(),1, player.inventoryMenu.getCraftSlots());
                ServerLevel sl = (ServerLevel) player.level();
                sl.sendParticles(
                        DANDELION_SEED.get(),
                        player.getX(),
                        player.getY()+1,
                        player.getZ(),
                        140,
                        0.7,
                        0.7,
                        0.7,
                        1
                );
                if(r.nextFloat() < 0.1) {
                    sl.sendParticles(
                            ParticleTypes.HAPPY_VILLAGER,
                            player.getX(),
                            player.getY()+1,
                            player.getZ(),
                            60,
                            0.3,
                            0.3,
                            0.3,
                            1
                    );
                    FortuneHelper.generateQualifiedFortune(1,player,player.level(),true);
                }
            }
        }

    }
}

