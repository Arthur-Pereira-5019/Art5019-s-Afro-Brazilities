package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.entity_renderer.BabalorisaRenderer;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.entity_type.BabalorisaEntityType.BABALORISA_TYPE;

@Mod(MODID)
@EventBusSubscriber(value = Dist.CLIENT)
public class RegisterRenderer {

    public static final ModelLayerLocation BABALORISA = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "babalorisa"),
            "main"
    );

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BABALORISA_TYPE.get(), BabalorisaRenderer::new);
    }

    private static LayerDefinition renderVillager() {
        return LayerDefinition.create(VillagerModel.createBodyModel(), 64, 64);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BABALORISA, RegisterRenderer::renderVillager);
    }



}
