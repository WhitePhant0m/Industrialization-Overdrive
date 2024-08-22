package dev.wp.ex_extended_industrialization.compat.mi;

import aztech.modern_industrialization.machines.recipe.MachineRecipeType;
import dev.wp.ex_extended_industrialization.ExEIBlocks;
import dev.wp.ex_extended_industrialization.ExEIItems;
import dev.wp.ex_extended_industrialization.ExEIOtherRegistries;
import dev.wp.ex_extended_industrialization.ExEISortOrder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.swedz.tesseract.neoforge.compat.mi.hook.MIHookRegistry;
import net.swedz.tesseract.neoforge.compat.mi.hook.TesseractMIHookEntrypoint;
import net.swedz.tesseract.neoforge.registry.SortOrder;
import net.swedz.tesseract.neoforge.registry.holder.BlockHolder;
import net.swedz.tesseract.neoforge.registry.holder.ItemHolder;

@TesseractMIHookEntrypoint
public final class ExEIMIHookRegistry implements MIHookRegistry {
    @Override
    public DeferredRegister.Blocks blockRegistry() {
        return ExEIBlocks.Registry.BLOCKS;
    }

    @Override
    public DeferredRegister<BlockEntityType<?>> blockEntityRegistry() {
        return ExEIBlocks.Registry.BLOCK_ENTITIES;
    }

    @Override
    public DeferredRegister.Items itemRegistry() {
        return ExEIItems.Registry.ITEMS;
    }

    @Override
    public DeferredRegister<RecipeSerializer<?>> recipeSerializerRegistry() {
        return ExEIOtherRegistries.RECIPE_SERIALIZERS;
    }

    @Override
    public DeferredRegister<RecipeType<?>> recipeTypeRegistry() {
        return ExEIOtherRegistries.RECIPE_TYPES;
    }

    @Override
    public void onBlockRegister(BlockHolder blockHolder) {
        ExEIBlocks.Registry.include(blockHolder);
    }

    @Override
    public void onBlockEntityRegister(BlockEntityType<?> blockEntityType) {

    }

    @Override
    public void onItemRegister(ItemHolder itemHolder) {
        ExEIItems.Registry.include(itemHolder);
    }

    @Override
    public void onMachineRecipeTypeRegister(MachineRecipeType machineRecipeType) {
    }

    @Override
    public SortOrder sortOrderMachines() {
        return ExEISortOrder.MACHINES;
    }
}
