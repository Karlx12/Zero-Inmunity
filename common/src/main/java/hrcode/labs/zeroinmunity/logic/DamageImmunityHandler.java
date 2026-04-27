package hrcode.labs.zeroinmunity.logic;

import hrcode.labs.zeroinmunity.config.Config;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;

public class DamageImmunityHandler {
    public static void handleInmunityPeriod(LivingEntity entity, DamageSource source) {
        if (shouldApplyInmunity(entity, source)) {
            entity.invulnerableTime = Config.invulnerabilityTicks();
        }
    }

    private static boolean shouldApplyInmunity(LivingEntity victim, DamageSource source) {
        if (victim.level().isClientSide()) return false;

        if (victim instanceof Player && !Config.affectPlayers()) return false;
        if (victim instanceof net.minecraft.world.entity.Mob && !Config.affectMobs()) return false;
        Entity attacker = source.getEntity();
        Entity directEntity = source.getDirectEntity();
        return attacker instanceof Player
                || attacker instanceof Mob
                || directEntity instanceof Projectile
                || directEntity instanceof PrimedTnt
                || source.is(DamageTypeTags.IS_EXPLOSION);
    }
}