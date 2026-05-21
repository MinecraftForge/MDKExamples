package net.minecraftforge.example;

import net.minecraftforge.example.mixins.ExampleMixin;

public interface MinecraftCanaryGetter {
    /**
     * @see ExampleMixin#mdkexamples_mixins_fg7$getCanary()
     */
    default long mdkexamples_mixins_fg7$getCanary() {
        throw new AssertionError("Implemented in ExampleMixin");
    }
}
