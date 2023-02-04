package turkey.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import turkey.ClientMain;

public class TurkeyEntityRender extends MobEntityRenderer<TurkeyEntity, TurkeyEntityModel<TurkeyEntity>> {
    public TurkeyEntityRender(EntityRendererFactory.Context context) {
        super(context, new TurkeyEntityModel<>(context.getPart(ClientMain.TurkeyEntityLayer)),0.3F);
    }
    private static final Identifier TEXTURE = new Identifier("turkey/textures/entity/turkey.png");

    @Override
    public Identifier getTexture(TurkeyEntity entity) {
        return TEXTURE;
    }

    protected float getAnimationProgress(TurkeyEntity TurkeyEntity, float f) {
        float g = MathHelper.lerp(f, TurkeyEntity.prevFlapProgress, TurkeyEntity.flapProgress);
        float h = MathHelper.lerp(f, TurkeyEntity.prevMaxWingDeviation, TurkeyEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0F) * h;
    }
}
