package net.minecraftforge.example.mixins;

import net.minecraft.client.Minecraft;
import net.minecraftforge.example.MinecraftCanaryGetter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Minecraft.class)
public abstract class ExampleMixin implements MinecraftCanaryGetter {
    @Shadow
    private @Final long canary;

    @Override
    public long mdkexamples_mixins_fg7$getCanary() {
        return canary;
    }
}
