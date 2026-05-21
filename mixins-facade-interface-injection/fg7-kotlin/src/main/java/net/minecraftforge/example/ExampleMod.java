package net.minecraftforge.example;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("examplemod")
public final class ExampleMod {
    public ExampleMod(FMLJavaModLoadingContext context) {
        LogUtils.getLogger()
                .info("Canary: {}", Minecraft.getInstance().mdkexamples_mixins_fg7$getCanary());
    }
}
