package riftdev.rift.listeners;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import riftdev.rift.Rift;
import riftdev.rift.RiftPlugin;
import riftdev.rift.util.Activatable;

public abstract class RiftEngine implements Listener, Activatable {

    protected final RiftPlugin plugin;

    @Getter
    private boolean active = false;

    protected RiftEngine(@NonNull RiftPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void activate(@NonNull Rift context) {
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
        this.active = true;
    }

    @Override
    public void deactivate(@NonNull Rift context) {
        HandlerList.unregisterAll(this);
        this.active = false;
    }
}
