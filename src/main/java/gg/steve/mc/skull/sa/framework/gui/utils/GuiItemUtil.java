package gg.steve.mc.skull.sa.framework.gui.utils;

import gg.steve.mc.skull.sa.framework.utils.ItemBuilderUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class GuiItemUtil {

    public static ItemStack createItem(ConfigurationSection section) {
        ItemBuilderUtil builder = ItemBuilderUtil.getBuilderForMaterial(section.getString("material"), section.getString("data"));
        builder.addName(section.getString("name"));
        builder.addLore(section.getStringList("lore"));
        builder.addEnchantments(section.getStringList("enchantments"));
        builder.addItemFlags(section.getStringList("item-flags"));
        builder.addNBT(section, true);
        return builder.getItem();
    }
}
