package hrcode.labs.zeroinmunity;

import hrcode.labs.zeroinmunity.logic.DamageImmunityHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

public class ZeroInmunity implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (amount <= 0.0f) {
                return true;
            }
            DamageImmunityHandler.handleInmunityPeriod(entity, source);
            return true;
        });

    }
}
