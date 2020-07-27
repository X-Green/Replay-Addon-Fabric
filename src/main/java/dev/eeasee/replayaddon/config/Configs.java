package dev.eeasee.replayaddon.config;

import com.google.common.collect.ImmutableList;
import dev.eeasee.replayaddon.config.optionlists.WeatherEnum;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.*;

import java.util.ArrayList;
import java.util.List;

public class Configs {
    public final static ConfigInteger TWEAKED_DAY_TIME;
    public final static ConfigOptionList TWEAKED_WEATHER;

    static {
        TWEAKED_DAY_TIME = Category.VISUAL.add(new ConfigInteger("replay_addon.config.visual.day_time", -1, -1, 23999, "Input -1 as default value"));
        TWEAKED_WEATHER = Category.VISUAL.add(new ConfigOptionList("replay_addon.config.visual.weather", WeatherEnum.DEFAULT, "..."));
    }

    public enum Category {
        VISUAL("replay_addon.gui.setting_screen.visual");

        private final String key;
        private final List<IConfigBase> configs;

        private Category(String key) {
            this.key = key;
            configs = new ArrayList<>();
        }

        protected <T extends IConfigBase> T add(T config) {
            this.configs.add(config);
            return config;
        }

        public List<IConfigBase> getConfigs() {
            return ImmutableList.copyOf(this.configs);
        }

        public String getKey() {
            return this.key;
        }
    }

}
