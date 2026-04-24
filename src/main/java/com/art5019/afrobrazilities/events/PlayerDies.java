package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.utils.FortuneHelper;
import net.minecraft.world.damagesource.DeathMessageType;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import java.util.List;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
@EventBusSubscriber
public class PlayerDies {
    @SubscribeEvent
    public static void playerDies(LivingDeathEvent event) {
        LivingEntity e = event.getEntity();
        if(e instanceof Player) {
            List<Fortunes> fortunesList = FortuneHelper.fortunesToday((Player) e);
            if(fortunesList.contains(Fortunes.DEATH)) {
                FortuneHelper.removeFortuneOfToday((Player) e, Fortunes.DEATH);
                FortuneHelper.generateFortunes(1,(Player) e,e.level());
            }
        }
    }
}
