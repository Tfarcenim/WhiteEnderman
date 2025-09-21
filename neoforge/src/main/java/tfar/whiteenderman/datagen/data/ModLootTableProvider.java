package tfar.whiteenderman.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends LootTableProvider {

    public ModLootTableProvider(PackOutput pOutput, Set<ResourceKey<LootTable>> pRequiredTables, List<SubProviderEntry> pSubProviders
    , CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(pOutput,pRequiredTables, pSubProviders,lookupProvider);
    }

    public static LootTableProvider create(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        return new ModLootTableProvider(pOutput,Set.of(), List.of(new SubProviderEntry(ModEntityLoot::new, LootContextParamSets.ENTITY)),lookupProvider);
    }


    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {

    }
}
