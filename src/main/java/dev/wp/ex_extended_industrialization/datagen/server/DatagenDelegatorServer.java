package dev.wp.ex_extended_industrialization.datagen.server;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import dev.wp.ex_extended_industrialization.datagen.server.provider.loottable.BlockLootTableDatagenProvider;
import dev.wp.ex_extended_industrialization.datagen.server.provider.recipes.CommonRecipesServerDatagenProvider;
import dev.wp.ex_extended_industrialization.datagen.server.provider.recipes.MachineItemRecipesServerDatagenProvider;
import dev.wp.ex_extended_industrialization.datagen.server.provider.tags.BlockTagDatagenProvider;
import dev.wp.ex_extended_industrialization.datagen.server.provider.tags.ItemTagDatagenProvider;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class DatagenDelegatorServer {
    public static void configure(GatherDataEvent event) {
    addLootTable(event, BlockLootTableDatagenProvider::new);

    add(event, CommonRecipesServerDatagenProvider::new);
    add(event, MachineItemRecipesServerDatagenProvider::new);

    add(event, BlockTagDatagenProvider::new);
    add(event, ItemTagDatagenProvider::new);
}

    private static void add(GatherDataEvent event, Function<GatherDataEvent, DataProvider> providerCreator) {
        event.getGenerator().addProvider(event.includeServer(), providerCreator.apply(event));
    }

    private static void addLootTable(GatherDataEvent event, Function<HolderLookup.Provider, LootTableSubProvider> providerCreator) {
        event.getGenerator().addProvider(
                event.includeServer(),
                new LootTableProvider(
                        event.getGenerator().getPackOutput(),
                        Set.of(),
                        List.of(new LootTableProvider.SubProviderEntry(providerCreator, LootContextParamSets.BLOCK)),
                        event.getLookupProvider()
                )
        );
    }
}
