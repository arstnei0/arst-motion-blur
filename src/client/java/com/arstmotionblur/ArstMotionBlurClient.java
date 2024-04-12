package com.arstmotionblur;

import com.arstmotionblur.config.ArstMotionBlurConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import net.minecraft.util.Identifier;
import net.fabricmc.api.ClientModInitializer;

public class ArstMotionBlurClient implements ClientModInitializer {
	public static String ID = "motionblur";

	private float currentBlur;

	private final ManagedShaderEffect motionblur = ShaderEffectManager.getInstance().manage(new Identifier(ID, "shaders/post/motion_blur.json"), shader -> shader.setUniformValue("BlendFactor", getBlur()));

	@Override
	public void onInitializeClient() {
		MidnightConfig.init("arst-motion-blur", ArstMotionBlurConfig.class);
		ShaderEffectRenderCallback.EVENT.register((deltaTick) -> {
			if (ArstMotionBlurConfig.enabled) {
				if(currentBlur!=getBlur()){
					motionblur.setUniformValue("BlendFactor", getBlur());
					currentBlur=getBlur();
				}
				motionblur.render(deltaTick);
			}
		});
	}

	public float getBlur() {
		return Math.min(ArstMotionBlurConfig.blur, 99)/100F;
	}
}