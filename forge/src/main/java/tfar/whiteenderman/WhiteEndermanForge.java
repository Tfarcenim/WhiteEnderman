package tfar.whiteenderman;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import tfar.whiteenderman.datagen.ModDatagen;

@Mod(WhiteEnderman.MOD_ID)
public class WhiteEndermanForge {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, WhiteEnderman.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, WhiteEnderman.MOD_ID);

    public WhiteEndermanForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, WhiteEndermanConfigs.SERVER_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

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
        if (FMLEnvironment.dist.isClient()) {
            ModClientForge.init(bus);
        }

        MinecraftForge.EVENT_BUS.addListener(this::entityJoin);
        // Use Forge to bootstrap the Common mod.
        WhiteEnderman.init();

    }

    void addToTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(WhiteEnderman.WHITE_ENDERMAN_SPAWN_EGG::get);
        }
    }

    void entityJoin(EntityJoinLevelEvent event) {
        Level level = event.getLevel();
        if (!event.loadedFromDisk() && !level.isClientSide) {
            Entity entity = event.getEntity();
            if (entity instanceof EnderMan enderMan && !(entity instanceof WhiteEndermanEntity)) {
                boolean replace = level.getRandom().nextDouble() < WhiteEndermanConfigs.SERVER.WHITE_ENDERMAN_CHANCE.get();
                if (replace) {
                    event.setCanceled(true);
                    CompoundTag compoundTag = enderMan.saveWithoutId(new CompoundTag());
                    WhiteEndermanEntity whiteEndermanEntity = WhiteEnderman.WHITE_ENDERMAN.get().create(level);
                    whiteEndermanEntity.load(compoundTag);
                    level.addFreshEntity(whiteEndermanEntity);
                }
            }
        }
    }

    void spawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(WhiteEnderman.WHITE_ENDERMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);

    }
}