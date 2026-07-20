package com.art5019.afrobrazilities.recipes;

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
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

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


