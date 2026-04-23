package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.utils.FortuneHelper;
import com.art5019.afrobrazilities.data.FortuneRecord;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.List;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.data.DataAttachments.FORTUNE;

@Mod(MODID)
@EventBusSubscriber
public class PlayerJoins {

    @SubscribeEvent
    private static void playerLogs(PlayerEvent.PlayerLoggedInEvent event) {
        Player entity = event.getEntity();
        List<FortuneRecord> fortunes = entity.getData(FORTUNE);
        if(fortunes.isEmpty() || fortunes.size() < 10) {
            FortuneHelper.generateFortunes(10,entity,event.getEntity().level());
        }
    }


}
