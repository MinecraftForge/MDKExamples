package net.minecraftforge.example;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("examplemod")
public final class ExampleMod {
    public ExampleMod(FMLJavaModLoadingContext context) {
        System.exit(0);
    }
}
