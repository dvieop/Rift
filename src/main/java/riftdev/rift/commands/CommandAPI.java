package riftdev.rift.commands;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public final class CommandAPI {

    @Getter
    private static final CommandAPI instance = new CommandAPI();

    private CommandAPI() {

    }

    private final Map<Class<? extends BaseCommand>, BaseCommand> allCommands = new LinkedHashMap<>();

    public void newCommand(final BaseCommand command) {
        allCommands.putIfAbsent(command.getClass(), command);
    }

    private CommandMap getCommandMap() {
        return Bukkit.getCommandMap();
    }

    public <T extends BaseCommand> T getCommand(final Class<T> klass) {
        final Object obj = allCommands.get(klass);
        if (obj == null) {
            return null;
        }
        return (T) obj;
    }

    private BaseCommand getCommand(final Object obj) {
        if (obj instanceof Class) {
            return getCommand((Class) obj);
        } else if (obj instanceof BaseCommand) {
            return (BaseCommand) obj;
        } else {
            return null;
        }
    }

    public void enableCommand(final Object obj) {
        final BaseCommand command = getCommand(obj);
        if (command == null || command instanceof ChildCommand) return;
        final CommandMap commandMap = getCommandMap();
        commandMap.register(command.getName(), command);
        command.getAliases().forEach(a -> commandMap.register(a.toLowerCase(), command));
    }

    public void disableCommand(final Object obj) {
        final BaseCommand command = getCommand(obj);
        if (command == null || command instanceof ChildCommand) return;
        try {
            final Field field = SimpleCommandMap.class.getDeclaredField("knownCommands");
            field.setAccessible(true);
            final Map<String, Command> map = (Map<String, Command>) field.get(getCommandMap());
            map.remove(command.getName());
            command.getAliases().forEach(a -> map.remove(a.toLowerCase()));
        } catch (Throwable err) {
            throw new RuntimeException(err);
        }
    }
}
