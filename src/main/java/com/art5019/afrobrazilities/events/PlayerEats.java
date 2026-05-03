package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.utils.FortuneHelper;
import com.ibm.icu.util.EasterHoliday;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
@EventBusSubscriber
public class PlayerEats {
    @SubscribeEvent
    public static void playerEats(LivingEntityUseItemEvent.Finish e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getItem().has(DataComponents.FOOD)) {
                if (FortuneHelper.fortunesToday(p).contains(Fortunes.BETRAYAL))
                    p.addEffect(new MobEffectInstance(MobEffects.WITHER, 340, 2));
                FortuneHelper.removeFortuneOfToday(p,Fortunes.BETRAYAL);
            }
        }
    }
}
