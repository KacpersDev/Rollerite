package net.pulsir.rollerrite.listeners;

import net.pulsir.rollerrite.data.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class GodModeListener implements Listener {

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (Data.godPlayers.contains(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (Data.godPlayers.contains(player.getUniqueId())) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (Data.godPlayers.contains(player.getUniqueId())) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByBlockEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (Data.godPlayers.contains(player.getUniqueId())) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }
}
