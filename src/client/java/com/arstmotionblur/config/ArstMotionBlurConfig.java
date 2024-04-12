package com.arstmotionblur.config;

import eu.midnightdust.lib.config.MidnightConfig;

/** Every option in a MidnightConfig class has to be public and static, so we can access it from other classes.
 * The config class also has to extend MidnightConfig*/

public class ArstMotionBlurConfig extends MidnightConfig {
    @Entry(name = "Blur Enabled") public static boolean enabled = true;
    @Entry(name = "Blur Strength", isSlider = true, min = 0, max = 100) public static int blur = 30;
}