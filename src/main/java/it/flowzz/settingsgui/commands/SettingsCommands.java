package it.flowzz.settingsgui.commands;

import it.flowzz.settingsgui.SettingsGuiPlugin;
import it.flowzz.settingsgui.menu.Menu;
import it.flowzz.settingsgui.menu.MenuManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SettingsCommands implements CommandExecutor {

    public SettingsCommands(SettingsGuiPlugin plugin) {
        plugin.getCommand("Settings").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = ((Player) sender);
            Menu menu = MenuManager.getMenuByName("Settings");
            if(menu != null){
                player.openInventory(menu.toInventory());
                return true;
            }
        }else {
            sender.sendMessage("§CThis command can only be executed by a player!");
        }
        return false;
    }
}
