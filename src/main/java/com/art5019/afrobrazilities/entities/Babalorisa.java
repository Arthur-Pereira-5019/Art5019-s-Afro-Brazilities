package com.art5019.afrobrazilities.entities;

import com.art5019.afrobrazilities.data.FortuneRecord;
import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.utils.ItemStackUtils;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.Filterable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.data.DataAttachments.FORTUNE;
import static com.art5019.afrobrazilities.items.BuzioConch.BUZIO_CONCH;
import static net.minecraft.core.component.DataComponents.WRITTEN_BOOK_CONTENT;
import static net.minecraft.network.chat.Component.literal;
import static net.minecraft.world.item.Items.*;

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

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            boolean p1 = player.getInventory().contains(new ItemStack(WRITABLE_BOOK));
            boolean p2 = ItemStackUtils.is(player.getItemInHand(hand),BUZIO_CONCH.asItem(),16);
            boolean p3 = player.getInventory().contains(new ItemStack(EMERALD,28));
            if(!p2) {
                player.displayClientMessage(literal("[").
                                append(Component.translatable("art5019afrobrazilities.entity.babalorisa")).
                                append(literal("] ")).
                                append(Component.translatable("art5019afrobrazilities.entity.babalorisa.np2")),
                        false);
                playSound(SoundEvents.VILLAGER_NO);
                return InteractionResult.SUCCESS;
            }
            if(!p3) {
                player.displayClientMessage(literal("[").
                                append(Component.translatable("art5019afrobrazilities.entity.babalorisa")).
                                append(literal("] ")).
                                append(Component.translatable("art5019afrobrazilities.entity.babalorisa.np3")),
                        false);
                playSound(SoundEvents.VILLAGER_NO);
                return InteractionResult.SUCCESS;
            }
            if(!p1) {
                player.displayClientMessage(literal("[").
                        append(Component.translatable("art5019afrobrazilities.entity.babalorisa")).
                        append(literal("] ")).
                        append(Component.translatable("art5019afrobrazilities.entity.babalorisa.np1")),
                        false);
                playSound(SoundEvents.VILLAGER_NO);
                return InteractionResult.SUCCESS;
            }
            Inventory i = player.getInventory();
            i.clearOrCountMatchingItems(p -> WRITABLE_BOOK == p.getItem(),1, player.inventoryMenu.getCraftSlots());
            i.clearOrCountMatchingItems(p -> BUZIO_CONCH.asItem() == p.getItem(),16, player.inventoryMenu.getCraftSlots());
            i.clearOrCountMatchingItems(p -> EMERALD == p.getItem(),28, player.inventoryMenu.getCraftSlots());

            ArrayList<Filterable<Component>> pages = new ArrayList<>();
            List<FortuneRecord> frs = player.getData(FORTUNE);
            for(FortuneRecord fr: frs) {
                pages.add(Filterable.passThrough(Component.translatable(Fortunes.getById(fr.fortune()).getTk_complete(),fr.day())));
            }
            ItemStack newBook = new ItemStack(WRITTEN_BOOK,1);
            newBook.set(WRITTEN_BOOK_CONTENT, new WrittenBookContent(Filterable.passThrough("Messages from the other side"),"???",0,pages,true));
            player.addItem(newBook);

        }
        return InteractionResult.SUCCESS;
    }
}


