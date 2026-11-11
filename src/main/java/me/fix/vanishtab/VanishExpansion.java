package me.fix.vanishtab;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class VanishExpansion extends PlaceholderExpansion {
    private final VanishTabFix plugin;

    VanishExpansion(VanishTabFix plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "vanishtab";
    }

    @Override
    public String getAuthor() {
        return "VanishTabFix";
    }

    @Override
    public String getVersion() {
        return "1.3";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        int real = Bukkit.getOnlinePlayers().size();
        int hidden = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (plugin.vanished(p.getUniqueId())) hidden++;
        }
        return switch (params.toLowerCase()) {
            case "online" -> String.valueOf(Math.max(0, real - hidden));
            case "online_real" -> String.valueOf(real);
            case "hidden" -> String.valueOf(hidden);
            default -> null;
        };
    }
}
