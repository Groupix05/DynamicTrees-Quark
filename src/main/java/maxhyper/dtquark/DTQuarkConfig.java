package maxhyper.dtquark;

import net.minecraftforge.common.ForgeConfigSpec;

public class DTQuarkConfig {

    public static ForgeConfigSpec COMMON_CONFIG;

    public static final ForgeConfigSpec.IntValue ANCIENT_SEED_LOOT_WEIGHT;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("World Settings").push("world");
        ANCIENT_SEED_LOOT_WEIGHT = COMMON_BUILDER.comment("Defines the weight of ancient seed loot in ancient city chests. Set to 0 to disable.")
                .defineInRange("ancientSeedLootWeight", 8, 0, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

}
