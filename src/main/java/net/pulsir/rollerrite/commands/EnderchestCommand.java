package net.pulsir.rollerrite.commands;

import net.pulsir.rollerrite.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class EnderchestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender.hasPermission("rollerite.enderchest"))) {
            sender.sendMessage(Color.translate("&cNo Permissions."));
            return false;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Color.translate("&cYou must be a player to execute this command."));
            return false;
        }

        if (args.length == 0) {
            Inventory enderChest = player.getEnderChest();
            player.openInventory(enderChest);
        } else {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(Color.translate("&cPlayer not found."));
                return false;
            }

            Inventory enderChest = target.getEnderChest();
            player.openInventory(enderChest);
        }

        return true;
    }
}
