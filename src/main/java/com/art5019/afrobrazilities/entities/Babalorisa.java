package com.art5019.afrobrazilities.entities;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.VillagerGoalPackages;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

public class Babalorisa extends AbstractVillager {


    public Babalorisa(EntityType<? extends Babalorisa> type, Level level) {
        super(type, level);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {}

    @Override
    protected void updateTrades() {

    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {}


    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    protected void rewardTradeXp(MerchantOffer merchantOffer) {

    }


    @Override
    public boolean hurtServer(ServerLevel level, DamageSource damageSource, float amount) {
        return true;
    }

    @Override
    public boolean hurtClient(DamageSource damageSource) {
        return true;
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zombie.class, 8.0F, 1.0, 1.2));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isSleeping()) {
            return null;
        } else {
            return this.isTrading() ? SoundEvents.VILLAGER_TRADE : SoundEvents.VILLAGER_AMBIENT;
        }
    }
}


