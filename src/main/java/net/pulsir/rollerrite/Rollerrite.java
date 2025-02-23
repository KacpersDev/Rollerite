package net.pulsir.rollerrite;

import net.pulsir.rollerrite.commands.*;
import net.pulsir.rollerrite.listeners.GodModeListener;
import net.pulsir.rollerrite.listeners.OpenInventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Rollerrite extends JavaPlugin {

    private static Rollerrite instance;

    @Override
    public void onEnable() {
        instance = this;

        this.loadCommands();
        this.loadListeners(Bukkit.getPluginManager());
    }

    public static Rollerrite getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void loadCommands() {
        Objects.requireNonNull(getCommand("god")).setExecutor(new GodCommand());
        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new GamemodeCommand());
        Objects.requireNonNull(getCommand("enderchest")).setExecutor(new EnderchestCommand());
        Objects.requireNonNull(getCommand("trash")).setExecutor(new TrashCommand());
        Objects.requireNonNull(getCommand("tpa")).setExecutor(new TPACommand());
        Objects.requireNonNull(getCommand("tpaaccept")).setExecutor(new TPAccept());
    }

    private void loadListeners(PluginManager pluginManager) {
        pluginManager.registerEvents(new GodModeListener(), this);
        pluginManager.registerEvents(new OpenInventoryListener(), this);
    }
}
