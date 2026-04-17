package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.entity_renderer.BabalorisaRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.entity_type.BabalorisaEntityType.BABALORISA_TYPE;

@Mod(MODID)
@EventBusSubscriber
public class RegisterRender {
    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BABALORISA_TYPE.get(), BabalorisaRenderer::new);
    }


}
