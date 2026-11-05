package net.minecraftforge.test;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.EnumSet;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Mod(modid = "test")
public class TestMod implements ITickHandler {
    private static final Logger LOG = LogManager.getLogManager().getLogger("test");
    public TestMod() {
        LOG.info("Loaded: " + FMLCommonHandler.instance().getSide());
        // Only register out handler on the server so I can test loading into the full world
        if (FMLCommonHandler.instance().getSide() == Side.SERVER)
            TickRegistry.registerTickHandler(this, Side.SERVER);
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        LOG.info("SERVER_TICK_SUCCESS " + new File("test_marker.txt").getAbsolutePath());
        try {
            Files.write(new File("test_marker.txt").toPath(), "SERVER_TICK_SUCCESS".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            FMLCommonHandler.instance().getMinecraftServerInstance().initiateShutdown();
        }
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel() {
        return "test";
    }
}
