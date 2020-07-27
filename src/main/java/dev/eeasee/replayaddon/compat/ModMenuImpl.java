package dev.eeasee.replayaddon.compat;

import dev.eeasee.replayaddon.ReplayAddon;
import dev.eeasee.replayaddon.config.GuiConfig;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public String getModId() {
        return ReplayAddon.MOD_ID;
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return (screen) -> {
            GuiConfig guiConfig = new GuiConfig();
            guiConfig.setParent(screen);
            return guiConfig;
        };
    }
}
