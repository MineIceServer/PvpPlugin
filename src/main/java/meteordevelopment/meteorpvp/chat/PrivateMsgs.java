package meteordevelopment.meteorpvp.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PrivateMsgs {
    private static final Map<UUID, UUID> lastMessages = new HashMap<>();

    public static void send(Player sender, Player receiver, String message) {
        String senderNick = ChatColor.translateAlternateColorCodes('&', sender.getName());
        String receiverNick = ChatColor.translateAlternateColorCodes('&', receiver.getName());

        sender.sendMessage(getMsg("Я", receiverNick, message));

        if (!Ignores.hasReceiverIgnored(sender, receiver)) {
            receiver.sendMessage(getMsg(senderNick, "Я", message));
        }

        lastMessages.put(receiver.getUniqueId(), sender.getUniqueId());
    }

    public static boolean reply(Player sender, String message) {
        UUID uuid = lastMessages.get(sender.getUniqueId());
        if (uuid == null) return false;

        Player receiver = Bukkit.getPlayer(uuid);
        if (receiver == null) return false;

        send(sender, receiver, message);
        return true;
    }

    private static String getMsg(String left, String right, String message) {
        return String.format("%s[%s%s %s- %s%s%s]%s » %s%s", ChatColor.DARK_GRAY, ChatColor.RESET, left, ChatColor.DARK_GRAY, ChatColor.RESET, right, ChatColor.DARK_GRAY, ChatColor.GOLD, ChatColor.AQUA, message);
    }
}
