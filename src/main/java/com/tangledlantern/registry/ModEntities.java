package com.tangledlantern.registry;

import com.tangledlantern.TangledLantern;
import com.tangledlantern.entity.FloatingLanternEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<FloatingLanternEntity> FLOATING_LANTERN = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier.of(TangledLantern.MOD_ID, "floating_lantern"),
        EntityType.Builder.<FloatingLanternEntity>create(FloatingLanternEntity::new, SpawnGroup.MISC)
            .dimensions(0.6f, 0.9f)
            .maxTrackingRange(8)
            .trackingTickInterval(3)
            .build()
    );

    public static void register() {}
}
