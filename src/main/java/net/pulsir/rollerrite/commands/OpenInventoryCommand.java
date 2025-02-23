package net.pulsir.rollerrite.commands;

import net.pulsir.rollerrite.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class OpenInventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!sender.hasPermission("rollerite.openinv")) {
            sender.sendMessage(Color.translate("&cNo Permissions"));
            return false;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage("&cYou must be a player");
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(Color.translate("&cUsage: /openinv <player>"));
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null || !target.isOnline()) {
                sender.sendMessage(Color.translate("&cPlayer not found."));
                return false;
            }

            Inventory inventory = target.getInventory();
            player.openInventory(inventory);
        }

        return true;
    }
}
