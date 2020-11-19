package it.flowzz.settingsgui.menu;

import it.flowzz.settingsgui.menu.items.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@AllArgsConstructor
@Getter
public class Menu {

    private final List<MenuItem> items;
    private final ItemStack filler;
    private final int slots;
    private final String name;

    public Inventory toInventory(){
        Inventory inventory = Bukkit.createInventory(null,slots, ChatColor.translateAlternateColorCodes('&', name));
        items.forEach(item -> inventory.setItem(item.getSlot(), item.getItemStack()));

        for (int i = 0; i < inventory.getContents().length; i++) {
            if(inventory.getContents()[i] == null)
                inventory.setItem(i,filler);
        }
        return inventory;
    }

}
