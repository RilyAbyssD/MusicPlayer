package abyss.musicplayer;

import abyss.musicplayer.Manager.FileManager;
import abyss.musicplayer.Manager.MusicDataManager;
import abyss.musicplayer.Manager.PlayerDataManager;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import net.raidstone.wgevents.events.RegionLeftEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MusicPlayer extends JavaPlugin implements Listener {

    private static MusicPlayer plugin;

    @Override
    public void onEnable() {

        // メインクラスを代入
        plugin = this;

        // 初回起動のみファイルを作成
        FileManager.createDirectory();

        getServer().getPluginManager().registerEvents(new MusicListener(), this);

        // 起動メッセージ

        getLogger().info("MusicPlayer is Enable");
        getLogger().info(String.valueOf("メモリ使用量: " +
                Runtime.getRuntime().maxMemory() + " / " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())
        ));

    }

    public static MusicPlayer getPlugin() {
        return plugin;
    }

}