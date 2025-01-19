package tfar.whiteenderman;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tfar.whiteenderman.platform.services.IPlatformHelper;

import java.util.ServiceLoader;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

//Really rare.
//White skin, like the picture.
//Still passive and picks blocks normally.
//You CAN look at him. Won't be enraged by being looked at.
//If you damage him, he's enraged and will attack you like a normal enderman.
//Higher health and damage than normal.
//Does NOT take damage from water and will follow you into water.
//If there are other normal endermen around, they will come to his defense.
//Drops 1-2 Eye of Ender (guaranteed) and a low chance of an End Crystal drop.
public class WhiteEnderman {

    public static final String MOD_ID = "whiteenderman";
    public static final String MOD_NAME = "WhiteEnderman";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final IPlatformHelper COMMON_PLATFORM = ServiceLoader.load(IPlatformHelper.class).findFirst().orElseThrow();

    public static void init() {
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static final Supplier<EntityType<WhiteEndermanEntity>> WHITE_ENDERMAN = COMMON_PLATFORM.registerEntity(
            "white_enderman", () -> EntityType.Builder.of(WhiteEndermanEntity::new, MobCategory.MONSTER)
                    .sized(0.75f, 3.625F).clientTrackingRange(8).build("white_enderman"));

    public static final Supplier<SpawnEggItem> WHITE_ENDERMAN_SPAWN_EGG
            = COMMON_PLATFORM.registerItem("white_enderman_spawn_egg", COMMON_PLATFORM.makeSpawnEgg(WHITE_ENDERMAN,
            0xffffffff, 0xeeeeeeee, new Item.Properties()));

    public static void registerEntityAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> register) {
        register.accept(WHITE_ENDERMAN.get(), WhiteEndermanEntity.createWhiteAttributes().build());
    }

    public static boolean onEntityJoinLevel(Entity entity, Level level, boolean loadedFromDisk) {
        if (!loadedFromDisk && !level.isClientSide && entity instanceof EnderMan enderMan && !(entity instanceof WhiteEndermanEntity)) {
            boolean replace = level.getRandom().nextDouble() < WhiteEndermanConfigs.SERVER.WHITE_ENDERMAN_CHANCE.get();
            if (replace) {
                WhiteEndermanEntity whiteEndermanEntity = WhiteEnderman.WHITE_ENDERMAN.get().create(level);
                whiteEndermanEntity.setPos(enderMan.position());
                if (level.noCollision(whiteEndermanEntity) && level.isUnobstructed(whiteEndermanEntity)) {//check for other mobs and blocks
                    CompoundTag compoundTag = enderMan.saveWithoutId(new CompoundTag());
                    whiteEndermanEntity.load(compoundTag);
                    level.addFreshEntity(whiteEndermanEntity);
                    return true;
                } else {
                    whiteEndermanEntity.discard();
                }
            }
        }
        return false;
    }
}