package meteordevelopment.meteorpvp.commands.normal;

import meteordevelopment.meteorpvp.commands.MyCommand;
import meteordevelopment.meteorpvp.chat.Msgs;
import meteordevelopment.meteorpvp.chat.Prefixes;
import meteordevelopment.meteorpvp.arenas.Regions;
import meteordevelopment.meteorpvp.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class NetherCommand extends MyCommand {
    public NetherCommand() {
        super("nether", "Teleports you to the nether.");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        if (Regions.isInAnyPvp(player)) {
            sender.sendMessage(Prefixes.ARENA + Msgs.cantUseThisInPvp());
            return true;
        }

        player.teleport(Utils.NETHER.getSpawnLocation().add(0.5, 0, 0.5));

        return true;
    }
}
