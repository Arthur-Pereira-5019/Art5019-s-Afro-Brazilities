package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.data.FortuneRecord;
import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.utils.FortuneHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.internal.NeoForgeBlockTagsProvider;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.List;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.data.DataAttachments.FORTUNE;
import static com.art5019.afrobrazilities.data.DataAttachments.FORTUNES;

@Mod(MODID)
@EventBusSubscriber
public class PlayerBreaksBlock {
    @SubscribeEvent
    public static void playerMines(BlockEvent.BreakEvent e) {
        Player p = e.getPlayer();
        Level l = p.level();
        BlockState b = e.getState();
        BlockPos bp = e.getPos();
        if(b.getTags().toList().contains(Tags.Blocks.ORES)) {
            LootParams.Builder lootparams = new LootParams.Builder((ServerLevel) l)
                    .withParameter(LootContextParams.ORIGIN, p.position())
                    .withParameter(LootContextParams.TOOL, new ItemStack(p.getItemInHand(InteractionHand.MAIN_HAND).getItem()))
                    .withParameter(LootContextParams.THIS_ENTITY, p)
                    .withLuck(p.getLuck());
            List<ItemStack> items = b.getDrops(lootparams);
            for(ItemStack i: items) {
                if(i.is(b.getBlock().asItem())) {
                    return;
                }
            }
            for(ItemStack i: items) {
                ItemEntity itementity = new ItemEntity(l,bp.getX(),bp.getY(),bp.getZ(),i);
                l.addFreshEntity(itementity);
            }
        }
    }
}
