package riftdev.rift.util;

import lombok.NonNull;
import riftdev.rift.Rift;

public interface Activatable {

    void activate(@NonNull Rift context);

    void deactivate(@NonNull Rift context);

    default boolean canDeactivate() {
        return true;
    }

    boolean isActive();

}
