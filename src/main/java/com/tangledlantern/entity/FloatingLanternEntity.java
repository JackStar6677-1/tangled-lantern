package com.tangledlantern.entity;

import com.tangledlantern.registry.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FloatingLanternEntity extends Entity {
    // 5 minutes at 20 TPS
    private static final int MAX_LIFETIME = 6000;

    public FloatingLanternEntity(EntityType<?> type, World world) {
        super(type, world);
        this.noClip = true;
        this.setNoGravity(true);
    }

    public static FloatingLanternEntity create(World world) {
        return new FloatingLanternEntity(ModEntities.FLOATING_LANTERN, world);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.age >= MAX_LIFETIME) {
            this.discard();
            return;
        }

        // Slow upward float with gentle side drift
        double upSpeed = 0.035;
        double driftX = Math.sin(this.age * 0.04) * 0.007;
        double driftZ = Math.cos(this.age * 0.03) * 0.007;

        this.setVelocity(new Vec3d(driftX, upSpeed, driftZ));
        this.move(MovementType.SELF, this.getVelocity());

        // Small flame particles visible on client
        if (this.getWorld().isClient && this.age % 3 == 0) {
            this.getWorld().addParticle(
                ParticleTypes.SMALL_FLAME,
                this.getX() + (this.random.nextDouble() - 0.5) * 0.25,
                this.getY() + 0.05,
                this.getZ() + (this.random.nextDouble() - 0.5) * 0.25,
                0, 0.008, 0
            );
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }
}
