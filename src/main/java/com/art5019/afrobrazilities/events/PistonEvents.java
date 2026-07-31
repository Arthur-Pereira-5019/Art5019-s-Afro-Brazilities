package com.art5019.afrobrazilities.events;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.PistonEvent;

import java.util.List;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.block.Misc.SOY_FEED_CAULDRON;
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
                    if(e instanceof ItemEntity) {
                        ItemStack item = ((ItemEntity) e).getItem();
                        if(item.is(SOYBEANS_ITEM)) {
                            e.discard();
                            if(theresCauldron(event.getFaceOffsetPos().below(),sl)) {
                                BlockPos cauldronPos = event.getFaceOffsetPos().below();
                                float chanceModifier = item.getCount()*0.005F;
                                float chance = sl.random.nextFloat();
                                int power = 0;
                                if(chance > 1-chanceModifier*1) {
                                    power = 3;
                                } else if (chance > 1-chanceModifier*2) {
                                    power = 2;
                                } else if (chance > 1-chanceModifier*3) {
                                    power = 1;
                                }
                                BlockState cauldron = sl.getBlockState(cauldronPos);
                                if(cauldron.is(CAULDRON) && power > 0) {
                                    sl.setBlockAndUpdate(cauldronPos, SOY_FEED_CAULDRON.get().defaultBlockState());
                                    power-=1;
                                }
                                if (cauldron.is(SOY_FEED_CAULDRON) && power > 0) {
                                    BlockState newState = sl.getBlockState(cauldronPos).setValue(LayeredCauldronBlock.LEVEL,Math.min(cauldron.getValue(LayeredCauldronBlock.LEVEL)+power,3));
                                    sl.setBlockAndUpdate(cauldronPos, newState);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean theresCauldron(BlockPos b, ServerLevel sl) {
        var state = sl.getBlockState(b);
        if(state.is(SOY_FEED_CAULDRON) || state.is(CAULDRON)) {
            return true;
        }
        return false;
    }
}
