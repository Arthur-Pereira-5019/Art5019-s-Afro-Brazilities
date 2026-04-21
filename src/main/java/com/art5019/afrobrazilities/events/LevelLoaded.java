package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.utils.FortuneHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.LevelEvent;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
@EventBusSubscriber
public class LevelLoaded {

    @SubscribeEvent
    private static void levelLoads(LevelEvent.Load event) {
        FortuneHelper.startBase();
    }


}
