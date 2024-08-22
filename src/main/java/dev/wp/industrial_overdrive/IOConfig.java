package dev.wp.industrial_overdrive;

import net.neoforged.neoforge.common.ModConfigSpec;

public class IOConfig {
    private static final ModConfigSpec.Builder BUILDER;

    private static final ModConfigSpec.BooleanValue ALLOW_UPGRADES_IN_MULTI_PROCESSING_ARRAY;
    private static final ModConfigSpec.DoubleValue MULTI_PROCESSING_ARRAY_EU;

    public static final ModConfigSpec SPEC;

    static {
        BUILDER = new ModConfigSpec.Builder();

        ALLOW_UPGRADES_IN_MULTI_PROCESSING_ARRAY = BUILDER
                .comment("Whether upgrades should be allowed in the Multi Processing Array")
                .define("allow_upgrades_in_multi_processing_array", true);
        {
            BUILDER.push("batching_machines");

            MULTI_PROCESSING_ARRAY_EU = BUILDER
                    .comment("The multiplier to use for the EU cost of the Multi Processing Array")
                    .defineInRange("multi_processing_array_eu", 1D, 0.1D, Double.MAX_VALUE);

            BUILDER.pop();
        }

        SPEC = BUILDER.build();
    }

    public static boolean allowUpgradesInMultiProcessingArray;
    public static double multiProcessingArrayEuCostMultiplier;

    public static void loadConfig() {
        allowUpgradesInMultiProcessingArray = ALLOW_UPGRADES_IN_MULTI_PROCESSING_ARRAY.get();
        multiProcessingArrayEuCostMultiplier = MULTI_PROCESSING_ARRAY_EU.get();
    }
}
