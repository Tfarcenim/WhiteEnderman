package tfar.whiteenderman.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import tfar.whiteenderman.datagen.data.ModBiomeTagsProvider;
import tfar.whiteenderman.datagen.data.ModDataPackProvider;
import tfar.whiteenderman.datagen.data.ModLootTableProvider;
import tfar.whiteenderman.datagen.data.ModRecipeProvider;

import java.util.concurrent.CompletableFuture;

public class ModDatagen {

    public static void gather(GatherDataEvent event) {
        boolean client = event.includeClient();
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        dataGenerator.addProvider(client, new ModLangProvider(packOutput));
        dataGenerator.addProvider(client, new ModItemModelProvider(packOutput, existingFileHelper));
        if (event.includeServer()) {
            dataGenerator.addProvider(true, ModLootTableProvider.create(packOutput));
            dataGenerator.addProvider(true, new ModRecipeProvider(packOutput));
            dataGenerator.addProvider(true, new ModDataPackProvider(packOutput, lookupProvider));
            dataGenerator.addProvider(true, new ModBiomeTagsProvider(packOutput, lookupProvider, existingFileHelper));
        }

    }
}
