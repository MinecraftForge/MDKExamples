package net.minecraftforge.example.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.client.main.Main;

@Mixin(Main.class)
public class ExampleMixin {
    @Overwrite
    public static void main(String[] args) {
        System.out.println("Overwrote main!");
    }
}
