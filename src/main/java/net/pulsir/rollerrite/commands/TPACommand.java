package net.pulsir.rollerrite.commands;

import net.pulsir.rollerrite.data.Data;
import net.pulsir.rollerrite.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TPACommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!sender.hasPermission("rollerite.tpa")) {
            sender.sendMessage(Color.translate("&cNo Permissions"));
            return false;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Color.translate("&aYou must be a player to execute this command."));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(Color.translate("&cUsage /tpa <player>"));
        } else {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                sender.sendMessage(Color.translate("&cPlayer not found."));
                return false;
            }

            if (!Data.tpa.containsKey(player.getUniqueId())) {
                List<UUID> uuids = new ArrayList<>();
                uuids.add(target.getUniqueId());

                Data.tpa.put(player.getUniqueId(), uuids);
            } else {
                Data.tpa.get(player.getUniqueId()).add(target.getUniqueId());
            }

            player.sendMessage(Color.translate("&aTeleportation request was sent"));
            target.sendMessage(Color.translate("&a" + player.getName() + " wants to teleport to you. Accept by typing /tpaccept"));
        }

        return true;
    }
}
