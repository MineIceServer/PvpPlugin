package meteordevelopment.meteorpvp.listeners;

import meteordevelopment.meteorpvp.arenas.Regions;
import meteordevelopment.meteorpvp.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent event) {
        if (!Regions.isInAnyPvp(event.getEntity()) || Utils.isAdmin(event.getEntity())) {
            event.getDrops().clear();
        }

        event.setDroppedExp(0);
    }

    @EventHandler
    private void onPlayerRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(event.getPlayer().getWorld().getSpawnLocation().add(0.5, 0, 0.5));
    }
}
