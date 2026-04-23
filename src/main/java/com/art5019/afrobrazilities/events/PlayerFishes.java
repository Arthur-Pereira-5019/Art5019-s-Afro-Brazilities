package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.utils.FortuneHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.io.IOException;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static net.minecraft.world.item.Items.DIAMOND;

@Mod(MODID)
@EventBusSubscriber
public class PlayerFishes {
    @SubscribeEvent
    public static void playerFishes(ItemFishedEvent event) throws IOException {
        Player p = event.getEntity();
        Level level = p.level();
        if(FortuneHelper.fortunesToday(p).contains(Fortunes.LUCK_FISHING)) {
            ItemStack i = new ItemStack(DIAMOND);
            FishingHook fh = event.getHookEntity();
            ItemEntity itementity = new ItemEntity(level,fh.getX(),fh.getY(),fh.getZ(),i);
            double d0 = p.getX() - fh.getX();
            double d1 = p.getY() - fh.getY();
            double d2 = p.getZ() - fh.getZ();
            itementity.setDeltaMovement(d0 * 0.1, d1 * 0.1 + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08, d2 * 0.1);
            level.addFreshEntity(itementity);

        }

    }
}
