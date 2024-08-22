package dev.wp.ex_extended_industrialization.datagen.server.provider.recipes;

import dev.wp.ex_extended_industrialization.ExEI;
import dev.wp.ex_extended_industrialization.datagen.api.recipe.ShapedRecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.function.Consumer;

public final class CommonRecipesServerDatagenProvider extends RecipesServerDatagenProvider {
    public CommonRecipesServerDatagenProvider(GatherDataEvent event) {
        super(event);
    }

    private static void addBasicCraftingRecipes(String path, String name, boolean assembler, ItemLike result, int resultCount, Consumer<ShapedRecipeBuilder> crafting, RecipeOutput output) {
        ShapedRecipeBuilder shapedRecipeBuilder = new ShapedRecipeBuilder();
        crafting.accept(shapedRecipeBuilder);
        shapedRecipeBuilder.setOutput(result, resultCount);
        shapedRecipeBuilder.offerTo(output, ExEI.id(path + "/craft/" + name));

        if (assembler) {
            shapedRecipeBuilder.exportToAssembler().offerTo(output, ExEI.id(path + "/assembler/" + name));
        }
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
    }
}
