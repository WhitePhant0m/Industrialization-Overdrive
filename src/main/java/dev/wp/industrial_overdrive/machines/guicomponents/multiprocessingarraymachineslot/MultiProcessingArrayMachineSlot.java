package dev.wp.industrial_overdrive.machines.guicomponents.multiprocessingarraymachineslot;

import aztech.modern_industrialization.inventory.HackySlot;
import aztech.modern_industrialization.inventory.SlotGroup;
import aztech.modern_industrialization.machines.MachineBlock;
import aztech.modern_industrialization.machines.MachineBlockEntity;
import aztech.modern_industrialization.machines.blockentities.multiblocks.AbstractElectricCraftingMultiblockBlockEntity;
import aztech.modern_industrialization.machines.blockentities.multiblocks.FusionReactorBlockEntity;
import aztech.modern_industrialization.machines.gui.GuiComponent;
import aztech.modern_industrialization.machines.gui.MachineGuiParameters;
import dev.wp.industrial_overdrive.IO;
import dev.wp.industrial_overdrive.IOConfig;
import dev.wp.industrial_overdrive.machines.components.craft.MultiProcessingArrayMachineComponent;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

import java.util.Set;
import java.util.function.Supplier;

public final class MultiProcessingArrayMachineSlot {
    public static final ResourceLocation ID = IO.id("multi_processing_array_machine_slot");

    private static final Set<Class<? extends AbstractElectricCraftingMultiblockBlockEntity>> EXCLUDED_MACHINES = Set.of(
            FusionReactorBlockEntity.class
    );

    public static int getSlotX(MachineGuiParameters guiParameters) {
        return guiParameters.backgroundWidth + 6;
    }

    public static int getSlotY() {
        return 86 - (IOConfig.allowUpgradesInMultiProcessingArray ? 0 : 20);
    }

    public static boolean isMachine(ItemStack itemStack) {
        if (itemStack.getItem() instanceof BlockItem blockItem &&
                blockItem.getBlock() instanceof MachineBlock machineBlock &&
                machineBlock.getBlockEntityInstance() instanceof AbstractElectricCraftingMultiblockBlockEntity blockEntity) {
            return !EXCLUDED_MACHINES.contains(blockEntity.getClass());
        }
        return false;
    }

    public static AbstractElectricCraftingMultiblockBlockEntity getMachine(ItemStack itemStack) {
        return (AbstractElectricCraftingMultiblockBlockEntity) ((MachineBlock) ((BlockItem) itemStack.getItem()).getBlock()).getBlockEntityInstance();
    }

    public static final class Server implements GuiComponent.Server<Integer> {
        private final MachineBlockEntity machine;
        private final Supplier<Integer> getMaxMachines;
        private final MultiProcessingArrayMachineComponent machines;

        public Server(MachineBlockEntity machine, Supplier<Integer> getMaxMachines, MultiProcessingArrayMachineComponent machines) {
            this.machine = machine;
            this.getMaxMachines = getMaxMachines;
            this.machines = machines;
        }

        @Override
        public Integer copyData() {
            return getMaxMachines.get();
        }

        @Override
        public boolean needsSync(Integer cachedData) {
            return !cachedData.equals(getMaxMachines.get());
        }

        @Override
        public void writeInitialData(RegistryFriendlyByteBuf buf) {
            this.writeCurrentData(buf);
        }

        @Override
        public void writeCurrentData(RegistryFriendlyByteBuf buf) {
            buf.writeInt(getMaxMachines.get());
        }

        @Override
        public ResourceLocation getId() {
            return ID;
        }

        @Override
        public void setupMenu(GuiComponent.MenuFacade menu) {
            menu.addSlotToMenu(
                    new HackySlot(getSlotX(machine.guiParams), getSlotY()) {
                        @Override
                        protected ItemStack getRealStack() {
                            return machines.getMachines();
                        }

                        @Override
                        protected void setRealStack(ItemStack itemStack) {
                            machines.setMachines(machine, itemStack);
                        }

                        @Override
                        public boolean mayPlace(ItemStack itemStack) {
                            return isMachine(itemStack);
                        }

                        @Override
                        public int getMaxStackSize() {
                            return getMaxMachines.get();
                        }
                    },
                    SlotGroup.CONFIGURABLE_STACKS
            );
        }
    }
}