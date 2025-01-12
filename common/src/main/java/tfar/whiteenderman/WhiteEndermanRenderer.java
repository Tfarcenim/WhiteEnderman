package tfar.whiteenderman;

import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.EnderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.EnderMan;

public class WhiteEndermanRenderer extends EndermanRenderer {
    private static final ResourceLocation ENDERMAN_LOCATION = WhiteEnderman.id("textures/entity/enderman.png");


    public WhiteEndermanRenderer(EntityRendererProvider.Context $$0) {
        super($$0);
        layers.removeIf(enderManEndermanModelRenderLayer -> enderManEndermanModelRenderLayer instanceof EnderEyesLayer<EnderMan>);
        addLayer(new WhiteEnderEyesLayer<>(this));
    }

    public ResourceLocation getTextureLocation(EnderMan $$0) {
        return ENDERMAN_LOCATION;
    }

}
