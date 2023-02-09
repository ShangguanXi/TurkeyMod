package turkey;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import turkey.entity.Turkey.TurkeyEntityModel;
import turkey.entity.Turkey.TurkeyEntityRender;

@Environment(EnvType.CLIENT)
@SuppressWarnings("deprecation")
public class ClientMain implements ClientModInitializer {
    public static final EntityModelLayer TurkeyEntityLayer = new EntityModelLayer(Main.id("turkey"), "main");


    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(TurkeyEntityLayer, TurkeyEntityModel::getTexturedModelData);
        EntityRendererRegistry.INSTANCE.register(Main.turkey, TurkeyEntityRender::new);
        EntityRendererRegistry.INSTANCE.register(Main.turkey_egg_entity, FlyingItemEntityRenderer::new);

    }
}
