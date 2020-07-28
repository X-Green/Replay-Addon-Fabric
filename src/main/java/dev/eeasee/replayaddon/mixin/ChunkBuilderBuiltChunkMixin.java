package dev.eeasee.replayaddon.mixin;

import dev.eeasee.replayaddon.config.Configs;
import net.minecraft.client.render.chunk.ChunkBuilder.BuiltChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BuiltChunk.class)
public abstract class ChunkBuilderBuiltChunkMixin {
    @Inject(method = "shouldBuild", at = @At("HEAD"), cancellable = true)
    private void should(CallbackInfoReturnable<Boolean> cir) {
        if (Configs.FORCED_CHUNK_BUILDING.getBooleanValue()) {
            cir.setReturnValue(true);
        }
    }
}
