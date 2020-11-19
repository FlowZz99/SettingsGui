package it.flowzz.settingsgui.listeners;

import it.flowzz.settingsgui.SettingsGuiPlugin;
import it.flowzz.settingsgui.menu.Menu;
import it.flowzz.settingsgui.menu.MenuManager;
import it.flowzz.settingsgui.menu.items.MenuItem;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    public MenuListener(SettingsGuiPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getCurrentItem() == null) return;
        Menu menu = MenuManager.getMenuByInventory(event.getInventory());
        if(menu != null){
            //This is a Menu inventory
            String command = null;
            for(MenuItem item : menu.getItems()){
                if(item.getItemStack().equals(event.getCurrentItem())){
                    command = item.getCommand();
                }
            }

            //Execute caommand
            if(command != null) Bukkit.dispatchCommand(event.getWhoClicked(),command);
            event.setCancelled(true);
        }
    }
}
