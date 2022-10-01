package nl.enjarai.client_paintings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientPaintings implements ClientModInitializer {
	public static final String MOD_ID = "client_paintings";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ClientPaintingManager PAINTING_MANAGER;

	@Override
	public void onInitializeClient() {
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(PAINTING_MANAGER = new ClientPaintingManager());

//		ClientSpriteRegistryCallback.event(ClientPaintingManager.SPRITE_ATLAS_ID).register((atlasTexture, registry) -> {
//			PAINTING_MANAGER.paintings.values().forEach(clientPainting -> registry.register(clientPainting.getTexture()));
//		});

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
