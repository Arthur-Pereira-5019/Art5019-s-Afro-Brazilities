package com.art5019.afrobrazilities.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import static com.art5019.afrobrazilities.block.Simple_Plants.BEANS;
import static com.art5019.afrobrazilities.block.Simple_Plants.BEANS_ITEM;

public class BarteringModifier extends LootModifier {
    public static final MapCodec<BarteringModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).apply(inst, BarteringModifier::new)
    );

    public BarteringModifier(LootItemCondition[] conditions) {
        super(conditions);
    }
    
    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if(context.getRandom().nextFloat() < 0.04) {
            ItemStack beans = new ItemStack(BEANS_ITEM.asItem(),2);
            generatedLoot = new ObjectArrayList<>();
            generatedLoot.add(beans);
        }
        return generatedLoot;
    }
}


