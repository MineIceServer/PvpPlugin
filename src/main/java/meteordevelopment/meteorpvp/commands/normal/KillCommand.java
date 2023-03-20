package meteordevelopment.meteorpvp.commands.normal;

import meteordevelopment.meteorpvp.commands.MyCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand extends MyCommand {
    public KillCommand() {
        super("kill", "Kills you.");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        player.setHealth(0);
        return true;
    }
}
