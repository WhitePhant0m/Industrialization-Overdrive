package dev.wp.ex_extended_industrialization;

import dev.wp.ex_extended_industrialization.datagen.DatagenDelegator;
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

@Mod(ExEI.ID)
public final class ExEI {
    public static final String ID = "ex_extended_industrialization";
    public static final String NAME = "Extended Extended Industrialization";

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(ID, name);
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public ExEI(IEventBus bus, ModContainer container) {
        container.registerConfig(ModConfig.Type.STARTUP, ExEIConfig.SPEC);
        ExEIConfig.loadConfig();
        bus.addListener(FMLCommonSetupEvent.class, (event) -> ExEIConfig.loadConfig());

        ExEIItems.init(bus);
        ExEIBlocks.init(bus);
        ExEIOtherRegistries.init(bus);

        bus.register(new DatagenDelegator());

        bus.addListener(FMLCommonSetupEvent.class, (event) -> {
            ExEIItems.values().forEach(ItemHolder::triggerRegistrationListener);
            ExEIBlocks.values().forEach(BlockHolder::triggerRegistrationListener);
        });

        bus.addListener(RegisterCapabilitiesEvent.class, (event) -> CapabilitiesListeners.triggerAll(ID, event));
    }
}
