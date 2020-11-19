package it.flowzz.settingsgui;

import it.flowzz.settingsgui.commands.SettingsCommands;
import it.flowzz.settingsgui.listeners.MenuListener;
import it.flowzz.settingsgui.menu.MenuManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class SettingsGuiPlugin extends JavaPlugin {

    @Getter private MenuManager menuManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        menuManager = new MenuManager(this);
        new MenuListener(this);
        new SettingsCommands(this);
    }

}
