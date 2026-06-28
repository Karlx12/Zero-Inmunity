package hrcode.labs.zeroinmunity.logic;

import hrcode.labs.zeroinmunity.config.Config;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;

/** * Handles damage immunity periods for entities. */
public class DamageImmunityHandler {
    /**     * Processes the invulnerability period for a damaged entity.     * @param entity The entity that was damaged     * @param source The source of the damage     */
    public static void overrideInvulnerabilityTime(LivingEntity entity, DamageSource source) {
        if (shouldApplyOverride(entity, source)) {
            entity.invulnerableTime = Config.invulnerabilityTicks();
        }
    }
    /**    * Determines if the invulnerability period should be applied based on the damage source and entity type.     * @param victim The entity that was damaged     * @param source The source of the damage     * @return true if the invulnerability period should be applied, false otherwise     */
    private static boolean shouldApplyOverride(LivingEntity victim, DamageSource source) {
        if (victim.level().isClientSide()) return false;
        /* Check if its turn off the options of need to affect Players and Mobs*/
        if (victim instanceof Player && !Config.affectPlayers()) return false;
        if (victim instanceof Mob && !Config.affectMobs()) return false;

        Entity attacker = source.getEntity();
        Entity directEntity = source.getDirectEntity();
        return attacker instanceof Player
                || attacker instanceof Mob
                || directEntity instanceof Projectile
                || directEntity instanceof PrimedTnt
                || source.is(DamageTypeTags.IS_EXPLOSION)
                || source.is(DamageTypeTags.IS_FALL)
                || source.is(DamageTypeTags.IS_DROWNING);
    }
}