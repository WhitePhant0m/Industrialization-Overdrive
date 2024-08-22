package dev.wp.ex_extended_industrialization.datagen;

import dev.wp.ex_extended_industrialization.datagen.client.DatagenDelegatorClient;
import dev.wp.ex_extended_industrialization.datagen.server.DatagenDelegatorServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public final class DatagenDelegator {
    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        DatagenDelegatorClient.configure(event);
        DatagenDelegatorServer.configure(event);
    }
}
