package com.art5019.afrobrazilities.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TerreiroPoolElement extends SinglePoolElement {
    public static final Codec<TerreiroPoolElement> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(templateCodec(), processorsCodec()).apply(instance, TerreiroPoolElement::new);
    });

    public TerreiroPoolElement(Either<ResourceLocation, StructureTemplate> template, Holder<StructureProcessorList> processors) {
        super(template, processors, StructureTemplatePool.Projection.RIGID, Optional.of(LiquidSettings.IGNORE_WATERLOGGING));
    }

    @Override
    public void handleDataMarker(LevelAccessor level, StructureTemplate.StructureBlockInfo info, BlockPos pos, Rotation rotation, RandomSource random, BoundingBox box) {
        /*
        if (info.nbt() != null) {
            if (StructureMode.valueOf(info.nbt().getString("mode")) == StructureMode.DATA) {
                String marker = info.nbt().getString("metadata");

                if ("apiary".equals(marker)) {
                    replaceWithApiary(level, info, random);
                }
            }
        }*/
    }

    @Override
    protected StructurePlaceSettings getSettings(Rotation rotation, BoundingBox bounds, LiquidSettings liquidSettings, boolean keepJigsaws) {
        return super.getSettings(rotation, bounds, liquidSettings, keepJigsaws)
                .popProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
    }

    /*
    private static void replaceWithApiary(LevelAccessor level, StructureTemplate.StructureBlockInfo info, RandomSource random) {
        BlockPos markerPos = info.pos();

        // remove the block entity of the Data block beforehand so that its NBT doesn't overwrite the apiary
        level.removeBlock(markerPos, false);
        level.setBlock(markerPos, ApicultureBlocks.BASE.get(BlockTypeApiculture.APIARY).defaultState(), Block.UPDATE_CLIENTS | Block.UPDATE_NEIGHBORS);

        // add a queen and some frames
        TileUtil.actOnTile(level, markerPos, TileApiary.class, apiary -> {
            ItemStack queen = chooseRandomVillageQueen(level, markerPos, random);

            apiary.setItem(InventoryBeeHousing.SLOT_QUEEN, queen);

            // this method gets called multiple times so having random number of frames is impossible :)))
            for (int i = 0; i < 3; ++i) {
                ItemStack frame = ApicultureItems.FRAME_PROVEN.stack();
                int maxDamage = frame.getMaxDamage();
                frame.setDamageValue(random.nextIntBetweenInclusive(maxDamage / 4, maxDamage - maxDamage / 4));
                apiary.setItem(InventoryApiary.SLOT_FRAMES_1 + i, frame);
            }
        });
    }*/

    /*@Override
    public StructurePoolElementType<?> getType() {
        return ApicultureFeatures.APIARIST_POOL_ELEMENT_TYPE.get();
    } */

}