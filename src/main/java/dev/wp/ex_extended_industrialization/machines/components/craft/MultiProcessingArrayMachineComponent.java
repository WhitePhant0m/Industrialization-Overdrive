package dev.wp.ex_extended_industrialization.machines.components.craft;

import aztech.modern_industrialization.machines.IComponent;
import aztech.modern_industrialization.machines.MachineBlockEntity;
import aztech.modern_industrialization.machines.components.DropableComponent;
import aztech.modern_industrialization.machines.recipe.MachineRecipeType;
import dev.wp.ex_extended_industrialization.ExEI;
import dev.wp.ex_extended_industrialization.machines.guicomponents.multiprocessingarraymachineslot.MultiProcessingArrayMachineSlot;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public final class MultiProcessingArrayMachineComponent implements IComponent.ServerOnly, DropableComponent {
    public static final ResourceLocation ID = ExEI.id("mb_processing_array_machine");

    private ItemStack machines = ItemStack.EMPTY;
    private MachineRecipeType machineRecipeType;

    public ItemStack getMachines() {
        return machines;
    }

    public boolean hasMachines() {
        return !machines.isEmpty() && machineRecipeType != null;
    }

    public int getMachineCount() {
        return machines.getCount();
    }

    public MachineRecipeType getMachineRecipeType() {
        return machineRecipeType;
    }

    public void setMachines(MachineBlockEntity be, ItemStack machines) {
        this.machines = machines;
        this.machineRecipeType = machines.isEmpty() ? null : MultiProcessingArrayMachineSlot.getMachine(machines).recipeType();
        be.setChanged();
        be.sync();
    }

    @Override
    public void writeNbt(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("machineStack", machines.saveOptional(registries));
    }

    @Override
    public void readNbt(CompoundTag tag, HolderLookup.Provider registries, boolean isUpgradingMachine) {
        machines = ItemStack.parseOptional(registries, tag.getCompound("machineStack"));
        if(!machines.isEmpty()) {
            machineRecipeType = MultiProcessingArrayMachineSlot.getMachine(machines).recipeType();
        }
    }

    @Override
    public ItemStack getDrop() {
        return machines;
    }
}
