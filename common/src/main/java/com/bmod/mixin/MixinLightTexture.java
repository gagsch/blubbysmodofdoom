package com.bmod.mixin;

import com.bmod.util.mixinUtils.LightmapAccess;
import com.bmod.util.mixinUtils.TextureAccess;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;

@Mixin(LightTexture.class)
public class MixinLightTexture implements LightmapAccess {
    @Final
    @Shadow
    private DynamicTexture lightTexture;
    @Shadow
    private float blockLightRedFlicker;
    @Shadow
    private boolean updateLightTexture;

    @Inject(method = "<init>*", at = @At(value = "RETURN"))
    private void afterInit(GameRenderer gameRenderer, Minecraft minecraftClient, CallbackInfo ci) {
        ((TextureAccess) lightTexture).blubbysmod$enableUploadHook();
    }

    @Override
    public float blubbysmod$prevFlicker() {
        return blockLightRedFlicker;
    }

    @Override
    public boolean blubbysmod$isDirty() {
        return updateLightTexture;
    }
}
