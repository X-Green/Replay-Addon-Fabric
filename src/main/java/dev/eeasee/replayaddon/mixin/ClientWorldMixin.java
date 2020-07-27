package dev.eeasee.replayaddon.mixin;

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
        return Dimension.MOON_PHASE_TO_SIZE[this.getDimension().getMoonPhase(this.getLevelProperties().getTimeOfDay())];
    }

    @Override
    public float getSkyAngle(float tickDelta) {
        return this.getDimension().getSkyAngle(this.getLevelProperties().getTimeOfDay(), tickDelta);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public int getMoonPhase() {
        return this.getDimension().getMoonPhase(this.getLevelProperties().getTimeOfDay());
    }

}
