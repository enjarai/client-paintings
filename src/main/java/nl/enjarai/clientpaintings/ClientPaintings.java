package nl.enjarai.clientpaintings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import nl.enjarai.cicada.api.conversation.ConversationManager;
import nl.enjarai.cicada.api.util.CicadaEntrypoint;
import nl.enjarai.cicada.api.util.JsonSource;
import nl.enjarai.cicada.api.util.ProperLogger;
import org.slf4j.Logger;

public class ClientPaintings implements ClientModInitializer, CicadaEntrypoint {
	public static final String MOD_ID = "clientpaintings";
	public static final Logger LOGGER = ProperLogger.getLogger(MOD_ID);
	public static ClientPaintingManager PAINTING_MANAGER;
	@Override
	public void onInitializeClient() {
		FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container -> {
			ResourceManagerHelper.registerBuiltinResourcePack(
					id("default"), container,
					Text.literal("Client Paintings Defaults"),
					ResourcePackActivationType.NORMAL
			);
		});
	}

	@Override
	public void registerConversations(ConversationManager conversationManager) {
		conversationManager.registerSource(
				JsonSource.fromUrl("https://raw.githubusercontent.com/enjarai/client-paintings/master/src/main/resources/cicada/clientpaintings/conversations.json")
						.or(JsonSource.fromResource("cicada/clientpaintings/conversations.json")),
				LOGGER::info
		);
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
