package tfar.whiteenderman;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModClientForge {

    public static void init(IEventBus bus) {
        bus.addListener(ModClientForge::renderers);
    }

    static void renderers(EntityRenderersEvent.RegisterRenderers event) {
        ModClient.registerEntityRenderers(event::registerEntityRenderer);
    }

}
