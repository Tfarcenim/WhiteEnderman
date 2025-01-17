package tfar.whiteenderman.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import tfar.whiteenderman.ForgeInit;
import tfar.whiteenderman.WhiteEnderman;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataPackProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModDataPackProvider::biomeModifiers);


    public ModDataPackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(WhiteEnderman.MOD_ID));
    }

    public static void biomeModifiers(BootstapContext<BiomeModifier> context) {
        context.register(ForgeInit.SPAWNS,
                new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME)
                        .getOrThrow(ForgeInit.BIOME_SPAWNS), List.of(
                        new MobSpawnSettings.SpawnerData(WhiteEnderman.WHITE_ENDERMAN.get(), 1, 1, 1)))
        );
    }
}
