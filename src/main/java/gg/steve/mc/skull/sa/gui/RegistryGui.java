package gg.steve.mc.skull.sa.gui;

import gg.steve.mc.skull.sa.core.RegistrationManager;
import gg.steve.mc.skull.sa.framework.gui.AbstractGui;
import gg.steve.mc.skull.sa.framework.gui.utils.GuiItemUtil;
import gg.steve.mc.skull.sa.framework.message.GeneralMessage;
import gg.steve.mc.skull.sa.framework.utils.CommandUtil;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class RegistryGui extends AbstractGui {
    private ConfigurationSection section;

    /**
     * Constructor the create a new Gui
     */
    public RegistryGui(ConfigurationSection section) {
        super(section, section.getString("type"), section.getInt("size"));
        this.section = section;
        List<Integer> slots = section.getIntegerList("fillers.slots");
        ItemStack filler = GuiItemUtil.createItem(section.getConfigurationSection("fillers"));
        for (Integer slot : slots) {
            setItemInSlot(slot, filler, (player, click) -> {
            });
        }
    }

    @Override
    public void refresh() {
        for (String entry : section.getKeys(false)) {
            try {
                Integer.parseInt(entry);
            } catch (NumberFormatException e) {
                continue;
            }
            ItemStack item = GuiItemUtil.createItem(section.getConfigurationSection(entry));
            List<Integer> slots = section.getIntegerList(entry + ".slots");
            switch (Objects.requireNonNull(section.getString(entry + ".action"))) {
                case "register":
                    for (Integer slot : slots) {
                        setItemInSlot(slot, item, (player, click) -> {
                            player.closeInventory();
                            if (RegistrationManager.registerPlayerIp(player)) {
                                GeneralMessage.IP_LINK_SUCCESS.message(player);
                                GuiManager.removeRequiredPlayer(player);
                            } else {
                                player.closeInventory();
                                GeneralMessage.IP_LINK_FAILURE.message(player);
                            }
                        });
                    }
                    break;
                case "cancel":
                    for (Integer slot : slots) {
                        setItemInSlot(slot, item, (player, click) -> {
                            player.closeInventory();
                            CommandUtil.execute(this.section.getStringList(entry + ".commands"), player);
                        });
                    }
                    break;
                case "close":
                    for (Integer slot : slots) {
                        setItemInSlot(slot, item, (player, click) -> {
                            player.closeInventory();
                        });
                    }
                    break;
                default:
                    for (Integer slot : slots) {
                        setItemInSlot(slot, item, (player, click) -> {
                        });
                    }
                    break;
            }
        }
    }
}
