package tfar.whiteenderman;

import net.minecraft.client.renderer.entity.EntityRenderers;

public class ModClient {

    public static void registerEntityRenderers() {
        EntityRenderers.register(WhiteEnderman.WHITE_ENDERMAN.get(), WhiteEndermanRenderer::new);
    }
}
