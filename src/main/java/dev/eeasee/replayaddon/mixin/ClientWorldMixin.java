package dev.eeasee.replayaddon.mixin;

import dev.eeasee.replayaddon.config.Configs;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.dimension.Dimension;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin implements IWorld {
    @Override
    public float getMoonSize() {
        int tweakedDayTime = Configs.TWEAKED_DAY_TIME.getIntegerValue();
        return Dimension.MOON_PHASE_TO_SIZE[this.getDimension().getMoonPhase((tweakedDayTime == -1) ? this.getLevelProperties().getTimeOfDay() : tweakedDayTime)];
    }

    @Override
    public float getSkyAngle(float tickDelta) {
        int tweakedDayTime = Configs.TWEAKED_DAY_TIME.getIntegerValue();
        return this.getDimension().getSkyAngle((tweakedDayTime == -1) ? this.getLevelProperties().getTimeOfDay() : tweakedDayTime, tickDelta);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public int getMoonPhase() {
        int tweakedDayTime = Configs.TWEAKED_DAY_TIME.getIntegerValue();
        return this.getDimension().getMoonPhase((tweakedDayTime == -1) ? this.getLevelProperties().getTimeOfDay() : tweakedDayTime);
    }

}
