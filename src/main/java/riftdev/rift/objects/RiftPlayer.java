package riftdev.rift.objects;

import lombok.NonNull;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import riftdev.rift.util.RiftUtil;

import java.util.UUID;

public class RiftPlayer {

    private transient @NonNull OfflinePlayer player;
    private final @NonNull UUID uuid;

    public RiftPlayer(@NonNull UUID uuid) {
        this.uuid = player.getUniqueId();
    }

    public RiftPlayer(@NonNull Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
    }

    public Player getPlayer() {
        return player instanceof Player ? (Player) player : null;
    }

    public RiftPlayer sendMessage(String msg) {
        Player player = getPlayer();
        RiftUtil.sendMessage(player, msg);
        return this;
    }


    public RiftPlayer sendActionBar(String msg) {
        Player player = getPlayer();
        RiftUtil.sendActionBar(player, msg);
        return this;
    }

    public RiftPlayer sendTitle(String title, String subtitle) {
        Player player = getPlayer();
        RiftUtil.sendTitle(player, title, subtitle);
        return this;
    }
}
