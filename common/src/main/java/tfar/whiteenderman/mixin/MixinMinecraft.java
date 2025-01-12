package tfar.whiteenderman.mixin;

import tfar.whiteenderman.WhiteEnderman;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(CallbackInfo info) {
        
        WhiteEnderman.LOG.info("This line is printed by an example mod common mixin!");
        WhiteEnderman.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}