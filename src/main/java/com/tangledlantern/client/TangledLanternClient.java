package com.tangledlantern.client;

import com.tangledlantern.client.entity.FloatingLanternEntityModel;
import com.tangledlantern.client.entity.FloatingLanternEntityRenderer;
import com.tangledlantern.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class TangledLanternClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(
            ModModelLayers.FLOATING_LANTERN,
            FloatingLanternEntityModel::getTexturedModelData
        );
        EntityRendererRegistry.register(
            ModEntities.FLOATING_LANTERN,
            FloatingLanternEntityRenderer::new
        );
    }
}
