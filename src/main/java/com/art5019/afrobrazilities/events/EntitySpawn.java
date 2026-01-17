package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.data.FortuneDataAttachment;
import com.art5019.afrobrazilities.data.FortuneRecord;
import com.art5019.afrobrazilities.items.BuzioConch;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.LOGGER;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.data.FortuneDataAttachment.*;

@Mod(MODID)
@EventBusSubscriber
public class EntitySpawn {
    @SubscribeEvent
    private static void onEntitySpawn(FinalizeSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Drowned) {
            entity.getData(FORTUNE);
            List<FortuneRecord> a = new ArrayList<>();
            a.add(new FortuneRecord("Death",10));

            entity.setData(FORTUNE, a);
            String drownedFortune = entity.getData(FORTUNE).get(0).fortune();
            LOGGER.warn(drownedFortune);

            a = entity.getData(FORTUNE);
            a.add(new FortuneRecord("Death",12));

            entity.setData(FORTUNE, a);
            drownedFortune = entity.getData(FORTUNE).get(1).fortune();
            LOGGER.warn(drownedFortune);
            if(!event.getLevel().isClientSide()) {
                Random r = new Random();
                if(r.nextInt(1,10) < 4) {
                    entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(BuzioConch.BUZIO_CONCH.asItem()));
                }
            }
        }
    }
}
