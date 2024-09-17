package dev.wp.industrial_overdrive;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.swedz.tesseract.neoforge.capabilities.CapabilitiesListeners;
import net.swedz.tesseract.neoforge.registry.holder.BlockHolder;
import net.swedz.tesseract.neoforge.registry.holder.ItemHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(IO.ID)
public final class IO {
    public static final String ID = "industrialization_overdrive";
    public static final String NAME = "Industrialization Overdrive";

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(ID, name);
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public IO(IEventBus bus, ModContainer container) {
        container.registerConfig(ModConfig.Type.STARTUP, IOConfig.SPEC);
        IOConfig.loadConfig();
        bus.addListener(FMLCommonSetupEvent.class, (event) -> IOConfig.loadConfig());

        IOItems.init(bus);
        IOBlocks.init(bus);
        IOOtherRegistries.init(bus);

        bus.addListener(FMLCommonSetupEvent.class, (event) -> {
            IOItems.values().forEach(ItemHolder::triggerRegistrationListener);
            IOBlocks.values().forEach(BlockHolder::triggerRegistrationListener);
        });

        bus.addListener(RegisterCapabilitiesEvent.class, (event) -> CapabilitiesListeners.triggerAll(ID, event));
    }
}
