package tfar.whiteenderman.platform;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import tfar.whiteenderman.WhiteEndermanForge;
import tfar.whiteenderman.platform.services.IPlatformHelper;

import java.util.function.Supplier;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, Supplier<EntityType<T>> entity) {
        return WhiteEndermanForge.ENTITIES.register(id, entity);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return WhiteEndermanForge.ITEMS.register(id, item);
    }

    @Override
    public <E extends Mob> Supplier<SpawnEggItem> makeSpawnEgg(Supplier<EntityType<E>> entityType, int primaryEggColour, int secondaryEggColour, Item.Properties itemProperties) {
        return () -> new ForgeSpawnEggItem(entityType, primaryEggColour, secondaryEggColour, itemProperties);
    }
}