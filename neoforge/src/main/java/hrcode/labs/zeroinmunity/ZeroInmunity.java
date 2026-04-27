package hrcode.labs.zeroinmunity;


import com.iamkaf.konfig.neoforge.api.v1.KonfigNeoForgeClientScreens;
import hrcode.labs.zeroinmunity.logic.DamageImmunityHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@Mod(Constants.MOD_ID)
public class ZeroInmunity {

    public ZeroInmunity(IEventBus eventBus, ModContainer modContainer) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        Constants.LOG.info("Hello NeoForge world!");
        CommonClass.init();
        KonfigNeoForgeClientScreens.register(modContainer,Constants.MOD_ID);
        NeoForge.EVENT_BUS.addListener(ZeroInmunity::onIncomingDamage);
    }
    private static void onIncomingDamage(LivingIncomingDamageEvent event) {
        DamageImmunityHandler.handleInmunityPeriod(event.getEntity(), event.getSource());
    }
}