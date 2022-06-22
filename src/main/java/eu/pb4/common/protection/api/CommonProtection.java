package eu.pb4.common.protection.api;

import com.mojang.authlib.GameProfile;
import eu.pb4.common.protection.impl.ProtectionImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import javax.annotation.Nullable;
import java.util.Collection;


public final class CommonProtection {
    public static final GameProfile UNKNOWN = ProtectionProvider.UNKNOWN;

    private CommonProtection() {
    }

    public static boolean isAreaProtected(World world, Box area) {
        return ProtectionImpl.isAreaProtected(world, area);
    }

    public static boolean isProtected(World world, BlockPos pos) {
        return ProtectionImpl.isProtected(world, pos);
    }

    public static boolean canBreakBlock(World world, BlockPos pos, GameProfile profile, @Nullable PlayerEntity player) {
        return ProtectionImpl.canBreakBlock(world, pos, profile, player);
    }

    public static boolean canPlaceBlock(World world, BlockPos pos, GameProfile profile, @Nullable PlayerEntity player) {
        return ProtectionImpl.canPlaceBlock(world, pos, profile, player);
    }

    public static boolean canExplodeBlock(World world, BlockPos pos, Explosion explosion, GameProfile profile, @Nullable PlayerEntity player) {
        return ProtectionImpl.canExplodeBlock(world, pos, explosion, profile, player);
    }

    public static boolean canInteractBlock(World world, BlockPos pos, GameProfile profile, @Nullable PlayerEntity player) {
        return ProtectionImpl.canInteractBlock(world, pos, profile, player);
    }

    public static boolean canInteractEntity(World world, Entity entity, GameProfile profile, @Nullable PlayerEntity player) {
        return ProtectionImpl.canInteractEntity(world, entity, profile, player);
    }

    public static boolean canDamageEntity(World world, Entity entity, GameProfile profile, @Nullable PlayerEntity player) {
        return ProtectionImpl.canDamageEntity(world, entity, profile, player);
    }

    public static ProtectionProvider register(Identifier identifier, ProtectionProvider provider) {
        return ProtectionImpl.register(identifier, provider);
    }

    public static void remove(Identifier identifier) {
        ProtectionImpl.remove(identifier);
    }

    @Nullable
    public static ProtectionProvider getProvider(Identifier identifier) {
        return ProtectionImpl.getProvider(identifier);
    }

    @Nullable
    public static Collection<Identifier> getProviderIds() {
        return ProtectionImpl.getProviderIds();
    }
}
