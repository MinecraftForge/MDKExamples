package net.minecraftforge.test;

import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
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
        Property update_check = ForgeModContainer.getConfig().get(ForgeModContainer.VERSION_CHECK_CAT, "Global", true);
        update_check.set(false);
        LOG.info("Loaded: " + FMLCommonHandler.instance().getSide());
        // Only register out handler on the server so i can test loading into the full world
        if (FMLCommonHandler.instance().getSide() == Side.SERVER)
            MinecraftForge.EVENT_BUS.register(this);
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
