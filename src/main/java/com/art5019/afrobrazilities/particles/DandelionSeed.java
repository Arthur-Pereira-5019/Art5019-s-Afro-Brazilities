package com.art5019.afrobrazilities.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.SuspendedParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.common.Mod;

import java.util.function.Supplier;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.PARTICLE_TYPES;

public class DandelionSeed extends SuspendedParticle {
    private final SpriteSet spriteSet;

    public DandelionSeed(ClientLevel level, double x, double y, double z, SpriteSet spriteSet) {
        super(level, spriteSet, x, y, z, 0, 0, 0);
        this.spriteSet = spriteSet;
        this.gravity = 0.01F;

        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void move(double x, double y, double z) {
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
}
