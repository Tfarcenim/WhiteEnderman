package tfar.whiteenderman;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class WhiteEndermanConfigs {
    public static final WhiteEndermanConfigs SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    public static final boolean ALTERNATIVE_SPAWNING = false;

    public final ForgeConfigSpec.DoubleValue WHITE_ENDERMAN_CHANCE;
    public final ForgeConfigSpec.BooleanValue AGGRESSIVE;

    public WhiteEndermanConfigs(ForgeConfigSpec.Builder builder) {
        builder.push("general");
        WHITE_ENDERMAN_CHANCE = builder.defineInRange("white_enderman_chance", .01, 0, 1);
        AGGRESSIVE = builder.define("aggressive",false);
        builder.pop();
    }

    static {
        final Pair<WhiteEndermanConfigs, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(WhiteEndermanConfigs::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
    }
}
