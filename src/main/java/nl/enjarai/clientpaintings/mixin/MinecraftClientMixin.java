package nl.enjarai.clientpaintings.mixin;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.resource.ResourceType;
import nl.enjarai.clientpaintings.ClientPaintingManager;
import nl.enjarai.clientpaintings.ClientPaintings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/texture/PaintingManager;<init>(Lnet/minecraft/client/texture/TextureManager;)V"
            )
    )
    private void clientpaintings$initPaintingManager(RunArgs args, CallbackInfo ci) {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES)
                .registerReloadListener(ClientPaintings.PAINTING_MANAGER = new ClientPaintingManager());
    }
}
