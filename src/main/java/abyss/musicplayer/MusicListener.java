package abyss.musicplayer;

import abyss.musicplayer.Manager.FileManager;
import abyss.musicplayer.Manager.MusicDataManager;
import abyss.musicplayer.Manager.PlayerDataManager;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import net.raidstone.wgevents.events.RegionLeftEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class MusicListener implements Listener {

    // PlayerStatus用の定数
    final String NOW = "LISTEN_NOW";
    final String NOT = "LISTEN_NOT_NOW";

    @EventHandler
    public void onEnteredRegion(RegionEnteredEvent e) {

        Player p = e.getPlayer();

        // リージョン名を取得
        String RegionName = e.getRegionName();

        // インスタンス生成
        PlayerDataManager data = new PlayerDataManager(p);

        // HashMapにデータを代入
        MusicDataManager.setPlayerData(data);

        playSound(p, RegionName);

    }

    @EventHandler
    public void onLeftRegion(RegionLeftEvent e) {

        Player p = e.getPlayer();

        // プレイヤーの再生中の音声を中止
        p.stopAllSounds();

        // PlayerStatusをNOTに変更
        MusicDataManager.setPlayerStatus(p, NOT);

        // MapからPlayer情報を削除
        MusicDataManager.removeSoundTimes(p);
    }

    private void playSound(Player p, String RegionName) {

        PlayerDataManager playerData = MusicDataManager.getPlayerData(p);

        // リージョンに対する音声名を取得し、代入
        playerData.setSound_title(FileManager.getMusicName(RegionName));

        // リージョンに対する再生時間を取得し、代入
        playerData.setSound_times(FileManager.getMusicTimes(RegionName));

        MusicDataManager.setPlayerStatus(p, NOW);

        p.playSound(p.getLocation(), playerData.getSound_title(), 0.5F, 1.0F);

        BukkitRunnable task = new BukkitRunnable() {

            // 再生時間を代入
            double times = playerData.getSound_times();

            @Override
            public void run() {

                if (times == 0) {
                    // 再生
                    p.playSound(p.getLocation(), playerData.getSound_title(), 0.5F, 1.0F);
                    times = playerData.getSound_times();
                }
                if (MusicDataManager.getPlayerStatus(p).equals(NOT)) {
                    cancel();
                }

                times --;
            }
        };
        task.runTaskTimer(MusicPlayer.getPlugin(), 0L, 5L);
    }

}
