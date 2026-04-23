package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.utils.FortuneHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.entity.schedule.ScheduleBuilder;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
@EventBusSubscriber()
public class PlayerAttacks {
    @SubscribeEvent
    public static void entityIsHurt(LivingIncomingDamageEvent event) {
        Entity t = event.getEntity();
        Level l = t.level();
        DamageContainer container = event.getContainer();
        DamageSource source = container.getSource();
        if(source.getEntity() != null && t instanceof Monster) {
            Entity e = source.getEntity();
            if(e instanceof Player) {
                List<Fortunes> f = FortuneHelper.fortunesToday((Player) e);
                if(f.contains(Fortunes.LUCK_FIGHT)) {
                    Random r = new Random();
                    if(r.nextInt(0,10) > 8) {
                        container.setNewDamage(container.getOriginalDamage() + 14);
                        if(!l.isClientSide) {
                            ServerLevel sl = (ServerLevel) l;
                            sl.sendParticles(
                                    ParticleTypes.ENCHANTED_HIT,
                                    t.getX(),
                                    t.getY(),
                                    t.getZ(),
                                    30,
                                    0.1,
                                    0.1,
                                    0.1,
                                    1
                            );
                        }
                        FortuneHelper.removeFortuneOfToday((Player) e,Fortunes.LUCK_FIGHT);
                    }
                } else if (f.contains(Fortunes.UNLUCK_FIGHT)) {
                    Random r = new Random();
                    if(r.nextInt(0,10) > 8) {
                        float oldDamage = container.getOriginalDamage();
                        container.setNewDamage(0);
                        if(!l.isClientSide) {
                            ServerLevel sl = (ServerLevel) l;
                            e.hurtServer(sl,sl.damageSources().magic(),oldDamage/3);
                            sl.playLocalSound(e.blockPosition(), SoundEvents.EGG_THROW, SoundSource.PLAYERS,0.8F,1,false);
                            sl.sendParticles(
                                    ParticleTypes.GUST,
                                    e.getX(),
                                    e.getY(),
                                    e.getZ(),
                                    5,
                                    0.1,
                                    0.1,
                                    0.1,
                                    1
                            );
                        }
                        FortuneHelper.removeFortuneOfToday((Player) e,Fortunes.UNLUCK_FIGHT);
                    }
            }
        }
            }
    }
}
