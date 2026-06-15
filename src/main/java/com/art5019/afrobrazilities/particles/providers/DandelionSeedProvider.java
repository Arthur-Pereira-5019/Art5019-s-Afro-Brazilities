package com.art5019.afrobrazilities.particles.providers;

import com.art5019.afrobrazilities.particles.DandelionSeed;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public class DandelionSeedProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public DandelionSeedProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new DandelionSeed(level, x, y, z, spriteSet);
        }
    }
