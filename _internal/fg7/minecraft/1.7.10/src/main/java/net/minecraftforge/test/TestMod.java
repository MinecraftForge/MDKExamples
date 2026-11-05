package net.minecraftforge.test;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.ForgeModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Mod(modid = "test")
public class TestMod {
    private static final Logger LOG = LogManager.getLogger();
    public TestMod() {
        ForgeModContainer.disableVersionCheck = true;
        LOG.info("Loaded: " + FMLCommonHandler.instance().getSide());
        // Only register out handler on the server so I can test loading into the full world
        if (FMLCommonHandler.instance().getSide() == Side.SERVER)
            FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        LOG.info("SERVER_TICK_SUCCESS " + new File("test_marker.txt").getAbsolutePath());
        try {
            Files.write(new File("test_marker.txt").toPath(), "SERVER_TICK_SUCCESS".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            FMLCommonHandler.instance().getMinecraftServerInstance().initiateShutdown();
        }
    }
}
