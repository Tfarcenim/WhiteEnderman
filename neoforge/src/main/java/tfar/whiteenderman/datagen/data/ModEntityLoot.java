package tfar.whiteenderman.datagen.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.packs.VanillaEntityLoot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import tfar.whiteenderman.WhiteEnderman;

import java.util.stream.Stream;

public class ModEntityLoot extends VanillaEntityLoot {
    public ModEntityLoot(HolderLookup.Provider pRegistries) {
        super(pRegistries);
    }

    @Override
    public void generate() {
        this.add(WhiteEnderman.WHITE_ENDERMAN.get(), LootTable.lootTable()
                .withPool(LootPool
                        .lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.ENDER_EYE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))
                )
                .withPool(LootPool
                        .lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.END_CRYSTAL))
                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.025F, 0.05F))
                )
        );

    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return Stream.of(WhiteEnderman.WHITE_ENDERMAN.get());
    }
}
