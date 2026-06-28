package hrcode.labs.zeroinmunity.config;

import com.iamkaf.konfig.api.v1.*;
import hrcode.labs.zeroinmunity.Constants;

public final class Config {
    public static final ConfigHandle HANDLE;
    public static final ConfigValue<Boolean> AFFECT_PLAYERS;
    public static final ConfigValue<Boolean> AFFECT_MOBS;
    public static final ConfigValue<Integer> INVULNERABILITY_TICKS;

    static {


        ConfigBuilder builder = Konfig.builder(Constants.MOD_ID, "common")
                .scope(ConfigScope.COMMON)
                .syncMode(SyncMode.LOGIN_AND_RELOAD)
                .comment("Zero Immunity settings");
        builder.push("damage");

        AFFECT_PLAYERS = builder.bool("affectPlayers", true)
                .comment("Apply custom invulnerability logic to player victims")
                .sync(true)
                .restart(RestartRequirement.WORLD)
                .build();

        AFFECT_MOBS = builder.bool("affectMobs", true)
                .comment("Apply custom invulnerability logic to mob victims")
                .sync(true)
                .restart(RestartRequirement.WORLD)
                .build();

        INVULNERABILITY_TICKS = builder.intRange("invulnerabilityTicks", 0, 0, 20)
                .comment("Ticks of immunity after taking damage. Vanilla is 10.")
                .sync(true)
                .restart(RestartRequirement.WORLD)
                .build();

        builder.pop();

        HANDLE = builder.build();
    }

    private Config() {}

    public static void init() {
        // Trigger static init/registration.
    }

    public static boolean affectPlayers() {
        return AFFECT_PLAYERS.get();
    }

    public static boolean affectMobs() {
        return AFFECT_MOBS.get();
    }

    public static int invulnerabilityTicks() {
        return INVULNERABILITY_TICKS.get();
    }
}