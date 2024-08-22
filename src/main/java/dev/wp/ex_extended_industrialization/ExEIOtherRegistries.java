package dev.wp.ex_extended_industrialization;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.swedz.tesseract.neoforge.registry.holder.ItemHolder;

import java.util.Comparator;
import java.util.function.Supplier;

public final class ExEIOtherRegistries {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ExEI.ID);

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, ExEI.ID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExEI.ID);

    public static final Supplier<CreativeModeTab> CREATIVE_TAB = ExEIOtherRegistries.CREATIVE_MODE_TABS.register(ExEI.ID, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.%s.%s".formatted(ExEI.ID, ExEI.ID)))
            .icon(() -> Blocks.DIRT.asItem().getDefaultInstance())
            .displayItems((params, output) -> {
                Comparator<ItemHolder> compareBySortOrder = Comparator.comparing(ItemHolder::sortOrder);
                Comparator<ItemHolder> compareByName = Comparator.comparing((i) -> i.identifier().id());
                ExEIItems.values().stream()
                        .sorted(compareBySortOrder.thenComparing(compareByName))
                        .forEach(output::accept);
            })
            .build());

    public static void init(IEventBus bus) {
        RECIPE_SERIALIZERS.register(bus);
        RECIPE_TYPES.register(bus);
        CREATIVE_MODE_TABS.register(bus);
    }
}
