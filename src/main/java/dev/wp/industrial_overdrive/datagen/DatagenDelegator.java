package dev.wp.industrial_overdrive.datagen;

import dev.wp.industrial_overdrive.IO;
import dev.wp.industrial_overdrive.datagen.client.DatagenDelegatorClient;
import dev.wp.industrial_overdrive.datagen.server.DatagenDelegatorServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static net.neoforged.fml.common.EventBusSubscriber.Bus.MOD;

@EventBusSubscriber(modid = IO.ID, bus = MOD)
public final class DatagenDelegator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DatagenDelegatorClient.configure(event);
        DatagenDelegatorServer.configure(event);
    }
}
