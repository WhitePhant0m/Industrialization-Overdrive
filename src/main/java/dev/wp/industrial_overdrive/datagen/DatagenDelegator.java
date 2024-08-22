package dev.wp.industrial_overdrive.datagen;

import dev.wp.industrial_overdrive.datagen.client.DatagenDelegatorClient;
import dev.wp.industrial_overdrive.datagen.server.DatagenDelegatorServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public final class DatagenDelegator {
    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        DatagenDelegatorClient.configure(event);
        DatagenDelegatorServer.configure(event);
    }
}
