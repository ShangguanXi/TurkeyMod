package turkey;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import turkey.entity.TurkeyEntityModel;
import turkey.entity.TurkeyEntityRender;

@Environment(EnvType.CLIENT)
@SuppressWarnings("deprecation")
public class ClientMain implements ClientModInitializer {
    public static final EntityModelLayer TurkeyEntityLayer = new EntityModelLayer(Main.id("turkey"), "main");


    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(TurkeyEntityLayer, TurkeyEntityModel::getTexturedModelData);
        EntityRendererRegistry.INSTANCE.register(Main.turkey, TurkeyEntityRender::new);

    }
}
