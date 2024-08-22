package dev.wp.industrial_overdrive;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum IOText {
    MULTI_PROCESSING_ARRAY_BATCH_SIZE("Batch size is determined by the amount of machines provided to it."),
    MULTI_PROCESSING_ARRAY_EU_COST_MULTIPLIER("Runs at %s the EU cost."),
    MULTI_PROCESSING_ARRAY_MACHINE_INPUT("Insert electric crafting multiblocks to run in parallel."),
    MULTI_PROCESSING_ARRAY_RECIPE("Can run recipes of any electric crafting multiblock provided to it in batches."),
    MULTI_PROCESSING_ARRAY_SIZE("Machines: %d");

    private final String englishText;

    IOText(String englishText) {
        this.englishText = englishText;
    }

    public String englishText() {
        return englishText;
    }

    public String getTranslationKey() {
        return "text.%s.%s".formatted(IO.ID, this.name().toLowerCase());
    }

    public MutableComponent text() {
        return Component.translatable(this.getTranslationKey());
    }

    public MutableComponent text(Object... args) {
        return Component.translatable(this.getTranslationKey(), args);
    }
}
