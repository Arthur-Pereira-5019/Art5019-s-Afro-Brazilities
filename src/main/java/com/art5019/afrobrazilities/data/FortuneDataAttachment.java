package com.art5019.afrobrazilities.data;

import com.mojang.serialization.Codec;
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
    //TODO: Learn how to use the IOSerializer to replace the use of codecs
    private static final Supplier<AttachmentType<String>> MANA = ATTACHMENT_TYPES.register(
            "fortune", () -> AttachmentType.builder(() -> "Live long and happily").serialize(Codec.STRING.fieldOf("fortune")).build()
    );

    @Override
    public String read(IAttachmentHolder iAttachmentHolder, ValueInput valueInput) {
        return "";
    }

    @Override
    public boolean write(String s, ValueOutput valueOutput) {
        return false;
    }
}
