package meteordevelopment.meteorpvp.listeners;

import meteordevelopment.meteorpvp.kits.Kit;
import meteordevelopment.meteorpvp.kits.Kits;
import meteordevelopment.meteorpvp.kits.MaxKits;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class KitsGuiListener implements Listener {
    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;

        String name = event.getView().getTitle();
        if (name.equals(Kits.GUI_TITLE)) onGui(event, this::onGuiMain);
        else if (name.equals(Kits.GUI_PRIVATE_KITS_TITLE) || name.equals(Kits.GUI_PUBLIC_KITS_TITLE)) onGui(event, null);
    }

    private void onGui(InventoryClickEvent event, Consumer<InventoryClickEvent> handler) {
        event.setCancelled(true);

        if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
            Kit kit = Kits.INSTANCE.get(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));

            if (kit != null) {
                if (event.getClick() == ClickType.LEFT) {
                    kit.apply(event.getWhoClicked());
                    event.getWhoClicked().closeInventory();
                }
                else if (event.getClick() == ClickType.RIGHT && MaxKits.get(event.getWhoClicked()).canHavePublic && kit.author.equals(event.getWhoClicked().getUniqueId())) {
                    if (kit.isPublic) Kits.INSTANCE.removePublic(kit);
                    else {
                        Kits.INSTANCE.addPublic(kit);

                        for (Kit k : Kits.INSTANCE.getKits(event.getWhoClicked())) {
                            if (k.isPublic) {
                                k.isPublic = false;
                                Kits.INSTANCE.removePublic(k);
                            }
                        }
                    }

                    kit.isPublic = !kit.isPublic;
                    event.getWhoClicked().openInventory(Kits.INSTANCE.guiPrivateKits(event.getWhoClicked()));
                }
            }

            return;
        }

        if (handler != null) handler.accept(event);
    }

    private void onGuiMain(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        String name = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());

        if (name.equals(Kits.GUI_PRIVATE_KITS_TITLE)) {
            event.getWhoClicked().openInventory(Kits.INSTANCE.guiPrivateKits(event.getWhoClicked()));
        } else if (name.equals(Kits.GUI_PUBLIC_KITS_TITLE)) {
            event.getWhoClicked().openInventory(Kits.INSTANCE.guiPublicKits(event.getWhoClicked()));
        }
    }
}
