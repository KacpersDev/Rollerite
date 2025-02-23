package net.pulsir.rollerrite.commands;

import net.pulsir.rollerrite.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender.hasPermission("rollerite.gamemode"))) {
            sender.sendMessage(Color.translate("&cNo Permissions."));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(Color.translate("&cPlease specify gamemode, (creative, survival, spectator)"));
        } else {
            try {
                GameMode gameMode = GameMode.valueOf(args[0].toUpperCase());

                if (args.length == 1) {
                    if (!(sender instanceof Player player)) {
                        sender.sendMessage(Color.translate("&cYou must be a player to give yourself a god mode"));
                        return false;
                    }

                    player.setGameMode(gameMode);
                    player.sendMessage(Color.translate("&aSuccessfully updated gamemode " + gameMode));
                } else {
                    Player target = Bukkit.getPlayer(args[1]);

                    if (target == null || !target.isOnline()) {
                        sender.sendMessage(Color.translate("&cPlayer not found."));
                        return false;
                    }

                    target.setGameMode(gameMode);
                    sender.sendMessage(Color.translate("&aSuccessfully updated gamemode for " + target.getName() + " to " + gameMode));
                }
            } catch (IllegalArgumentException e) {
                sender.sendMessage(Color.translate("&cPlease specify gamemode, (creative, survival, spectator)"));
            }
        }

        return true;
    }
}
