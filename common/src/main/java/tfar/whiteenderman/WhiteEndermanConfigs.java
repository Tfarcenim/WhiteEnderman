package tfar.whiteenderman;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class WhiteEndermanConfigs {
    public static final WhiteEndermanConfigs SERVER;
    public static final ModConfigSpec SERVER_SPEC;

    public static final boolean ALTERNATIVE_SPAWNING = false;

    public final ModConfigSpec.DoubleValue WHITE_ENDERMAN_CHANCE;
    public final ModConfigSpec.BooleanValue AGGRESSIVE;

    public WhiteEndermanConfigs(ModConfigSpec.Builder builder) {
        builder.push("general");
        WHITE_ENDERMAN_CHANCE = builder.defineInRange("white_enderman_chance", .01, 0, 1);
        AGGRESSIVE = builder.define("aggressive",false);
        builder.pop();
    }

    static {
        final Pair<WhiteEndermanConfigs, ModConfigSpec> specPair2 = new ModConfigSpec.Builder().configure(WhiteEndermanConfigs::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
    }
}
