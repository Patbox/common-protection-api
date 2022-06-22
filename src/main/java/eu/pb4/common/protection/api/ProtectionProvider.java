package eu.pb4.common.protection.api;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import javax.annotation.Nullable;

public interface ProtectionProvider {
    GameProfile UNKNOWN = new GameProfile(Util.NIL_UUID, "[Unknown]");

    boolean isProtected(World world, BlockPos pos);

    boolean isAreaProtected(World world, Box area);

    default boolean canBreakBlock(World world, BlockPos pos, GameProfile profile, @Nullable PlayerEntity player) {
        return !isProtected(world, pos);
    }

    default boolean canExplodeBlock(World world, BlockPos pos, Explosion explosion, GameProfile profile, @Nullable PlayerEntity player) {
        return canBreakBlock(world, pos, profile, player);
    }

    default boolean canPlaceBlock(World world, BlockPos pos, GameProfile profile, @Nullable PlayerEntity player) {
        return canBreakBlock(world, pos, profile, player);
    }

    default boolean canInteractBlock(World world, BlockPos pos, GameProfile profile, @Nullable PlayerEntity player) {
        return canBreakBlock(world, pos, profile, player);
    }

    default boolean canInteractEntity(World world, Entity entity, GameProfile profile, @Nullable PlayerEntity player) {
        return canDamageEntity(world, entity, profile, player);
    }

    default boolean canDamageEntity(World world, Entity entity, GameProfile profile, @Nullable PlayerEntity player) {
        return !isProtected(world, entity.getBlockPos());
    }
}
