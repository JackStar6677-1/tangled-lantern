package com.tangledlantern.client.entity;

import com.tangledlantern.client.ModModelLayers;
import com.tangledlantern.entity.FloatingLanternEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class FloatingLanternEntityRenderer extends EntityRenderer<FloatingLanternEntity> {
    private static final Identifier TEXTURE =
        Identifier.of("tangledlantern", "textures/entity/floating_lantern.png");

    private final FloatingLanternEntityModel<FloatingLanternEntity> model;

    public FloatingLanternEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new FloatingLanternEntityModel<>(ctx.getPart(ModModelLayers.FLOATING_LANTERN));
    }

    @Override
    public Identifier getTexture(FloatingLanternEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(FloatingLanternEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        float scale = 0.55f;
        // Flip Y and Z so the model faces correctly in world space
        matrices.scale(scale, -scale, -scale);

        // Slow rotation around Y axis
        float rotation = (entity.age + tickDelta) * 0.6f;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation));

        model.setAngles(entity, 0, 0, entity.age + tickDelta, 0, 0);

        // Render fully bright (emissive) so the lantern glows regardless of ambient light
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
            RenderLayer.getEntityCutoutNoCull(TEXTURE)
        );
        model.render(matrices, vertexConsumer,
            LightmapTextureManager.MAX_LIGHT_COORDINATE, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
