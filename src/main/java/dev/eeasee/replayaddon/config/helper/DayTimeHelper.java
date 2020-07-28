package dev.eeasee.replayaddon.config.helper;

import fi.dy.masa.malilib.config.options.ConfigInteger;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

public class DayTimeHelper {
    private static Integer realDayTime = null;

    public static void onFakeDayTimeChanged(ConfigInteger config) {
        int tweaked = config.getIntegerValue();
        ClientWorld clientWorld = MinecraftClient.getInstance().world;
        if (clientWorld == null) {
            return;
        }
        if (tweaked == -1) {
            if (realDayTime != null){
                clientWorld.setTimeOfDay(realDayTime);
            }
        } else {
            clientWorld.setTimeOfDay(tweaked * (-1));
        }
    }

    public static void setRealDayTime(int time) {
        realDayTime = time;
    }
}
