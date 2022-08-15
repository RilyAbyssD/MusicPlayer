package abyss.musicplayer.Manager;

import org.bukkit.entity.Player;

public class PlayerDataManager {


    public PlayerDataManager(Player p) {
        this.p = p;
    }

    private Player p;
    private String uuid;
    private Integer volume;
    private String sound_title;
    private double sound_times;

    public Player getPlayer() {
        return p;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getSound_title() {
        return sound_title;
    }

    public void setSound_title(String sound_title) {
        this.sound_title = sound_title;
    }

    public double getSound_times() {
        return sound_times;
    }

    public void setSound_times(double sound_times) {
        this.sound_times = sound_times;
    }
}
