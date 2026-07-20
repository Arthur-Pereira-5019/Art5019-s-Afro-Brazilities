package com.art5019.afrobrazilities.events;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
@EventBusSubscriber
public class BuzioConchRightClicked {

    /*@SubscribeEvent
    private static void onBuzioConchRightClicked(PlayerInteractEvent.RightClickItem event) {
        if(!event.getLevel().isClientSide) {
            if(event.getItemStack().is(BUZIO_CONCH)) {
                Player player = event.getEntity();
                Random r = new Random();
                List<FortuneRecord> frs = player.getData(FORTUNE);
                FortuneRecord fr = null;
                if(!frs.isEmpty()) {
                    fr = player.getData(FORTUNE).get(r.nextInt(0, frs.size() - 1));
                }
                if(fr == null) {
                    player.displayClientMessage(Component.literal("Your fate is unknown..."),true);
                    return;
                }
                player.displayClientMessage(Component.translatable(Fortunes.getById(fr.fortune()).getTk_name()).append(Component.literal(" will happen at day " + fr.day())),true);
            }
        }

    }*/
}
