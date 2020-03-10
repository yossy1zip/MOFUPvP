package net.mofucraft.mofupvp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.entity.Player;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;


public final class MapChange extends JavaPlugin {

    public static int mapNum = 1;
    public static String mapName;

    public void onEnable() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        switch (scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {

                DateFormat dateFormat = new SimpleDateFormat("mm");
                Date date = new Date();
                String time = dateFormat.format(date);

                if (time == "59") {
                    Random rand = new Random();
                    List<String> mapList = getConfig().getStringList("maps");
                    int next_mapNum = rand.nextInt(mapList.size()) + 1;
                    if (next_mapNum == mapNum) {
                        mapNum = next_mapNum + 1;
                        if (mapNum > mapList.size()) {
                            mapNum = 1;
                        }
                    } else {
                        mapNum = next_mapNum;
                    }
                    String mapName = mapList.get(mapNum);

                    Bukkit.broadcastMessage("あと1分でマップが変わります！");
                    Bukkit.broadcastMessage("次は 「" + mapName + "」 ！");
                    try {
                        Thread.sleep(50 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < 11; i++) {
                        Bukkit.broadcastMessage("マップ交代まで " + (10-i) + "...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Bukkit.broadcastMessage("&6移動しています...");
                    for (Player playerName : Bukkit.getOnlinePlayers()) {
                        String command = "mvtp" + " " + playerName + " " + mapName;
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                    }
                }
            }
        }, 0L, 30 * 20L)) {
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Bukkit.broadcastMessage("あああ！");
        getLogger().info("CHANGING");
        Random rand = new Random();
        List<String> mapList = getConfig().getStringList("maps");
        int next_mapNum = rand.nextInt(mapList.size()) + 1;
        if (next_mapNum == mapNum) {
            mapNum = next_mapNum + 1;
            if (mapNum > mapList.size()) {
                mapNum = 1;
            }
        } else {
            mapNum = next_mapNum;
        }
        String mapName = mapList.get(mapNum);
        Bukkit.broadcastMessage("あと30秒でマップが変わります！");
        Bukkit.broadcastMessage("次は 「" + mapName + "」 ！");
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 11; i++) {
            Bukkit.broadcastMessage("マップ交代まで " + (10 - i) + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Bukkit.broadcastMessage("&6移動しています...");
        for (Player playerName : Bukkit.getOnlinePlayers()) {
            String command = "mvtp" + " " + playerName + " " + mapName;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }
        return false;
    }
}
