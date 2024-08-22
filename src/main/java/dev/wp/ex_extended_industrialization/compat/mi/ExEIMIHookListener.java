package dev.wp.ex_extended_industrialization.compat.mi;

import dev.wp.ex_extended_industrialization.ExEIMachines;
import dev.wp.ex_extended_industrialization.machines.guicomponents.multiprocessingarraymachineslot.MultiProcessingArrayMachineSlot;
import dev.wp.ex_extended_industrialization.machines.guicomponents.multiprocessingarraymachineslot.MultiProcessingArrayMachineSlotClient;
import net.swedz.tesseract.neoforge.compat.mi.hook.MIHookListener;
import net.swedz.tesseract.neoforge.compat.mi.hook.TesseractMIHookEntrypoint;
import net.swedz.tesseract.neoforge.compat.mi.hook.context.listener.*;

@TesseractMIHookEntrypoint
public class ExEIMIHookListener implements MIHookListener {
    @Override
    public void blastFurnaceTiers(BlastFurnaceTiersMIHookContext hook)
    {
//        ExEIMachines.blastFurnaceTiers(hook);
    }

    @Override
    public void clientGuiComponents(ClientGuiComponentsMIHookContext hook) {
        hook.register(MultiProcessingArrayMachineSlot.ID, MultiProcessingArrayMachineSlotClient::new);
    }

    @Override
    public void machineCasings(MachineCasingsMIHookContext hook) {
//        ExEIMachines.casings(hook);
    }

    @Override
    public void machineRecipeTypes(MachineRecipeTypesMIHookContext hook) {
//        ExEIMachines.recipeTypes(hook);
    }

    @Override
    public void multiblockMachines(MultiblockMachinesMIHookContext hook) {
        ExEIMachines.multiblocks(hook);
    }

    @Override
    public void singleBlockCraftingMachines(SingleBlockCraftingMachinesMIHookContext hook) {
//        ExEIMachines.singleBlockCrafting(hook);
    }

    @Override
    public void singleBlockSpecialMachines(SingleBlockSpecialMachinesMIHookContext hook) {
//        ExEIMachines.singleBlockSpecial(hook);
    }

    @Override
    public void tooltips() {
//        ExEITooltips.init();
    }

    @Override
    public void viewerSetup(ViewerSetupMIHookContext hook) {
//        hook.register(new FluidFertilizerCategory());
    }
}
