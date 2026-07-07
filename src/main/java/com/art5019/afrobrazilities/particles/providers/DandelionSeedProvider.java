package com.art5019.afrobrazilities.particles.providers;

import com.art5019.afrobrazilities.particles.DandelionSeed;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.SuspendedParticle;
import net.minecraft.core.particles.ParticleGroup;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

import java.util.Optional;

public class DandelionSeedProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public DandelionSeedProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            SuspendedParticle suspendedparticle = new SuspendedParticle(level, this.spriteSet, x, y, z, (double)0.0F, (double)-0.8F, (double)0.0F) {
                public Optional<ParticleGroup> getParticleGroup() {
                    return Optional.of(ParticleGroup.SPORE_BLOSSOM);
                }
            };
            suspendedparticle.lifetime = Mth.randomBetweenInclusive(level.random, 500, 1000);
            suspendedparticle.gravity = 0.01F;
            return suspendedparticle;
        }
    }
