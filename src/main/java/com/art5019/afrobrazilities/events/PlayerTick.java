package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.data.FortuneHelper;
import com.art5019.afrobrazilities.data.Fortunes;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
@EventBusSubscriber
public class PlayerTick {

    @SubscribeEvent
    private static void playerTicks(PlayerTickEvent.Post event) {
        Player p = event.getEntity();
        Level l = p.level();
        if(!l.isClientSide) {
            if(l.getGameTime() % 1000 == 0) {
                List<Fortunes> fortunesOfToday = FortuneHelper.fortunesToday(p);
                //fortunesOfToday.forEach(System.out::println);
                if(fortunesOfToday.contains(Fortunes.LUCK_INCREASE)) {
                    p.addEffect(new MobEffectInstance(MobEffects.LUCK, 24000, 0));
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.LUCK_INCREASE);
                } else if(fortunesOfToday.contains(Fortunes.UNLUCK_INCREASE)) {
                    p.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 24000, 0));
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.UNLUCK_INCREASE);
                } else if(fortunesOfToday.contains(Fortunes.EXTRA_PROFITS)) {
                    p.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 24000, 0));
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.EXTRA_PROFITS);
                } else if(fortunesOfToday.contains(Fortunes.LUCK_FIGHT)) {
                    p.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 24000, 0));
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.LUCK_FIGHT);
                } else if(fortunesOfToday.contains(Fortunes.NOTHING)) {
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.NOTHING);
                }
            }
        }

    }
}
