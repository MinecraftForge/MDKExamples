package net.minecraftforge.example;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("examplemod")
public final class ExampleMod {
    public ExampleMod(FMLJavaModLoadingContext context) {
    	Minecraft.LOGGER.info("Able to access a private field");
    }
}
