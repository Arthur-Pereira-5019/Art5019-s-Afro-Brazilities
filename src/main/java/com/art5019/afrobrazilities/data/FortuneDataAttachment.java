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
public class FortuneDataAttachment implements IAttachmentSerializer<List<FortuneRecord>>{

    ArrayList<FortuneRecord> fortunes = new ArrayList<>();

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


    @Override
    public List<FortuneRecord> read(IAttachmentHolder iAttachmentHolder, ValueInput valueInput) {
        LOGGER.warn("Called");
        if(fortunes.isEmpty()) {
            fortunes.add(new FortuneRecord(7,3,false));
            LOGGER.warn("Called");
        }
        return fortunes;
    }

    @Override
    public boolean write(List<FortuneRecord> fortuneRecords, ValueOutput valueOutput) {
        fortunes.addAll(fortuneRecords);
        return false;
    }
}
