package riftdev.rift;

import lombok.Getter;

public final class Rift extends RiftPlugin {

    @Getter
    public static Rift instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }
}
