package com.art5019.afrobrazilities.recipes;

import com.art5019.afrobrazilities.block.Orchid;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static com.art5019.afrobrazilities.block.Simple_Plants.ORCHIDS;
import static com.art5019.afrobrazilities.items.Items.BASE_DYE;
import static net.minecraft.world.item.Items.*;

public class MyRecipeProvider extends RecipeProvider {

    protected MyRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
    }

    @Override
    protected void buildRecipes() {
        List<Item> dyes = List.of(RED_DYE,BLUE_DYE,YELLOW_DYE,MAGENTA_DYE,BROWN_DYE,WHITE_DYE,BLACK_DYE,ORANGE_DYE,
                PURPLE_DYE,LIME_DYE,CYAN_DYE,LIGHT_BLUE_DYE,GRAY_DYE,LIGHT_GRAY_DYE,PINK_DYE);
        /*
        Stream<Holder<Item>> itemsInTag = this.registries.lookupOrThrow(BuiltInRegistries.ITEM.key()).getOrThrow(Tags.Items.DYES).stream();
       itemsInTag.toList();*/
        for(Item item: dyes) {
            ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, new ItemStack(item,3)).requires(item).requires(BASE_DYE).group("base_dye_duplication").unlockedBy("has_base_dye", this.has(BASE_DYE)).save(this.output);
        }
        for(DeferredBlock<Orchid> block: ORCHIDS) {
            Item i = block.asItem();
            ItemLike dye = block.get().getDye();
            System.out.println(i.toString());
            ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, new ItemStack(dye,2)).
                    requires(i).group(i.toString().split(":")[1]).unlockedBy("has_orchid", this.has(i)).save(this.output,dye.asItem().toString().split(":")[1]+"_from_"+i.toString().split(":")[1]);
        }
    }


    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new MyRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "";
        }
    }
}


