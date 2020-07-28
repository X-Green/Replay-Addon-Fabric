package dev.eeasee.replayaddon.mixin;

import dev.eeasee.replayaddon.config.Configs;
import dev.eeasee.replayaddon.config.helper.DayTimeHelper;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.listener.ClientPlayPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

    @Redirect(method = "onWorldTimeUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;setTimeOfDay(J)V"))
    private void tweakedDayTimeUpdate(ClientWorld clientWorld, long time) {
        int tweaked = Configs.TWEAKED_DAY_TIME.getIntegerValue();
        if (tweaked == -1) {
            clientWorld.setTimeOfDay(time);
        } else {
            clientWorld.setTimeOfDay(-1 * tweaked);
        }
        DayTimeHelper.setRealDayTime((int) time);
    }
}
