package dev.wp.ex_extended_industrialization.datagen.server.provider.recipes;

import aztech.modern_industrialization.MIItem;
import dev.wp.ex_extended_industrialization.ExEI;
import dev.wp.ex_extended_industrialization.datagen.api.recipe.ShapedRecipeBuilder;
import dev.wp.ex_extended_industrialization.datagen.api.recipe.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.function.Consumer;

public final class MachineItemRecipesServerDatagenProvider extends RecipesServerDatagenProvider {
    public MachineItemRecipesServerDatagenProvider(GatherDataEvent event) {
        super(event);
    }

    private static String machine(String machine, String tier) {
        return "%s:%s".formatted(ExEI.ID, tier == null ? machine : "%s_%s".formatted(tier, machine));
    }

    private static String machineBronze(String machine) {
        return machine(machine, "bronze");
    }

    private static String machineSteel(String machine) {
        return machine(machine, "steel");
    }

    private static void addBasicCraftingMachineRecipes(String machineName, String machineTier, Consumer<ShapedRecipeBuilder> crafting, RecipeOutput output) {
        String recipeId = machineTier == null ? "" : "/%s".formatted(machineTier);

        ShapedRecipeBuilder shapedRecipeBuilder = new ShapedRecipeBuilder();
        crafting.accept(shapedRecipeBuilder);
        shapedRecipeBuilder.setOutput(machine(machineName, machineTier), 1);
        shapedRecipeBuilder.offerTo(output, ExEI.id("machines/%s/craft%s".formatted(machineName, recipeId)));

        shapedRecipeBuilder.exportToAssembler().offerTo(output, ExEI.id("machines/%s/assembler%s".formatted(machineName, recipeId)));
    }

    private static void addBasicCraftingMachineRecipes(String machineName, Consumer<ShapedRecipeBuilder> crafting, RecipeOutput output) {
        addBasicCraftingMachineRecipes(machineName, null, crafting, output);
    }

    private static void addBronzeMachineRecipes(String machine, Consumer<ShapedRecipeBuilder> crafting, RecipeOutput output) {
        addBasicCraftingMachineRecipes(machine, "bronze", crafting, output);
    }

    private static void addSteelMachineRecipes(String machine, Consumer<ShapedRecipeBuilder> crafting, RecipeOutput output) {
        addBasicCraftingMachineRecipes(machine, "steel", crafting, output);
    }

    private static void addElectricMachineRecipes(String machine, Consumer<ShapedRecipeBuilder> crafting, RecipeOutput output) {
        addBasicCraftingMachineRecipes(machine, "electric", crafting, output);
    }

    private static void addSteelUpgradeMachineRecipes(String machine, RecipeOutput output) {
        ShapelessRecipeBuilder shapelessRecipeBuilder = new ShapelessRecipeBuilder()
                .with(machineBronze(machine))
                .with(MIItem.STEEL_UPGRADE)
                .setOutput(machineSteel(machine), 1);
        shapelessRecipeBuilder.offerTo(output, ExEI.id("machines/%s/craft/upgrade_steel".formatted(machine)));

        shapelessRecipeBuilder.exportToPacker().offerTo(output, ExEI.id("machines/%s/packer/upgrade_steel".formatted(machine)));

        shapelessRecipeBuilder.exportToUnpackerAndFlip().offerTo(output, ExEI.id("machines/%s/unpacker/downgrade_steel".formatted(machine)));
    }

    private static void addBronzeAndSteelMachineRecipes(String machine, Consumer<ShapedRecipeBuilder> crafting, RecipeOutput output) {
        addBronzeMachineRecipes(machine, crafting, output);
        addSteelUpgradeMachineRecipes(machine, output);
    }

    private static void processingArray(RecipeOutput output) {
        addBasicCraftingMachineRecipes(
                "multi_processing_array",
                (builder) -> builder
                        .define('A', "modern_industrialization:robot_arm")
                        .define('D', "modern_industrialization:digital_circuit")
                        .define('C', "modern_industrialization:clean_stainless_steel_machine_casing")
                        .define('S', "modern_industrialization:assembler")
                        .pattern("ADA")
                        .pattern("CSC")
                        .pattern("ADA"),
                output
        );
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        processingArray(output);
    }
}
