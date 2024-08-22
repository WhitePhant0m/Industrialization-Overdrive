package dev.wp.ex_extended_industrialization.datagen.client.provider.models;

import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import dev.wp.ex_extended_industrialization.ExEI;
import dev.wp.ex_extended_industrialization.ExEIItems;
import net.swedz.tesseract.neoforge.registry.holder.ItemHolder;

public final class ItemModelsDatagenProvider extends ItemModelProvider {
    public ItemModelsDatagenProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), ExEI.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerModels() {
        for (ItemHolder item : ExEIItems.values()) {
            ItemModelBuilder itemModelBuilder = this.getBuilder("item/%s".formatted(item.identifier().id()));
            item.modelBuilder().accept(itemModelBuilder);
        }
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
