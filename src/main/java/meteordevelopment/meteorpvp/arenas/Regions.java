package meteordevelopment.meteorpvp.arenas;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import meteordevelopment.meteorpvp.duels.Duel;
import meteordevelopment.meteorpvp.duels.Duels;
import meteordevelopment.meteorpvp.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Regions {
    public static ProtectedRegion KITCREATOR;

    public static ProtectedRegion OW_SPAWN;
    public static ProtectedRegion OW_PVP;

    public static void onEnable() {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager OW = container.get(BukkitAdapter.adapt(Utils.OVERWORLD));

        KITCREATOR = OW.getRegion("kitcreator");

        OW_SPAWN = OW.getRegion("spawn");
        OW_PVP = OW.getRegion("pvp");
    }

    public static boolean isIn(ProtectedRegion region,Location pos) {
        return region.contains(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
    }

    public static boolean isIn(ProtectedRegion region, Entity entity) {
        return isIn(region, entity.getLocation());
    }

    public static boolean isInAnyPvp(Player player, boolean duels) {
        if (player.getWorld() == Utils.OVERWORLD) {
            return isIn(OW_PVP, player) || Duels.INSTANCE.get(player) != null;
        }

        if (duels) {
            Duel duel = Duels.INSTANCE.get(player);
            if (duel != null) return true;
        }

        return false;
    }

    public static boolean isInAnyPvp(Player player) {
        return isInAnyPvp(player, true) && !Utils.isAdmin(player);
    }

    public static boolean isInAnyOW(Player player) {
        return isIn(OW_SPAWN, player) || isIn(OW_PVP, player) || isIn(KITCREATOR, player);
    }

    public static boolean isInAnyNether(Player player) {
        return false;
    }

    public static boolean isInAnyBuildable(Location location) {
        if (location.getWorld() == Utils.OVERWORLD) {
            return isIn(OW_PVP, location) || Duels.INSTANCE.overworldNormal.isIn(location) || Duels.INSTANCE.overworldFlat.isIn(location);
        }

        return false;
    }

    public static Region toWERegion(ProtectedRegion region) {
        return new CuboidRegion(region.getMinimumPoint(), region.getMaximumPoint());
    }
}
