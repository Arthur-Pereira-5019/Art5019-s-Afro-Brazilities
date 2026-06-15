package com.art5019.afrobrazilities.particles;

import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.fml.common.Mod;

import java.util.function.Supplier;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.PARTICLE_TYPES;

@Mod(MODID)
public class ParticleTypes {
    public static final Supplier<SimpleParticleType> DANDELION_SEED = PARTICLE_TYPES.register(
            "dandelion_seed",
            () -> new SimpleParticleType(true)
    );
}
