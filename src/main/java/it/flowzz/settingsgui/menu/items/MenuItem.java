package it.flowzz.settingsgui.menu.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MenuItem {

    private final ItemStack itemStack;
    private final int slot;
    private final String command;

    public MenuItem(String mat, int slot, int subid, String name, List<String> lore, String command) {
        itemStack = new ItemStack(Material.valueOf(mat), 1, (short)subid);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> itemLore = lore.stream()
                .map(line -> ChatColor.translateAlternateColorCodes('&', line)).collect(Collectors.toList());
        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);
        this.slot = slot;
        this.command = command;
    }

}
