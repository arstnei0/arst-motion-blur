package com.arstmotionblur.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class ArstMotionBlurConfig extends MidnightConfig {
    @Entry(name = "Blur Enabled") public static boolean enabled = true;
    @Entry(name = "Blur Strength", isSlider = true, min = 0, max = 100) public static int blur = 50;
    @Comment(centered = true) public static Comment keybindHelp;

    public static void setBlur(int newBlur) {
        blur = Math.max(Math.min(newBlur, 100), 0);
    }
}