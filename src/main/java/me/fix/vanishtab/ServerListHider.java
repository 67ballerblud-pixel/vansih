package me.fix.vanishtab;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class ServerListHider implements Listener {
    private final VanishTabFix plugin;

    ServerListHider(VanishTabFix plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPing(PaperServerListPingEvent e) {
        try {
            int hidden = 0;
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (plugin.vanished(p.getUniqueId())) hidden++;
            }
            e.setNumPlayers(Math.max(0, e.getNumPlayers() - hidden));
            e.getListedPlayers().removeIf(info -> plugin.vanished(info.id()));
        } catch (Exception ignored) {
            // never break the server list
        }
    }
}
