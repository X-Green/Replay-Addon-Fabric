package dev.eeasee.replayaddon.event;

import dev.eeasee.replayaddon.config.Configs;
import dev.eeasee.replayaddon.config.helper.DayTimeHelper;
import net.fabricmc.api.ModInitializer;

public class Init implements ModInitializer {
    @Override
    public void onInitialize() {
        registerCallbacks();
    }

    private void registerCallbacks() {
        Configs.TWEAKED_DAY_TIME.setValueChangeCallback(DayTimeHelper::onFakeDayTimeChanged);
    }

}
