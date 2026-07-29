package com.art5019.afrobrazilities.events;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.PistonEvent;

import java.util.List;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.block.Misc.SOY_FEED_CAULDRON;
import static com.art5019.afrobrazilities.block.Misc.SOY_OIL_CAULDRON;
import static com.art5019.afrobrazilities.block.Simple_Plants.SOYBEANS;
import static com.art5019.afrobrazilities.block.Simple_Plants.SOYBEANS_ITEM;
import static net.minecraft.world.level.block.Blocks.CAULDRON;

@Mod(MODID)
@EventBusSubscriber()
public class PistonEvents {
    @SubscribeEvent
    public static void pistonMoves(PistonEvent.Pre event) {
        if(event.getPistonMoveType() == PistonEvent.PistonMoveType.EXTEND) {
            LevelAccessor l = event.getLevel();
            if(!(l instanceof ServerLevel)) {
                return;
            }
            ServerLevel sl = (ServerLevel) l;
            BlockPos hitPos = event.getFaceOffsetPos().relative(event.getDirection());
            BlockPos fp = event.getFaceOffsetPos();
            if(!event.getLevel().getBlockState(hitPos).canBeReplaced()) {
                List<Entity> entities = sl.getEntities(null, new AABB(fp.getX(),fp.getY(),fp.getZ(),fp.getX()+1,fp.getY()+1,fp.getZ()+1));
                for(Entity e: entities) {
                    System.out.println(3);
                    if(e instanceof ItemEntity) {
                        System.out.println(4);
                        ItemStack item = ((ItemEntity) e).getItem();
                        if(item.is(SOYBEANS_ITEM)) {
                            System.out.println(5);
                            e.discard();
                            BlockPos cauldronPos = searchForCauldron(event.getFaceOffsetPos(),sl);
                            if(cauldronPos != null) {
                                System.out.println(6);
                                BlockState cauldron = sl.getBlockState(cauldronPos);
                                if(cauldron.is(CAULDRON)) {
                                    sl.setBlockAndUpdate(cauldronPos, SOY_FEED_CAULDRON.get().defaultBlockState());
                                } else if (cauldron.is(SOY_FEED_CAULDRON)) {
                                    cauldron.setValue(LayeredCauldronBlock.LEVEL,Math.min(cauldron.getValue(LayeredCauldronBlock.LEVEL)+1,3));
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    public static BlockPos searchForCauldron(BlockPos b, ServerLevel sl) {
        for (BlockPos pos : BlockPos.betweenClosed(
                b.offset(0, 0, 0),
                b.offset(0, -12, 0))) {
            var state = sl.getBlockState(pos);
            if(state.getBlock() instanceof AbstractCauldronBlock) {
                return pos;
            }
        }
        return null;
    }
}
