# Arst Motion Blur

This mod adds [motion blur](https://en.wikipedia.org/wiki/Motion_blur) effect to Minecraft. Requires [MidnightLib](https://modrinth.com/mod/midnightlib) and [Satin API](https://modrinth.com/mod/satin-api).

You can enable or disable motion blur and adjust the blur strength with [Mod Menu Mod](https://modrinth.com/mod/modmenu). Three customizable keybinds are also added:

+ Toggle motion blur (Set to `m` key by defalt)
+ Increase blur strength (Unbound by default)
+ Decrease blur strength (Unbound by default)

It is not a fork of [Noryea's Motion Blur](https://github.com/Noryea/motionblur-fabric) but I did refer to some of its code. The shader code in Noryea's mod uses GLSL 120 which is not supported by some devices while Arst Motion Blur uses GLSL 330 and presumably supports more platforms. The original idea was to make Motion Blur work on my M1 Macbook and eventually I also added more features. You can say this is an upgraded version of Noryea's mod.

If you encounter any issues when using this mod, you can [submit an issue](https://github.com/arstnei0/arst-motion-blur/issues) in the Github repo or directly ask for help in [my discord](https://discord.gg/5mDP8mFAuT) if you are not familiar with Github.

Credits: Noryea for parts of the shader code and the inspiration for the project.