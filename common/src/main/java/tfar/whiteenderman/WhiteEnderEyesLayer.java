package tfar.whiteenderman;

import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WhiteEnderEyesLayer<T extends LivingEntity> extends EyesLayer<T, EndermanModel<T>> {
   private static final RenderType ENDERMAN_EYES = RenderType.eyes(WhiteEnderman.id("textures/entity/enderman_eyes.png"));

   public WhiteEnderEyesLayer(RenderLayerParent<T, EndermanModel<T>> p_116964_) {
      super(p_116964_);
   }

   public RenderType renderType() {
      return ENDERMAN_EYES;
   }
}