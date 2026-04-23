package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.utils.FortuneHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.io.IOException;
import java.util.List;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static net.minecraft.world.item.Items.*;

@Mod(MODID)
@EventBusSubscriber
public class PlayerFishes {
    @SubscribeEvent
    public static void playerFishes(ItemFishedEvent event) throws IOException {
        Player p = event.getEntity();
        Level level = p.level();
        FishingHook fh = event.getHookEntity();
        List<Fortunes> ft = FortuneHelper.fortunesToday(p);
        if(ft.contains(Fortunes.UNLUCK_FISHING)) {
            Drowned drowned = new Drowned(EntityType.DROWNED,level);
            drowned.setPos(fh.position());
            drowned.setItemSlot(EquipmentSlot.HEAD,new ItemStack(IRON_HELMET));
            drowned.setItemSlot(EquipmentSlot.CHEST,new ItemStack(IRON_CHESTPLATE));
            drowned.setItemSlot(EquipmentSlot.LEGS,new ItemStack(IRON_LEGGINGS));
            drowned.setItemSlot(EquipmentSlot.FEET,new ItemStack(IRON_BOOTS));
            drowned.setItemSlot(EquipmentSlot.MAINHAND,new ItemStack(TRIDENT));
            drowned.setDropChance(EquipmentSlot.MAINHAND,0);
            drowned.setPersistenceRequired();
            double d0 = p.getX() - fh.getX();
            double d1 = p.getY() - fh.getY();
            double d2 = p.getZ() - fh.getZ();
            drowned.setDeltaMovement(d0 * 0.1, d1 * 0.1 + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08, d2 * 0.1);
            level.addFreshEntity(drowned);
            FortuneHelper.removeFortuneOfToday(p,Fortunes.UNLUCK_FISHING);
        } else if(ft.contains(Fortunes.LUCK_FISHING)) {
            LootParams lootparams = new LootParams.Builder((ServerLevel) level)
                    .withParameter(LootContextParams.ORIGIN, fh.position())
                    .withParameter(LootContextParams.TOOL, p.getItemInHand(InteractionHand.MAIN_HAND))
                    .withParameter(LootContextParams.THIS_ENTITY, fh)
                    .withParameter(LootContextParams.ATTACKING_ENTITY, p)
                    .withLuck(7 + 3*p.getLuck())
                    .create(LootContextParamSets.FISHING);
            LootTable loottable = level.getServer().reloadableRegistries().getLootTable(BuiltInLootTables.FISHING);
            List<ItemStack> list = loottable.getRandomItems(lootparams);
            ItemEntity itementity = new ItemEntity(level,fh.getX(),fh.getY(),fh.getZ(),list.getFirst());
            double d0 = p.getX() - fh.getX();
            double d1 = p.getY() - fh.getY();
            double d2 = p.getZ() - fh.getZ();
            itementity.setDeltaMovement(d0 * 0.2, d1 * 0.2 + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.16, d2 * 0.2);
            level.addFreshEntity(itementity);
        }

    }
}
