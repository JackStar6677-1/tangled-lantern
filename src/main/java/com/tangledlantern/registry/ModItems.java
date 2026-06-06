package com.tangledlantern.registry;

import com.tangledlantern.TangledLantern;
import com.tangledlantern.item.SkyLanternItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SKY_LANTERN = Registry.register(
        Registries.ITEM,
        Identifier.of(TangledLantern.MOD_ID, "sky_lantern"),
        new SkyLanternItem(new Item.Settings().maxCount(16))
    );

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.add(SKY_LANTERN));
    }
}
