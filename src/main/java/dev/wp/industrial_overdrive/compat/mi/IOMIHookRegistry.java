package dev.wp.industrial_overdrive.compat.mi;

import aztech.modern_industrialization.machines.recipe.MachineRecipeType;
import dev.wp.industrial_overdrive.IOBlocks;
import dev.wp.industrial_overdrive.IOItems;
import dev.wp.industrial_overdrive.IOOtherRegistries;
import dev.wp.industrial_overdrive.IOSortOrder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.swedz.tesseract.neoforge.compat.mi.hook.MIHookEntrypoint;
import net.swedz.tesseract.neoforge.compat.mi.hook.MIHookRegistry;
import net.swedz.tesseract.neoforge.registry.SortOrder;
import net.swedz.tesseract.neoforge.registry.holder.BlockHolder;
import net.swedz.tesseract.neoforge.registry.holder.ItemHolder;

@MIHookEntrypoint
public final class IOMIHookRegistry implements MIHookRegistry {
    @Override
    public DeferredRegister.Blocks blockRegistry() {
        return IOBlocks.Registry.BLOCKS;
    }

    @Override
    public DeferredRegister<BlockEntityType<?>> blockEntityRegistry() {
        return IOBlocks.Registry.BLOCK_ENTITIES;
    }

    @Override
    public DeferredRegister.Items itemRegistry() {
        return IOItems.Registry.ITEMS;
    }

    @Override
    public DeferredRegister<RecipeSerializer<?>> recipeSerializerRegistry() {
        return IOOtherRegistries.RECIPE_SERIALIZERS;
    }

    @Override
    public DeferredRegister<RecipeType<?>> recipeTypeRegistry() {
        return IOOtherRegistries.RECIPE_TYPES;
    }

    @Override
    public void onBlockRegister(BlockHolder blockHolder) {
        IOBlocks.Registry.include(blockHolder);
    }

    @Override
    public void onBlockEntityRegister(BlockEntityType<?> blockEntityType) {

    }

    @Override
    public void onItemRegister(ItemHolder itemHolder) {
        IOItems.Registry.include(itemHolder);
    }

    @Override
    public void onMachineRecipeTypeRegister(MachineRecipeType machineRecipeType) {
    }

    @Override
    public SortOrder sortOrderMachines() {
        return IOSortOrder.MACHINES;
    }
}
