package it.flowzz.settingsgui.menu;

import it.flowzz.settingsgui.SettingsGuiPlugin;
import it.flowzz.settingsgui.menu.items.MenuItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MenuManager {


    private final SettingsGuiPlugin plugin;
    private static HashMap<String,Menu> menus;

    public MenuManager(SettingsGuiPlugin plugin) {
        this.plugin = plugin;
        menus = new HashMap<>();
        init();
    }

    private void init(){
        FileConfiguration config = plugin.getConfig();
        ItemStack filler = new ItemStack(
                Material.valueOf(config.getString("GUI.filler.material")),
                1,
                (short)config.getInt("GUI.filler.subid"));
        List<MenuItem> items = config.getConfigurationSection("GUI.Items").getKeys(false)
                .stream()
                .map(key -> new MenuItem(config.getString("GUI.Items." + key + ".material"),
                config.getInt("GUI.Items." + key + ".slot"),
                config.getInt("GUI.Items." + key + ".subid"),
                config.getString("GUI.Items." + key + ".name"),
                config.getStringList("GUI.Items." + key + ".lore"),
                config.getString("GUI.Items." + key + ".command"))).collect(Collectors.toList());

        menus.put("Settings",new Menu(items, filler,config.getInt("GUI.size"), ChatColor.translateAlternateColorCodes('&', config.getString("GUI.name"))));
    }

    public static Menu getMenuByName(String name){
        return menus.get(name);
    }

    public static Menu getMenuByInventory(Inventory inventory){
        return menus.values().stream()
                .filter(menu -> menu.getName().equalsIgnoreCase(inventory.getTitle()) && inventory.getSize() == menu.getSlots()).findFirst().orElse(null);
    }
}
