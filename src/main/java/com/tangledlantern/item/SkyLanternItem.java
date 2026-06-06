package com.tangledlantern.item;

import com.tangledlantern.entity.FloatingLanternEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SkyLanternItem extends Item {
    public SkyLanternItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            FloatingLanternEntity lantern = FloatingLanternEntity.create(world);
            Vec3d look = user.getRotationVector();
            double spawnX = user.getX() + look.x * 1.2;
            double spawnY = user.getEyeY();
            double spawnZ = user.getZ() + look.z * 1.2;
            lantern.setPosition(spawnX, spawnY, spawnZ);
            world.spawnEntity(lantern);

            world.playSound(null, user.getBlockPos(),
                SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 0.4f, 1.3f);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        return TypedActionResult.success(stack, world.isClient());
    }
}
