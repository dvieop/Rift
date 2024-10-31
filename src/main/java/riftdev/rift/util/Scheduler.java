package riftdev.rift.util;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import riftdev.rift.Rift;

public class Scheduler {

    public static BukkitTask sync(Runnable runnable) {
        return createBukkitRunnable(runnable).runTask(Rift.instance);
    }

    public static BukkitTask async(Runnable runnable) {
        return createBukkitRunnable(runnable).runTaskAsynchronously(Rift.instance);
    }

    public static BukkitTask delay(int value, TimeUnit unit, boolean async, Runnable runnable) {
        if (async) {
            return createBukkitRunnable(runnable).runTaskLaterAsynchronously(Rift.instance, unit.toTicks((long) value));
        } else {
            return createBukkitRunnable(runnable).runTaskLater(Rift.instance, unit.toTicks((long) value));
        }
    }

    public static BukkitTask delay(int ticks, Runnable runnable) {
        return delay(ticks, TimeUnit.TICKS, false, runnable);
    }

    public static BukkitTask delay(int ticks, boolean async, Runnable runnable) {
        return delay(ticks, TimeUnit.TICKS, async, runnable);
    }

    public static BukkitTask repeat(int delay, int period, TimeUnit unit, boolean async, Runnable runnable) {
        if (async) {
            return createBukkitRunnable(runnable).runTaskTimerAsynchronously(
                    Rift.instance,
                    unit.toTicks((long) delay),
                    unit.toTicks((long) period)
            );
        } else {
            return createBukkitRunnable(runnable).runTaskTimer(
                    Rift.instance,
                    unit.toTicks((long) delay),
                    unit.toTicks((long) period)
            );
        }
    }


    private static BukkitRunnable createBukkitRunnable(Runnable runnable) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        };
    }
}
