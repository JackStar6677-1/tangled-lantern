package com.tangledlantern.client.entity;

import com.tangledlantern.entity.FloatingLanternEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;

public class FloatingLanternEntityModel<T extends FloatingLanternEntity> extends SinglePartEntityModel<T> {
    private final ModelPart root;
    private final ModelPart lantern;

    public FloatingLanternEntityModel(ModelPart root) {
        this.root = root;
        this.lantern = root.getChild("lantern");
    }

    /*
     * UV layout for lantern box (10w x 16h x 10d) starting at UV(0,0):
     *   TOP:   (10, 0)→(20,10)   BOT: (20, 0)→(30,10)
     *   WEST:  ( 0,10)→(10,26)   NORTH:(10,10)→(20,26)
     *   EAST:  (20,10)→(30,26)   SOUTH:(30,10)→(40,26)
     * Texture size: 64x64
     */
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();
        root.addChild("lantern",
            ModelPartBuilder.create()
                .uv(0, 0)
                .cuboid(-5, -8, -5, 10, 16, 10),
            ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance,
                          float animationProgress, float headYaw, float headPitch) {
        // Slow gentle sway
        lantern.pivotY = (float) Math.sin(animationProgress * 0.05f) * 1.5f;
    }
}
