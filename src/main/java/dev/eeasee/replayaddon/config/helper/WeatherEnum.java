package dev.eeasee.replayaddon.config.helper;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum WeatherEnum implements IConfigOptionListEntry {
    DEFAULT("default", "replay_addon.enum.weather.default"),
    CLEAR("clear", "replay_addon.enum.weather.clear"),
    RAIN("rain", "replay_addon.enum.weather.rain"),
    THUNDER("thunder", "replay_addon.enum.weather.thunder"),
    DRY_THUNDER("dry_thunder", "replay_addon.enum.weather.dry_thunder")
    ;

    private final String configString;
    private final String unlocName;

    WeatherEnum(String configString, String unlocName) {
        this.configString = configString;
        this.unlocName = unlocName;
    }

    @Override
    public String getStringValue() {
        return this.configString;
    }

    @Override
    public String getDisplayName() {
        return StringUtils.translate(this.unlocName);
    }

    @Override
    public IConfigOptionListEntry cycle(boolean forward) {
        int id = this.ordinal();

        if (forward)
        {
            if (++id >= values().length)
            {
                id = 0;
            }
        }
        else
        {
            if (--id < 0)
            {
                id = values().length - 1;
            }
        }

        return values()[id % values().length];
    }

    @Override
    public IConfigOptionListEntry fromString(String value) {
        return fromStringStatic(value);
    }

    public static WeatherEnum fromStringStatic(String name)
    {
        for (WeatherEnum w : WeatherEnum.values())
        {
            if (w.configString.equalsIgnoreCase(name))
            {
                return w;
            }
        }

        return WeatherEnum.DEFAULT;
    }
}
