package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.worldgen.TerreiroPoolElement;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

import java.util.ArrayList;
import java.util.List;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
@EventBusSubscriber
public class ServerStarts {
    private static final ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_LIST_KEY = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath("minecraft", "empty"));

    @SubscribeEvent
    public static void serverStarts(ServerAboutToStartEvent e) {
        Registry<StructureTemplatePool> pools = e.getServer().registryAccess().lookupOrThrow(Registries.TEMPLATE_POOL);
        Registry<StructureProcessorList> processors = e.getServer().registryAccess().lookupOrThrow(Registries.PROCESSOR_LIST);

        //init(pools, processors);
    }


    public static void init(Registry<StructureTemplatePool> pools, Registry<StructureProcessorList> processors) {
        addVillagerHouse(pools, processors, "plains", 30);
    }

    private static void addVillagerHouse(Registry<StructureTemplatePool> pools, Registry<StructureProcessorList> processors, String biome, int weight) {
        addToJigsawPattern(pools, ResourceLocation.fromNamespaceAndPath("minecraft","village/" + biome + "/houses"), SinglePoolElement.single("art5019safrobrazilities:village/terreiro_" + biome + "_1").apply(StructureTemplatePool.Projection.RIGID),weight);
        addToJigsawPattern(pools, ResourceLocation.fromNamespaceAndPath("minecraft","village/" + biome + "/houses"), SinglePoolElement.single("art5019safrobrazilities:village/terreiro").apply(StructureTemplatePool.Projection.RIGID),weight);
    }

    public static void addToJigsawPattern(Registry<StructureTemplatePool> pools, ResourceLocation pool, StructurePoolElement newPiece, int weight) {
        StructureTemplatePool oldPool = pools.getValue(pool);
        if (oldPool != null) {
            List<StructurePoolElement> jigsawPieces = oldPool.templates;
            jigsawPieces.clear();


            for (int i = 0; i < weight; i++) {
                jigsawPieces.add(newPiece);
            }

            List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(oldPool.rawTemplates);
            listOfPieceEntries.add(new Pair<>(newPiece, weight));
            oldPool.rawTemplates = listOfPieceEntries;
            for (var g: oldPool.rawTemplates) {
                System.out.println(g);
            }
        }
    }
}
