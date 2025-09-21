package tfar.whiteenderman;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import tfar.whiteenderman.datagen.ModDatagen;

@Mod(WhiteEnderman.MOD_ID)
public class WhiteEndermanForge {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, WhiteEnderman.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, WhiteEnderman.MOD_ID);

    public WhiteEndermanForge(Dist dist, IEventBus bus, ModContainer container) {
        container.registerConfig(ModConfig.Type.SERVER, WhiteEndermanConfigs.SERVER_SPEC);

        ENTITIES.register(bus);
        ITEMS.register(bus);

        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
        bus.<EntityAttributeCreationEvent>addListener(entityAttributeCreationEvent
                -> WhiteEnderman.registerEntityAttributes(entityAttributeCreationEvent::put));
        bus.addListener(ModDatagen::gather);
        bus.addListener(this::spawnPlacement);
        bus.addListener(this::addToTab);
        if (dist.isClient()) {
            ModClientForge.init(bus);
        }

        NeoForge.EVENT_BUS.addListener(this::entityJoin);
        // Use Forge to bootstrap the Common mod.
        WhiteEnderman.init();

    }

    void addToTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(WhiteEnderman.WHITE_ENDERMAN_SPAWN_EGG::get);
        }
    }

    void entityJoin(EntityJoinLevelEvent event) {
        boolean cancel = WhiteEnderman.onEntityJoinLevel(event.getEntity(),event.getLevel(),event.loadedFromDisk());
        if (cancel) {
            event.setCanceled(true);
        }
    }

    void spawnPlacement(RegisterSpawnPlacementsEvent event) {
        event.register(WhiteEnderman.WHITE_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);

    }
}