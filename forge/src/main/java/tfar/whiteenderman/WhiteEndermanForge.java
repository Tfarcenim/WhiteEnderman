package tfar.whiteenderman;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegisterEvent;
import tfar.whiteenderman.datagen.ModDatagen;

@Mod(WhiteEnderman.MOD_ID)
public class WhiteEndermanForge {
    
    public WhiteEndermanForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
        bus.addListener(this::register);
        bus.addListener(this::attrib);
        bus.addListener(ModDatagen::gather);
        if (FMLEnvironment.dist.isClient()) {
            ModClientForge.init(bus);
        }
        // Use Forge to bootstrap the Common mod.
        WhiteEnderman.init();
        
    }

    void register(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.ENTITY_TYPE) {
            event.register(Registries.ENTITY_TYPE,WhiteEnderman.id("white_enderman"),() -> Init.TYPE);
        }

        if (event.getRegistryKey() == Registries.ITEM) {
            event.register(Registries.ITEM,WhiteEnderman.id("white_enderman_spawn_egg"),() -> Init.SPAWN_EGG);
        }
    }

    void attrib(EntityAttributeCreationEvent event) {
        event.put(Init.TYPE,WhiteEndermanEntity.createWhiteAttributes().build());
    }

    void spawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(EntityType.ENDERMAN, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

    }

}