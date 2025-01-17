package tfar.whiteenderman;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

public class ModClient {

    public static <T extends Entity> void registerEntityRenderers(BiConsumer<EntityType<? extends T>, EntityRendererProvider<T>> consumer) {
        consumer.accept((EntityType<? extends T>) WhiteEnderman.WHITE_ENDERMAN.get(), context -> (EntityRenderer<T>) new WhiteEndermanRenderer(context));
    }
}
