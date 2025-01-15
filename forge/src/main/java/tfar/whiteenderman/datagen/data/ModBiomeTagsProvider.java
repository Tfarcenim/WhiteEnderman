package tfar.whiteenderman.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import tfar.whiteenderman.ForgeInit;
import tfar.whiteenderman.WhiteEnderman;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {

    public ModBiomeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, WhiteEnderman.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ForgeInit.BIOME_SPAWNS);
    }
}
