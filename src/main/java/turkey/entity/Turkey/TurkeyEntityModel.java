package turkey.entity.Turkey;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class TurkeyEntityModel<T extends Entity> extends AnimalModel<T> {
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart rightWing;
	private final ModelPart leftWing;
	public TurkeyEntityModel(ModelPart root) {
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.rightWing = root.getChild("rightWing");
		this.leftWing = root.getChild("leftWing");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData rightLeg = modelPartData.addChild("rightLeg", ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 19.0F, 1.0F));

		ModelPartData leftLeg = modelPartData.addChild("leftLeg", ModelPartBuilder.create().uv(26, 0).mirrored().cuboid(-2.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, 19.0F, 1.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(0, 23).cuboid(-5.0F, -5.0F, 0.0F, 10.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -11.0F, 5.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r2 = body.addChild("cube_r2", ModelPartBuilder.create().uv(0, 9).cuboid(-3.0F, -3.0F, 4.0F, 6.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(18, 4).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(14, 0).cuboid(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 17.0F, -4.0F));

		ModelPartData rightWing = modelPartData.addChild("rightWing", ModelPartBuilder.create().uv(24, 13).cuboid(-1.0F, 1.0F, -3.0F, 1.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 13.0F, 0.0F));

		ModelPartData leftWing = modelPartData.addChild("leftWing", ModelPartBuilder.create().uv(24, 13).mirrored().cuboid(0.0F, 1.0F, -3.0F, 1.0F, 4.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(3.0F, 13.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}
	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.body, this.rightLeg, this.leftLeg, this.rightWing, this.leftWing);
	}
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.head.pitch = headPitch * 0.017453292F;
		this.head.yaw = headYaw * 0.017453292F;
		this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
		this.rightWing.roll = animationProgress;
		this.leftWing.roll = -animationProgress;
	}
}