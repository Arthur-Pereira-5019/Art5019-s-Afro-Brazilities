package com.art5019.afrobrazilities.events;
import com.art5019.afrobrazilities.data.FortuneRecord;
import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.utils.FortuneHelper;
import com.art5019.afrobrazilities.utils.ItemStackUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.block.Simple_Plants.GROWN_DANDELION_ITEM;
import static com.art5019.afrobrazilities.data.DataAttachments.FORTUNE;
import static com.art5019.afrobrazilities.items.BuzioConch.BUZIO_CONCH;

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
                if(r.nextFloat() < 0.1) {
                    FortuneHelper.generateQualifiedFortune(1,player,player.level(),true);
                }
            }
        }

    }
}

