package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.data.FortuneRecord;
import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.items.BuzioConch;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.BedBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.ATTACHMENT_TYPES;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.data.DataAttachments.FORTUNE;
import static com.art5019.afrobrazilities.items.BuzioConch.BUZIO_CONCH;

@Mod(MODID)
@EventBusSubscriber
public class BuzioConchRightClicked {

    /*@SubscribeEvent
    private static void onBuzioConchRightClicked(PlayerInteractEvent.RightClickItem event) {
        if(!event.getLevel().isClientSide) {
            if(event.getItemStack().is(BUZIO_CONCH)) {
                Player player = event.getEntity();
                Random r = new Random();
                List<FortuneRecord> frs = player.getData(FORTUNE);
                FortuneRecord fr = null;
                if(!frs.isEmpty()) {
                    fr = player.getData(FORTUNE).get(r.nextInt(0, frs.size() - 1));
                }
                if(fr == null) {
                    player.displayClientMessage(Component.literal("Your fate is unknown..."),true);
                    return;
                }
                player.displayClientMessage(Component.translatable(Fortunes.getById(fr.fortune()).getTk_name()).append(Component.literal(" will happen at day " + fr.day())),true);
            }
        }

    }*/
}
