package tfar.whiteenderman.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import tfar.whiteenderman.ForgeInit;
import tfar.whiteenderman.WhiteEnderman;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataPackProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModDataPackProvider::biomeModifiers);


    public ModDataPackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(WhiteEnderman.MOD_ID));
    }

    public static void biomeModifiers(BootstrapContext<BiomeModifier> context) {
        context.register(ForgeInit.SPAWNS,
                new BiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME)
                        .getOrThrow(ForgeInit.BIOME_SPAWNS), List.of(
                        new MobSpawnSettings.SpawnerData(WhiteEnderman.WHITE_ENDERMAN.get(), 1, 1, 1)))
        );
    }
}
