package tfar.whiteenderman;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.fml.config.ModConfig;

public class WhiteEndermanFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        WhiteEnderman.LOG.info("Hello Fabric world!");
        WhiteEnderman.init();
        WhiteEnderman.registerEntityAttributes(FabricDefaultAttributeRegistry::register);
        setupSpawning();
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(itemGroup
                -> itemGroup.accept(WhiteEnderman.WHITE_ENDERMAN_SPAWN_EGG.get()));
        ForgeConfigRegistry.INSTANCE.register(WhiteEnderman.MOD_ID, ModConfig.Type.SERVER, WhiteEndermanConfigs.SERVER_SPEC);
    }

    // We might need to mixin into where mobs spawn to do this on Fabric... I'm not sure how to proceed there sadly.

    /**
     * void entityJoin(EntityEvent event) {
     * Level level = event.getLevel();
     * if (!event.loadedFromDisk() && !level.isClientSide) {
     * Entity entity = event.getEntity();
     * if (entity instanceof EnderMan enderMan && !(entity instanceof WhiteEndermanEntity)) {
     * boolean replace = level.getRandom().nextDouble() < WhiteEndermanConfigs.SERVER.WHITE_ENDERMAN_CHANCE.get();
     * if (replace) {
     * event.setCanceled(true);
     * CompoundTag compoundTag = enderMan.saveWithoutId(new CompoundTag());
     * WhiteEndermanEntity whiteEndermanEntity = WhiteEnderman.WHITE_ENDERMAN.get().create(level);
     * whiteEndermanEntity.load(compoundTag);
     * level.addFreshEntity(whiteEndermanEntity);
     * }
     * }
     * }
     * }
     */

    void setupSpawning() {
        BiomeModifications.addSpawn(biome -> biome.getBiomeRegistryEntry().is(
                        new ResourceLocation(WhiteEnderman.MOD_ID, "spawns")), MobCategory.MONSTER,
                WhiteEnderman.WHITE_ENDERMAN.get(), 1, 1, 1);
        SpawnPlacements.register(WhiteEnderman.WHITE_ENDERMAN.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }
}
