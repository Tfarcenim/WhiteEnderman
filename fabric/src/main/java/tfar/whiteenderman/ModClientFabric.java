package tfar.whiteenderman;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ModClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModClient.registerEntityRenderers(EntityRendererRegistry::register);
    }
}
