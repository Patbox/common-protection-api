package eu.pb4.common.protection.impl;

import com.mojang.authlib.GameProfile;
import eu.pb4.common.protection.api.ProtectionProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import javax.annotation.Nullable;
import java.util.*;

public final class ProtectionImpl {
    private static final Map<Identifier, ProtectionProvider> PROVIDERS_BY_ID = new HashMap<>();
    private static final List<ProtectionProvider> PROVIDERS = new ArrayList<>();

    private ProtectionImpl() {
    }

    public static boolean isAreaProtected(World world, Box pos) {
        for (var p : PROVIDERS) {
            if (p.isAreaProtected(world, pos)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isProtected(World world, BlockPos pos) {
        for (var p : PROVIDERS) {
            if (p.isProtected(world, pos)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canBreakBlock(World world, BlockPos pos, GameProfile profile, @Nullable PlayerEntity player) {
        for (var p : PROVIDERS) {
            if (!p.canBreakBlock(world, pos, profile, player)) {
                return false;
            }
        }
        return true;
    }

    public static boolean canExplodeBlock(World world, BlockPos pos, Explosion explosion, GameProfile profile, @Nullable PlayerEntity player) {
        for (var p : PROVIDERS) {
            if (!p.canExplodeBlock(world, pos, explosion, profile, player)) {
                return false;
            }
        }
        return true;
    }

    public static boolean canPlaceBlock(World world, BlockPos pos, GameProfile profile, @Nullable PlayerEntity player) {
        for (var p : PROVIDERS) {
            if (!p.canPlaceBlock(world, pos, profile, player)) {
                return false;
            }
        }
        return true;
    }

    public static boolean canInteractBlock(World world, BlockPos pos, GameProfile profile, @Nullable PlayerEntity player) {
        for (var p : PROVIDERS) {
            if (!p.canInteractBlock(world, pos, profile, player)) {
                return false;
            }
        }
        return true;
    }

    public static boolean canInteractEntity(World world, Entity entity, GameProfile profile, @Nullable PlayerEntity player) {
        for (var p : PROVIDERS) {
            if (!p.canInteractEntity(world, entity, profile, player)) {
                return false;
            }
        }
        return true;
    }

    public static boolean canDamageEntity(World world, Entity entity, GameProfile profile, @Nullable PlayerEntity player) {
        for (var p : PROVIDERS) {
            if (!p.canDamageEntity(world, entity, profile, player)) {
                return false;
            }
        }
        return true;
    }

    public static ProtectionProvider register(Identifier identifier, ProtectionProvider provider) {
        if (PROVIDERS_BY_ID.containsKey(identifier)) {
            throw new IllegalArgumentException(identifier + " is already registered ProtectionProvider!");
        }

        PROVIDERS_BY_ID.put(identifier, provider);
        PROVIDERS.add(provider);

        return provider;
    }

    public static ProtectionProvider getProvider(Identifier identifier) {
        return PROVIDERS_BY_ID.get(identifier);
    }

    public static Collection<Identifier> getProviderIds() {
        return PROVIDERS_BY_ID.keySet();
    }

    public static void remove(Identifier identifier) {
        PROVIDERS.remove(PROVIDERS_BY_ID.remove(identifier));
    }
}
