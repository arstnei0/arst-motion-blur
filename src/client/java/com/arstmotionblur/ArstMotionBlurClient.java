package com.arstmotionblur;

import com.arstmotionblur.config.ArstMotionBlurConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class ArstMotionBlurClient implements ClientModInitializer {
    private final MinecraftClient client = MinecraftClient.getInstance();
    public static String ID = "motionblur";

    private float currentBlur;

    private final ManagedShaderEffect motionblur = ShaderEffectManager.getInstance().manage(new Identifier(ID, "shaders/post/motion_blur.json"), shader -> shader.setUniformValue("BlendFactor", getBlur()));

    private static KeyBinding toggleKeyBinding;
    private static KeyBinding increaseStrengthKeyBinding;
    private static KeyBinding decreaseStrengthKeyBinding;

    private void sendBlurStrength() {
        if (client.player != null) {
            client.player.sendMessage(Text.literal("Motion Blur Strength: " + ArstMotionBlurConfig.blur + "%"), true);
        }
    }

    private void sendBlurEnabled() {
        if (client.player != null) {
            client.player.sendMessage(Text.literal("Motion Blur " + (ArstMotionBlurConfig.enabled ? "enabled" : "disabled") + "."), true);
        }
    }

    @Override
    public void onInitializeClient() {
        toggleKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.arst-motion-blur.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_M,
                "category.arst-motion-blur.blur"
        ));
        increaseStrengthKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.arst-motion-blur.increase",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                "category.arst-motion-blur.blur"
        ));
        decreaseStrengthKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.arst-motion-blur.decrease",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                "category.arst-motion-blur.blur"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleKeyBinding.wasPressed()) {
                ArstMotionBlurConfig.enabled = !ArstMotionBlurConfig.enabled;
                sendBlurEnabled();
            } else if (increaseStrengthKeyBinding.wasPressed()) {
                ArstMotionBlurConfig.setBlur(ArstMotionBlurConfig.blur + 10);
                sendBlurStrength();
            } else if (decreaseStrengthKeyBinding.wasPressed()) {
                ArstMotionBlurConfig.setBlur(ArstMotionBlurConfig.blur - 10);
                sendBlurStrength();
            }
        });
        MidnightConfig.init("arst-motion-blur", ArstMotionBlurConfig.class);

        ShaderEffectRenderCallback.EVENT.register((deltaTick) -> {
            if (ArstMotionBlurConfig.enabled) {
                this.client.getProfiler().push("arst-motion-blur");

                if (currentBlur != getBlur()) {
                    motionblur.setUniformValue("BlendFactor", getBlur());
                    currentBlur = getBlur();
                }
                motionblur.render(deltaTick);

                this.client.getProfiler().pop();
            }
        });
    }

    public float getBlur() {
        return Math.min(ArstMotionBlurConfig.blur, 99) / 100F;
    }
}