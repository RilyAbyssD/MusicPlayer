package abyss.musicplayer.Manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MusicDataManager {

    private static Map<Player, PlayerDataManager> player_data = new HashMap<>();
    private static Map<Player, String> player_status = new HashMap<>();

    public static PlayerDataManager getPlayerData(Player p) {
        return player_data.get(p);
    }

    public static void setPlayerData(PlayerDataManager data) {
        Player p = data.getPlayer();
        player_data.put(p, data);
    }

    public static void removeSoundTimes(Player p) {
        player_data.remove(p);
    }

    public static String getPlayerStatus(Player p) {
        return player_status.get(p);
    }

    public static void setPlayerStatus(Player p, String Status) {
        player_status.put(p, Status);
    }
}
