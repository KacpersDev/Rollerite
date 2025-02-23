package net.pulsir.rollerrite.commands;

import net.pulsir.rollerrite.data.Data;
import net.pulsir.rollerrite.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TPAccept implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!sender.hasPermission("rollerite.tpaccept")) {
            sender.sendMessage(Color.translate("&cNo Permissions"));
            return false;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Color.translate("&aYou must be a player to execute this command."));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(Color.translate("&cUsage: /tpaccept <player>"));
        } else {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                sender.sendMessage(Color.translate("&cPlayer not found."));
                return false;
            }

            if (Data.tpa.get(target.getUniqueId()) != null
            && !Data.tpa.get(target.getUniqueId()).contains(player.getUniqueId())) {
                player.sendMessage(Color.translate("&c" + target.getName() + " has never sent a teleportation request."));
                return false;
            }

            Data.tpa.get(target.getUniqueId()).remove(player.getUniqueId());
            target.teleport(player.getLocation());
            player.sendMessage(Color.translate("&aSuccessfully approved teleportation request."));
        }

        return true;
    }
}
