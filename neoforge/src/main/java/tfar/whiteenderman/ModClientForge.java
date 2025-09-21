package tfar.whiteenderman;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModClientForge {

    public static void init(IEventBus bus) {
        bus.addListener(ModClientForge::renderers);
    }

    static void renderers(EntityRenderersEvent.RegisterRenderers event) {
        ModClient.registerEntityRenderers();
    }

}
