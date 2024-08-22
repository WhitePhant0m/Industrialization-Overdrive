package dev.wp.industrial_overdrive.datagen.client.provider;

import aztech.modern_industrialization.machines.recipe.MachineRecipeType;
import dev.wp.industrial_overdrive.IO;
import dev.wp.industrial_overdrive.IOItems;
import dev.wp.industrial_overdrive.IOMachines;
import dev.wp.industrial_overdrive.IOText;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.swedz.tesseract.neoforge.registry.holder.ItemHolder;

import java.util.Map;

public final class LanguageDatagenProvider extends LanguageProvider {
    public LanguageDatagenProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), IO.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (IOText text : IOText.values()) {
            this.add(text.getTranslationKey(), text.englishText());
        }

        for (ItemHolder item : IOItems.values()) {
            this.add(item.asItem(), item.identifier().englishName());
        }


        for (Map.Entry<MachineRecipeType, String> entry : IOMachines.RecipeTypes.getNames().entrySet()) {
            this.add("recipe_type.%s.%s".formatted(entry.getKey().getId().getNamespace(), entry.getKey().getPath()), entry.getValue());
        }

        this.add("itemGroup.%s.%s".formatted(IO.ID, IO.ID), IO.NAME);
    }
}
