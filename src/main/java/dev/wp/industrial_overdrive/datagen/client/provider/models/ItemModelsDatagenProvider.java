package dev.wp.industrial_overdrive.datagen.client.provider.models;

import dev.wp.industrial_overdrive.IO;
import dev.wp.industrial_overdrive.IOItems;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.swedz.tesseract.neoforge.registry.holder.ItemHolder;

public final class ItemModelsDatagenProvider extends ItemModelProvider {
    public ItemModelsDatagenProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), IO.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerModels() {
        for (ItemHolder item : IOItems.values()) {
            ItemModelBuilder itemModelBuilder = this.getBuilder("item/%s".formatted(item.identifier().id()));
            item.modelBuilder().accept(itemModelBuilder);
        }
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
