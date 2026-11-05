package net.minecraftforge.example.mixins;

import net.minecraft.client.main.Main;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Main.class)
public class ExampleMixin {
    @Shadow @Final
    static Logger LOGGER; // This shows that the remapping of shaded fields works fine

    // This gets added to the refmap as its a string representation of an obfed method
    @ModifyVariable(method = {"stringHasValue(Ljava/lang/String;)Z"}, at = @At("HEAD"), index = 0)
    private static String modifyArgs(String arg) {
        return "EXAMPLE_ARG".equals(arg) ? null : arg;
    }

    // This just prints a ton of lines to the log to make it easy to see if we patched corrently
    // It also invokes an obfuscated method (stringHasValue) and references an obfuscated field (LOGGER) to make sure obf works correct
    @Inject(method = "main", at = @At("HEAD"))
    private static void main(String[] args, CallbackInfo callbackInfo) {
        for (int x = 0; x < 100; x++)
            LOGGER.info(" EXAMPLE MIXIN WORKED ================================ stringHasValue modification " + (stringHasValue("EXAMPLE_ARG") ? "failed" : "success"));
    }
    

    // This gives us access to the obfuscated method
    @Shadow
    private static boolean stringHasValue(String value) {
        return false;
    }
}
