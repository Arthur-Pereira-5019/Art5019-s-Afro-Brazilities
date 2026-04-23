package com.art5019.afrobrazilities.events;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.entity.schedule.ScheduleBuilder;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
@EventBusSubscriber(value = Dist.DEDICATED_SERVER)
public class PlayerAttacks {
    @SubscribeEvent()
    public static void playerAttacks(AttackEntityEvent event) {
        Entity t = event.getTarget();
        Level l = t.level();
        if(t instanceof LivingEntity) {
            ((LivingEntity) t).addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 0));
        }
        t.hurtServer((ServerLevel) l, new DamageSource(t.damageSources().damageTypes.getOrThrow(DamageTypes.MAGIC)),8);
    }
}
