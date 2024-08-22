package dev.wp.ex_extended_industrialization.datagen.client.provider.models;

import dev.wp.ex_extended_industrialization.ExEI;
import dev.wp.ex_extended_industrialization.ExEIBlocks;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.swedz.tesseract.neoforge.registry.holder.BlockHolder;

public final class BlockModelsDatagenProvider extends BlockStateProvider {
    public BlockModelsDatagenProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), ExEI.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        for (BlockHolder<?> block : ExEIBlocks.values()) {
            block.modelBuilder().accept(this);
        }
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
