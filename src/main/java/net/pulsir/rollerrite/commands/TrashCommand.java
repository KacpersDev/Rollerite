package net.pulsir.rollerrite.commands;

import net.pulsir.rollerrite.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class TrashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender.hasPermission("rollerite.trash"))) {
            sender.sendMessage(Color.translate("&cNo Permissions."));
            return false;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Color.translate("&cYou must be a player to give yourself a god mode"));
            return false;
        }

        Inventory inventory = Bukkit.createInventory(player,54, Color.translate("&aTrash"));
        player.openInventory(inventory);

        return true;
    }
}
