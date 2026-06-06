package com.tangledlantern;

import com.tangledlantern.registry.ModEntities;
import com.tangledlantern.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TangledLantern implements ModInitializer {
    public static final String MOD_ID = "tangledlantern";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModEntities.register();
        ModItems.register();
    }
}
