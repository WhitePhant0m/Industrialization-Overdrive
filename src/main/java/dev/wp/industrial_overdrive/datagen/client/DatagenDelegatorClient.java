package dev.wp.industrial_overdrive.datagen.client;

import dev.wp.industrial_overdrive.IO;
import dev.wp.industrial_overdrive.datagen.client.provider.LanguageDatagenProvider;
import dev.wp.industrial_overdrive.datagen.client.provider.models.BlockModelsDatagenProvider;
import dev.wp.industrial_overdrive.datagen.client.provider.models.ItemModelsDatagenProvider;
import net.minecraft.data.DataProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.swedz.tesseract.neoforge.datagen.mi.MIDatagenHooks;

import java.util.function.Function;

public final class DatagenDelegatorClient {
    public static void configure(GatherDataEvent event) {
        MIDatagenHooks.Client.includeMISprites(event);

        MIDatagenHooks.Client.addMachineCasingModelsHook(event, IO.ID);

        add(event, BlockModelsDatagenProvider::new);
        add(event, ItemModelsDatagenProvider::new);
        add(event, LanguageDatagenProvider::new);
    }

    private static void add(GatherDataEvent event, Function<GatherDataEvent, DataProvider> providerCreator) {
        event.getGenerator().addProvider(event.includeClient(), providerCreator.apply(event));
    }
}
