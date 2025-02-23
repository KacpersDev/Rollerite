package net.pulsir.rollerrite.commands;

import net.pulsir.rollerrite.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FixCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Color.translate("&cYou must be a player to give yourself a god mode"));
            return false;
        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack == null) {
            player.sendMessage(Color.translate("&cYou must be holding an item."));
            return false;
        }

        itemStack.setDurability((short) 0);
        player.sendMessage(Color.translate("&aSuccessfully fixed an item in your hand."));

        return true;
    }
}
