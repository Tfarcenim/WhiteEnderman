package tfar.whiteenderman;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class Init {

    public static final EntityType<WhiteEndermanEntity> TYPE = EntityType.Builder.of(WhiteEndermanEntity::new, MobCategory.MONSTER)
            .sized(0.75f, 3.625F).clientTrackingRange(8)
            .build("");

    public static final SpawnEggItem SPAWN_EGG = new SpawnEggItem(TYPE,0xffffffff,0xeeeeeeee,new Item.Properties());

}
