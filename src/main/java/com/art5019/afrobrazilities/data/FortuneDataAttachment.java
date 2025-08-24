package com.art5019.afrobrazilities.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.function.Supplier;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.ATTACHMENT_TYPES;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
public class FortuneDataAttachment implements IAttachmentSerializer<String> {
    String fortune;

    //TODO: Learn how to use the IOSerializer to replace the use of codecs
    public static final Supplier<AttachmentType<FortuneRecord>> FORTUNE = ATTACHMENT_TYPES.register(
            "fortune",
            () -> AttachmentType.builder(() -> "Live long and happily")
                    .serialize(FORTUNE_CODEC.fieldOf("fortune")).build()
    );

    @Override
    public String read(IAttachmentHolder iAttachmentHolder, ValueInput valueInput) {
        return fortune;
    }

    @Override
    public boolean write(String s, ValueOutput valueOutput) {
        fortune = s;
        return true;
    }

    public static final Codec<FortuneRecord> FORTUNE_CODEC = RecordCodecBuilder.create(instance -> // Given an instance
            instance.group( // Define the fields within the instance
                    Codec.STRING.fieldOf("s").forGetter(FortuneRecord::fortune), // String
                    Codec.INT.optionalFieldOf("i", 0).forGetter(FortuneRecord::day) // Integer, defaults to 0 if field not present
            ).apply(instance, FortuneRecord::new) // Define how to create the object
    );
}
