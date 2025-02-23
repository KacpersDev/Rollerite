package net.pulsir.rollerrite.commands;

import net.pulsir.rollerrite.data.Data;
import net.pulsir.rollerrite.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender.hasPermission("rollerite.god"))) {
            sender.sendMessage(Color.translate("&cNo Permissions."));
            return false;
        }

        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(Color.translate("&cYou must be a player to give yourself a god mode"));
                return false;
            }

            if (Data.godPlayers.contains(player.getUniqueId())) {
                Data.godPlayers.remove(player.getUniqueId());
                player.sendMessage(Color.translate("&fSuccessfully &cremoved &fgod mode."));
            } else {
                Data.godPlayers.add(player.getUniqueId());
                player.sendMessage(Color.translate("&fSuccessfully &aenabled &fgod mode."));
            }
        } else {
            if (args.length == 1) {
                 sender.sendMessage(Color.translate("&cUsage: /god [player]"));
            } else {
                Player target = Bukkit.getPlayer(args[1]);

                if (target == null || !target.isOnline()) {
                    sender.sendMessage(Color.translate("&cPlayer not found."));
                    return false;
                }

                if (Data.godPlayers.contains(target.getUniqueId())) {
                    Data.godPlayers.remove(target.getUniqueId());
                    sender.sendMessage(Color.translate("&fSuccessfully &cremoved &fgod mode for " + target.getName()));
                } else {
                    Data.godPlayers.add(target.getUniqueId());
                    sender.sendMessage(Color.translate("&fSuccessfully &aenabled &fgod mode for " + target.getName()));
                }
            }
        }

        return true;
    }
}
