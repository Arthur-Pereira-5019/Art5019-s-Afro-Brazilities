package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.block.Simple_Plants;
import com.art5019.afrobrazilities.particles.DandelionSeed;
import com.art5019.afrobrazilities.particles.ParticleTypes;
import com.art5019.afrobrazilities.particles.providers.DandelionSeedProvider;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.PARTICLE_TYPES;

@Mod(MODID)
@EventBusSubscriber(value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void clientSetup(RegisterClientExtensionsEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Simple_Plants.GROWN_DANDELION.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(Simple_Plants.DANDELION_SPROUT.get(), ChunkSectionLayer.CUTOUT);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleTypes.DANDELION_SEED.get(), DandelionSeedProvider::new);
    }

}
