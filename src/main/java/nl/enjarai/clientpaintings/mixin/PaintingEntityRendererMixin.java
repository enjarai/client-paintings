package nl.enjarai.clientpaintings.mixin;

import nl.enjarai.clientpaintings.ClientPaintings;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PaintingEntityRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.UUID;

@Mixin(PaintingEntityRenderer.class)
public abstract class PaintingEntityRendererMixin {
	private UUID clientpaintings$uuid;
	private VertexConsumerProvider clientpaintings$vertexConsumerProvider;

	@Inject(
			method = "render(Lnet/minecraft/entity/decoration/painting/PaintingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
			at = @At(value = "HEAD")
	)
	private void clientpaintings$captureVars(PaintingEntity paintingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
		clientpaintings$uuid = paintingEntity.getUuid();
		this.clientpaintings$vertexConsumerProvider = vertexConsumerProvider;
	}

	@ModifyArgs(
			method = "render(Lnet/minecraft/entity/decoration/painting/PaintingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PaintingEntityRenderer;renderPainting(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/entity/decoration/painting/PaintingEntity;IILnet/minecraft/client/texture/Sprite;Lnet/minecraft/client/texture/Sprite;)V")
	)
	private void clientpaintings$init(Args args) {
		if (clientpaintings$uuid == null || clientpaintings$vertexConsumerProvider == null) {
			return;
		}

		var originalSprite = (Sprite) args.get(5);
		var painting = ClientPaintings.PAINTING_MANAGER
				.getPaintingFromUUID(clientpaintings$uuid, originalSprite.getContents().getWidth(), originalSprite.getContents().getHeight());

		if (painting == null) {
			return;
		}

		var sprite = painting.getSprite();
		var backSprite = painting.getBackSprite();

		args.set(1, clientpaintings$vertexConsumerProvider.getBuffer(RenderLayer.getEntitySolid(sprite.getAtlasId())));
		args.set(5, sprite);
		args.set(6, backSprite);

		clientpaintings$uuid = null;
		clientpaintings$vertexConsumerProvider = null;
	}
}
