package abyss.musicplayer.Manager;

import abyss.musicplayer.MusicPlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public static void createDirectory() {

        if (!MusicPlayer.getPlugin().getDataFolder().exists()) {
            MusicPlayer.getPlugin().getDataFolder().mkdir();
        }

        // MusicPlayerのルートディレクトリにlist.ymlを作成
        File file = new File(MusicPlayer.getPlugin().getDataFolder(), "SoundList.yml");


        // ファイルが存在しているか
        if (file.exists()) return;

        // ファイルの作成チェック
        try {
            if (file.createNewFile()) {
                MusicPlayer.getPlugin().getLogger().info("MusicPlayerに必要なファイルの作成に成功しました！！");
            } else {
                MusicPlayer.getPlugin().getLogger().info("MusicPlayerに必要なファイルの作成に失敗しました。");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getMusicName(String RegionName) {

        File file = new File(MusicPlayer.getPlugin().getDataFolder(), "SoundList.yml");

        // ファイルを読み込み
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        return yaml.getString(RegionName + ".sound-name");
    }

    public static double getMusicTimes(String RegionName) {

        File file = new File(MusicPlayer.getPlugin().getDataFolder(), "SoundList.yml");

        // ファイルを読み込み
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        return yaml.getDouble(RegionName + ".sound-times") / 0.25;
    }

}
