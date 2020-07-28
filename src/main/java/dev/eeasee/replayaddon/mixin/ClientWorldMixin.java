package dev.eeasee.replayaddon.mixin;

import dev.eeasee.replayaddon.config.Configs;
import dev.eeasee.replayaddon.config.helper.WeatherEnum;
import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkManager;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.BiFunction;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin extends World{
    protected ClientWorldMixin(LevelProperties levelProperties, DimensionType dimensionType, BiFunction<World, Dimension, ChunkManager> chunkManagerProvider, Profiler profiler, boolean isClient) {
        super(levelProperties, dimensionType, chunkManagerProvider, profiler, isClient);
    }

    @Override
    public float getThunderGradient(float f) {
        if (!Configs.TWEAKED_WEATHER.isModified()) {
            return MathHelper.lerp(f, this.thunderGradientPrev, this.thunderGradient) * this.getRainGradient(f);
        }
        IConfigOptionListEntry tweaked = Configs.TWEAKED_WEATHER.getOptionListValue();
        if (tweaked == WeatherEnum.THUNDER || tweaked == WeatherEnum.DRY_THUNDER) {
            return 1.0F;
        } else {
            return 0.0F;
        }
    }

    @Override
    public float getRainGradient(float f) {
        if (!Configs.TWEAKED_WEATHER.isModified()) {
            return MathHelper.lerp(f, this.rainGradientPrev, this.rainGradient);
        }
        IConfigOptionListEntry tweaked = Configs.TWEAKED_WEATHER.getOptionListValue();
        if (tweaked == WeatherEnum.RAIN || tweaked == WeatherEnum.THUNDER) {
            return 1.0F;
        } else {
            return 0.0F;
        }
    }

}
