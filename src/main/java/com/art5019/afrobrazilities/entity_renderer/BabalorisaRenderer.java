package com.art5019.afrobrazilities.entity_renderer;


import com.art5019.afrobrazilities.entities.Babalorisa;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

public class BabalorisaRenderer extends AgeableMobRenderer<Babalorisa, VillagerRenderState, VillagerModel> {
    public BabalorisaRenderer(EntityRendererProvider.Context context) {
        super(context, new VillagerModel(context.bakeLayer(ModelLayers.VILLAGER)), new VillagerModel(context.bakeLayer(ModelLayers.VILLAGER_BABY)), 0.5F);
    }

    @Override
    public VillagerRenderState createRenderState() {
        return new VillagerRenderState();
    }

    @Override
    public void extractRenderState(Babalorisa entity, VillagerRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
    }


    @Override
    public void render(VillagerRenderState state, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(state, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(VillagerRenderState villagerRenderState) {
        return ResourceLocation.fromNamespaceAndPath(MODID, "textures/entity/babalorisa.png");
    }
}