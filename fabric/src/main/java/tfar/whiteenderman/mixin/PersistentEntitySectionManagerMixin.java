package tfar.whiteenderman.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraft.world.level.entity.PersistentEntitySectionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.whiteenderman.WhiteEnderman;

@Mixin(PersistentEntitySectionManager.class)
public class PersistentEntitySectionManagerMixin<T extends EntityAccess> {
    @Inject(method = "addEntity",at = @At("HEAD"),cancellable = true)
    private void onEntityJoinLevel(T entity, boolean worldGenSpawned, CallbackInfoReturnable<Boolean> cir) {
        if (WhiteEnderman.onEntityJoinLevel((Entity) entity,((Entity)entity).level(),worldGenSpawned)) {
            cir.setReturnValue(false);
        }
    }
}
