package com.arstmotionblur;

import com.arstmotionblur.config.ArstMotionBlurConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import java.util.HashMap;
import java.util.Map;

public class ArstMotionBlurModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> ArstMotionBlurConfig.getScreen(parent, "arst-motion-blur");
    }

    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        HashMap<String, ConfigScreenFactory<?>> map = new HashMap<>();
        ArstMotionBlurConfig.configClass.forEach((modid, cClass) ->
                    map.put(modid, parent -> ArstMotionBlurConfig.getScreen(parent, modid))
        );
        return map;
    }
}
