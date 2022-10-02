package nl.enjarai.client_paintings.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import nl.enjarai.client_paintings.ClientPaintings;

@Modmenu(modId = ClientPaintings.MOD_ID)
@Config(name = "client-paintings-config", wrapperName = "ModConfig")
public class ModConfigModel {
    public boolean enableCustomPaintings = true;
    public boolean includeVanillaPaintings = true;
}
