package dev.wp.ex_extended_industrialization.datagen.client.provider;

import aztech.modern_industrialization.machines.recipe.MachineRecipeType;
import dev.wp.ex_extended_industrialization.ExEI;
import dev.wp.ex_extended_industrialization.ExEIItems;
import dev.wp.ex_extended_industrialization.ExEIMachines;
import dev.wp.ex_extended_industrialization.ExEIText;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.swedz.tesseract.neoforge.registry.holder.ItemHolder;

import java.util.Map;

public final class LanguageDatagenProvider extends LanguageProvider {
    public LanguageDatagenProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), ExEI.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (ExEIText text : ExEIText.values()) {
            this.add(text.getTranslationKey(), text.englishText());
        }

        for (ItemHolder item : ExEIItems.values()) {
            this.add(item.asItem(), item.identifier().englishName());
        }


        for (Map.Entry<MachineRecipeType, String> entry : ExEIMachines.RecipeTypes.getNames().entrySet()) {
            this.add("recipe_type.%s.%s".formatted(entry.getKey().getId().getNamespace(), entry.getKey().getPath()), entry.getValue());
        }

        this.add("itemGroup.%s.%s".formatted(ExEI.ID, ExEI.ID), ExEI.NAME);
    }
}
