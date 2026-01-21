package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.data.FortuneDataAttachment;
import com.art5019.afrobrazilities.data.FortuneRecord;
import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.items.BuzioConch;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.LOGGER;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.data.FortuneDataAttachment.*;

@Mod(MODID)
@EventBusSubscriber
public class PlayerJoins {
    @SubscribeEvent
    private static void playerLogs(PlayerEvent.PlayerLoggedInEvent event) {
        LivingEntity entity = event.getEntity();
        List<FortuneRecord> fortunes = entity.getData(FORTUNE);
        if()
    }


}
