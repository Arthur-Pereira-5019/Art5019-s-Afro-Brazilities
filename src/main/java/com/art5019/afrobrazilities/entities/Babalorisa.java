package com.art5019.afrobrazilities.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
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

}


