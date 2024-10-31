package riftdev.rift.util;

public enum TimeUnit {

    DAYS(java.util.concurrent.TimeUnit.DAYS),
    HOURS(java.util.concurrent.TimeUnit.HOURS),
    MINUTES(java.util.concurrent.TimeUnit.MINUTES),
    SECONDS(java.util.concurrent.TimeUnit.SECONDS),
    MILLS(java.util.concurrent.TimeUnit.MILLISECONDS),
    TICKS(null);

    private final java.util.concurrent.TimeUnit javaTimeUnit;

    TimeUnit(java.util.concurrent.TimeUnit javaTimeUnit) {
        this.javaTimeUnit = javaTimeUnit;
    }

    private static final int TICKS_PER_SECOND = 20;
    private static final int TICKS_PER_MINUTE = TICKS_PER_SECOND * 60;
    private static final int TICKS_PER_HOUR = TICKS_PER_MINUTE * 60;
    private static final int TICKS_PER_DAY = TICKS_PER_HOUR * 24;


    public long toDays(long duration) {
        return javaTimeUnit != null ? javaTimeUnit.toDays(duration) : (duration / TICKS_PER_DAY);
    }

    public long toHours(long duration) {
        return javaTimeUnit != null ? javaTimeUnit.toHours(duration) : (duration / TICKS_PER_HOUR);
    }

    public long toMinutes(long duration) {
        return javaTimeUnit != null ? javaTimeUnit.toMinutes(duration) : (duration / TICKS_PER_MINUTE);
    }

    public long toSeconds(long duration) {
        return javaTimeUnit != null ? javaTimeUnit.toSeconds(duration) : (duration / TICKS_PER_SECOND);
    }

    public long toTicks(long duration) {
        return javaTimeUnit != null ? javaTimeUnit.toMillis(duration) / 50 : duration;
    }

    public long toMillis(long duration) {
        return javaTimeUnit != null ? javaTimeUnit.toMillis(duration) : (duration * 50);
    }
}