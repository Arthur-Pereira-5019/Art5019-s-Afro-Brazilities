package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.utils.FortuneHelper;
import com.art5019.afrobrazilities.data.Fortunes;
import com.art5019.afrobrazilities.utils.GuideHelper;
import com.art5019.afrobrazilities.utils.SpiritualHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.damages.DamageTypes.PAINFUL_DEATH;
import static com.art5019.afrobrazilities.damages.DamageTypes.PEACEFUL_DEATH;
import static net.minecraft.world.item.Items.*;
import static net.minecraft.world.item.enchantment.Enchantments.FIRE_ASPECT;

@Mod(MODID)
@EventBusSubscriber
public class PlayerTick {

    @SubscribeEvent
    private static void playerTicks(PlayerTickEvent.Post event) {
        Player p = event.getEntity();
        Level l = p.level();
        Random r = new Random();
        float sp = SpiritualHelper.testSpiritualProtection(p);
        float k =  SpiritualHelper.testKarma(p);
        if(!l.isClientSide) {
            ServerLevel sl = (ServerLevel) l;
            if(l.getGameTime() % 1000 == 0) {
                List<Fortunes> fortunesOfToday = FortuneHelper.fortunesToday(p);
                if (fortunesOfToday.contains(Fortunes.JUDGEMENT)) {
                    SpiritualHelper.addAse(p, (int) k / 10);
                    l.playSound(null, p.blockPosition(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.MASTER, 2F, 1);
                    FortuneHelper.removeFortuneOfToday(p, Fortunes.JUDGEMENT);
                } else if (fortunesOfToday.contains(Fortunes.DEATH)) {
                    if (r.nextInt(0, 10) < 10 / (sp + 1)) {
                        if (k >= 0) {
                            p.hurtServer(sl, l.damageSources().source(PEACEFUL_DEATH), 20 - sp * 2);
                        } else {
                            p.hurtServer(sl, l.damageSources().source(PAINFUL_DEATH), 20 - sp * 2);
                        }
                    }
                } else if(fortunesOfToday.contains(Fortunes.STRONG_ENEMY)) {
                    strongEnemy(p, sl, l);
                }
                if(fortunesOfToday.contains(Fortunes.MESSAGE_FROM_YOUR_GUIDES)) {
                    GuideHelper.messageProvider(p);
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.MESSAGE_FROM_YOUR_GUIDES);
                } else if(fortunesOfToday.contains(Fortunes.LUCK_INCREASE)) {
                    p.addEffect(new MobEffectInstance(MobEffects.LUCK, 24000, 0));
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.LUCK_INCREASE);
                } else if(fortunesOfToday.contains(Fortunes.UNLUCK_INCREASE)) {
                    p.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 24000, 0));
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.UNLUCK_INCREASE);
                } else if(fortunesOfToday.contains(Fortunes.EXTRA_PROFITS)) {
                    p.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 24000, 0));
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.EXTRA_PROFITS);
                } else if(fortunesOfToday.contains(Fortunes.NOTHING)) {
                    FortuneHelper.removeFortuneOfToday(p,Fortunes.NOTHING);
                }
            }
        }

    }

    private static void strongEnemy(Player p, ServerLevel sl, Level l) {
        boolean spawned = false;
        ChunkPos chunkpos = p.chunkPosition();
        int i = chunkpos.getMinBlockX()+16;
        int j = chunkpos.getMinBlockZ()+16;
        if(sl.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
            if (sl.dimension() == Level.OVERWORLD) {
                if(l.getDayTime()%24000 > 12000) {
                    for (int t = 0; t < 10; t++) {
                        SkeletonHorse skeletonhorse = EntityType.SKELETON_HORSE.create(sl, EntitySpawnReason.EVENT);
                        Zombie zombie = EntityType.ZOMBIE.create(sl, EntitySpawnReason.EVENT);
                        Drowned drowned = EntityType.DROWNED.create(sl, EntitySpawnReason.EVENT);

                        BlockPos b = sl.getBlockRandomPos(i, sl.getHeight(Heightmap.Types.WORLD_SURFACE,i,j),j,0);
                        Vec3 v3 = new Vec3(b.getX(), sl.getHeight(Heightmap.Types.WORLD_SURFACE,b.getX(),b.getZ()),b.getZ());

                        skeletonhorse.setPos(v3);
                        zombie.setPos(v3);
                        drowned.setPos(v3);
                        if (sl.isThundering() && EventHooks.checkSpawnPosition(skeletonhorse, sl, EntitySpawnReason.EVENT)) {
                            skeletonhorse.setTrap(true);
                            skeletonhorse.setAge(0);
                            sl.addFreshEntity(skeletonhorse);
                            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(sl, EntitySpawnReason.EVENT);
                            if (lightningbolt != null) {
                                lightningbolt.snapTo(Vec3.atBottomCenterOf(new Vec3i((int) v3.x, (int) v3.y, (int) v3.z)));
                                sl.addFreshEntity(lightningbolt);
                            }
                            spawned = true;
                            break;
                        } else if (sl.getBiome(b).is(BiomeTags.IS_OCEAN) && EventHooks.checkSpawnPosition(drowned, sl, EntitySpawnReason.EVENT)) {
                            Dolphin dolphin = EntityType.DOLPHIN.create(sl, EntitySpawnReason.EVENT);
                            dolphin.setPos(v3);
                            drowned.setItemSlot(EquipmentSlot.CHEST, new ItemStack(IRON_CHESTPLATE));
                            drowned.setItemSlot(EquipmentSlot.LEGS, new ItemStack(IRON_LEGGINGS));
                            drowned.setItemSlot(EquipmentSlot.FEET, new ItemStack(IRON_BOOTS));
                            drowned.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(TRIDENT));
                            drowned.setDropChance(EquipmentSlot.MAINHAND, 0);
                            drowned.setDropChance(EquipmentSlot.CHEST, 0);
                            drowned.setDropChance(EquipmentSlot.LEGS, 0);
                            drowned.setDropChance(EquipmentSlot.FEET, 0);
                            drowned.setPersistenceRequired();
                            dolphin.setPersistenceRequired();
                            sl.addFreshEntity(zombie);
                            sl.addFreshEntity(dolphin);
                            drowned.startRiding(dolphin,true);
                            spawned = true;
                            break;
                        } else if (EventHooks.checkSpawnPosition(zombie, sl, EntitySpawnReason.EVENT)) {
                            zombie.setItemSlot(EquipmentSlot.HEAD, new ItemStack(DIAMOND_HELMET));
                            zombie.setItemSlot(EquipmentSlot.CHEST, new ItemStack(DIAMOND_CHESTPLATE));
                            zombie.setItemSlot(EquipmentSlot.LEGS, new ItemStack(DIAMOND_LEGGINGS));
                            zombie.setItemSlot(EquipmentSlot.FEET, new ItemStack(DIAMOND_BOOTS));
                            ItemStack weapon = new ItemStack(DIAMOND_SWORD);
                            weapon.enchant(sl.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS), 3);
                            zombie.setItemSlot(EquipmentSlot.MAINHAND, weapon);
                            zombie.setDropChance(EquipmentSlot.MAINHAND, 0);
                            zombie.setDropChance(EquipmentSlot.HEAD, 0);
                            zombie.setDropChance(EquipmentSlot.CHEST, 0);
                            zombie.setDropChance(EquipmentSlot.LEGS, 0);
                            zombie.setDropChance(EquipmentSlot.FEET, 0);
                            zombie.setPersistenceRequired();
                            sl.addFreshEntity(zombie);
                            spawned = true;
                            break;
                        }
                    }
                }
            } else if (sl.dimension() == Level.NETHER) {
                BlockPos b = sl.getBlockRandomPos(i,(int) p.getY(),j,0);
                int radius = 6;
                for (BlockPos pos : BlockPos.betweenClosed(
                        b.offset(-radius, -radius, -radius),
                        b.offset(radius, radius, radius))) {
                    var state = sl.getBlockState(pos);
                    if (state.isAir()) {
                        PiglinBrute piglin = EntityType.PIGLIN_BRUTE.create(sl, EntitySpawnReason.EVENT);
                        piglin.setPos(pos.getX(),pos.getY(),pos.getZ());
                        if (EventHooks.checkSpawnPosition(piglin, sl, EntitySpawnReason.EVENT)) {
                            piglin.setItemSlot(EquipmentSlot.LEGS, new ItemStack(NETHERITE_LEGGINGS));
                            ItemStack weapon = new ItemStack(NETHERITE_AXE);
                            weapon.enchant(sl.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.THORNS), 3);
                            piglin.setItemSlot(EquipmentSlot.MAINHAND, weapon);
                            ItemStack plate = new ItemStack(GOLDEN_CHESTPLATE);
                            plate.enchant(sl.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT), 3);
                            piglin.setItemSlot(EquipmentSlot.CHEST, plate);
                            ItemStack boots = new ItemStack(GOLDEN_BOOTS);
                            boots.enchant(sl.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SOUL_SPEED), 3);
                            piglin.setItemSlot(EquipmentSlot.FEET, boots);
                            piglin.setDropChance(EquipmentSlot.MAINHAND, 0);
                            piglin.setDropChance(EquipmentSlot.HEAD, 0);
                            piglin.setDropChance(EquipmentSlot.CHEST, 0);
                            piglin.setDropChance(EquipmentSlot.LEGS, 0);
                            piglin.setDropChance(EquipmentSlot.FEET, 0);
                            piglin.setPersistenceRequired();
                            sl.addFreshEntity(piglin);
                            spawned = true;
                            break;
                        }
                    }
                }
            } else if (sl.dimension() == Level.END) {
                EnderMan enderMan = EntityType.ENDERMAN.create(sl, EntitySpawnReason.EVENT);
                for (int t = 0; t < 10; t++) {
                    BlockPos b = sl.getBlockRandomPos(i, sl.getHeight(Heightmap.Types.WORLD_SURFACE,i,j),j,0);
                    Vec3 v3 = new Vec3(b.getX(), sl.getHeight(Heightmap.Types.WORLD_SURFACE,b.getX(),b.getZ()),b.getZ());
                    enderMan.setPos(v3);
                    if (EventHooks.checkSpawnPosition(enderMan, sl, EntitySpawnReason.EVENT)) {
                        enderMan.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 999999, 1));
                        enderMan.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 999999, 2));
                        enderMan.addEffect(new MobEffectInstance(MobEffects.SPEED, 999999, 2));
                        enderMan.setPersistenceRequired();
                        sl.addFreshEntity(enderMan);
                        enderMan.setTarget(p);
                        spawned = true;
                        break;
                    }
                }
            }
        }
        if(spawned) {
            FortuneHelper.removeFortuneOfToday(p,Fortunes.STRONG_ENEMY);
        }
    }

}
