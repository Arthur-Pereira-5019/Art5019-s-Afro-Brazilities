package com.art5019.afrobrazilities.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.*;


@Mod(MODID)
public class DataAttachments {
    public static final Codec<FortuneRecord> FORTUNE_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("f").forGetter(FortuneRecord::fortune),
                    Codec.INT.fieldOf("d").forGetter(FortuneRecord::day),
                    Codec.BOOL.fieldOf("h").forGetter(FortuneRecord::happened)
            ).apply(instance, FortuneRecord::new)
    );

    public static final Codec<List<FortuneRecord>> FORTUNES = FORTUNE_CODEC.listOf();

    public static final Supplier<AttachmentType<List<FortuneRecord>>> FORTUNE = ATTACHMENT_TYPES.register(
            "fortune", () -> AttachmentType.builder((Supplier<List<FortuneRecord>>) ArrayList::new).
                    serialize(FORTUNES.fieldOf("fortune")).copyOnDeath().build()
    );

    public static final Supplier<AttachmentType<Integer>> KARMA = ATTACHMENT_TYPES.register(
            "karma", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT.fieldOf("karma")).build()
    );

    public static final Supplier<AttachmentType<Integer>> ASE = ATTACHMENT_TYPES.register(
            "ase", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT.fieldOf("ase")).build()
    );

    public static final Supplier<AttachmentType<Boolean>> PATCHED_TODAY = ATTACHMENT_TYPES.register(
            "patched_today", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL.fieldOf("patch")).build()
    );

}
