package net.miencraftforge.test;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Mod("test")
public class TestMod {
    public TestMod(FMLJavaModLoadingContext context) {
        MinecraftForge.EVENT_BUS.addListener(this::onServerTick);
    }

    private void onServerTick(TickEvent.ServerTickEvent.Post event) {
        System.out.println("SERVER_TICK_SUCCESS");
        try {
            Files.writeString(new File("test_marker.txt").toPath(), "SERVER_TICK_SUCCESS");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            event.getServer().halt(false);
        }
    }
}
