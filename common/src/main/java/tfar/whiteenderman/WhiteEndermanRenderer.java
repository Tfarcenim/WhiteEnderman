package tfar.whiteenderman;

import com.mojang.blaze3d.vertex.PoseStack;
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

    private static final float SCALE = 1.25f;

    @Override
    protected void scale(EnderMan pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(SCALE, SCALE, SCALE);
    }

    @Override
    public ResourceLocation getTextureLocation(EnderMan $$0) {
        return ENDERMAN_LOCATION;
    }

}
