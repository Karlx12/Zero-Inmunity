package hrcode.labs.zeroinmunity.config;

import com.iamkaf.konfig.api.v1.ConfigBuilder;
import com.iamkaf.konfig.api.v1.ConfigHandle;
import com.iamkaf.konfig.api.v1.ConfigScope;
import com.iamkaf.konfig.api.v1.ConfigValue;
import com.iamkaf.konfig.api.v1.Konfig;
import com.iamkaf.konfig.api.v1.SyncMode;
import hrcode.labs.zeroinmunity.Constants;

public final class Config {
    public static final ConfigHandle HANDLE;
    public static final ConfigValue<Boolean> AFFECT_PLAYERS;
    public static final ConfigValue<Boolean> AFFECT_MOBS;
    public static final ConfigValue<Integer> INVULNERABILITY_TICKS;

    static {
        ConfigBuilder builder = Konfig.builder(Constants.MOD_ID, "server")
                .scope(ConfigScope.SERVER)
                .syncMode(SyncMode.LOGIN_AND_RELOAD)
                .comment("Zero Immunity server settings");

        builder.push("damage");

        AFFECT_PLAYERS = builder.bool("affectPlayers", true)
                .comment("Apply custom invulnerability logic to player victims")
                .sync(true)
                .build();

        AFFECT_MOBS = builder.bool("affectMobs", true)
                .comment("Apply custom invulnerability logic to mob victims")
                .sync(true)
                .build();

        INVULNERABILITY_TICKS = builder.intRange("invulnerabilityTicks", 0, 0, 20)
                .comment("Ticks of immunity after taking damage. Vanilla is 10.")
                .sync(true)
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