package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.utils.FortuneHelper;
import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.utils.GuideHelper;
import com.art5019.afrobrazilities.utils.SpiritualHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.damages.DamageTypes.PAINFUL_DEATH;
import static com.art5019.afrobrazilities.damages.DamageTypes.PEACEFUL_DEATH;

@Mod(MODID)
@EventBusSubscriber
public class PlayerTick {

    @SubscribeEvent
    private static void playerTicks(PlayerTickEvent.Post event) {
        Player p = event.getEntity();
        Level l = p.level();
        Random r = new Random();
        float sp = SpiritualHelper.testSpiritualProtection(p);
        float k =  SpiritualHelper.testKarma(p);
        if(!l.isClientSide) {
            ServerLevel sl = (ServerLevel) l;
            if(l.getGameTime() % 1000 == 0) {
                List<Fortunes> fortunesOfToday = FortuneHelper.fortunesToday(p);
                if (fortunesOfToday.contains(Fortunes.JUDGEMENT)) {
                    SpiritualHelper.addAse(p, (int) k / 10);
                    l.playSound(null, p.blockPosition(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.MASTER, 2F, 1);
                    FortuneHelper.removeFortuneOfToday(p, Fortunes.JUDGEMENT);
                } else if (fortunesOfToday.contains(Fortunes.DEATH)) {
                    if (r.nextInt(0, 10) < 10 / (sp + 1)) {
                        if (k >= 0) {
                            p.hurtServer(sl, l.damageSources().source(PEACEFUL_DEATH), 20 - sp * 2);
                        } else {
                            p.hurtServer(sl, l.damageSources().source(PAINFUL_DEATH), 20 - sp * 2);
                        }
                    }
                }
                if(fortunesOfToday.contains(Fortunes.MESSAGE_FROM_YOUR_GUIDES)) {
                    GuideHelper.messageProvider(p);
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.LUCK_INCREASE);
                } else if(fortunesOfToday.contains(Fortunes.LUCK_INCREASE)) {
                    p.addEffect(new MobEffectInstance(MobEffects.LUCK, 24000, 0));
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.LUCK_INCREASE);
                } else if(fortunesOfToday.contains(Fortunes.UNLUCK_INCREASE)) {
                    p.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 24000, 0));
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.UNLUCK_INCREASE);
                } else if(fortunesOfToday.contains(Fortunes.EXTRA_PROFITS)) {
                    p.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 24000, 0));
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.EXTRA_PROFITS);
                } else if(fortunesOfToday.contains(Fortunes.NOTHING)) {
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.NOTHING);
                }
            }
        }

    }

}
