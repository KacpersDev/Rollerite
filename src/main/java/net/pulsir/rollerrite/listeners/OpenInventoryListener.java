package net.pulsir.rollerrite.listeners;

import net.pulsir.rollerrite.data.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class OpenInventoryListener implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (Data.inventories.containsKey(player.getUniqueId())) {
            Player target = Bukkit.getPlayer(Data.inventories.get(player.getUniqueId()));
            if (target != null) {
                target.getInventory().setContents(event.getInventory().getContents());
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        Data.inventories.remove(player.getUniqueId());
    }
}
