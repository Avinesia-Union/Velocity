package com.velocitypowered.proxy;

import com.google.common.collect.Lists;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.velocitypowered.api.util.GameProfile;

import java.net.Proxy;
import java.util.List;
import java.util.UUID;

public class AuthServiceSingleton {
    public static YggdrasilAuthenticationService authService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
    public static MinecraftSessionService sessionService = authService.createMinecraftSessionService();

    public static GameProfile authLibToVelocityProfiles(com.mojang.authlib.GameProfile input) {

        List<GameProfile.Property> result = Lists.newArrayList();
        input.getProperties().forEach((key, property) -> {
            result.add(new GameProfile.Property(property.getName(), property.getValue(), property.getSignature()));
        });
        GameProfile velocityGameProfile = new GameProfile(input.getId().toString().replaceAll("-", ""), input.getName(), result);
        System.out.println(velocityGameProfile.toString());
        return velocityGameProfile;
    }

}
