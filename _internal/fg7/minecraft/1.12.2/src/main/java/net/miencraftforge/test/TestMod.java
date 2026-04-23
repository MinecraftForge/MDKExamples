package net.miencraftforge.test;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Mod(modid = "test")
public class TestMod {
    public TestMod() {
        // Only register out handler on the server so i can test loading into the full world
        if (FMLCommonHandler.instance().getSide() == Side.SERVER)
            MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        System.out.println("SERVER_TICK_SUCCESS");
        try {
            Files.write(new File("test_marker.txt").toPath(), "SERVER_TICK_SUCCESS".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            FMLCommonHandler.instance().getMinecraftServerInstance().initiateShutdown();
        }
    }
}
