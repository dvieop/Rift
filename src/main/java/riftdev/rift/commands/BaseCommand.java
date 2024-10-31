package riftdev.rift.commands;


import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import riftdev.rift.commands.annotation.Command;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseCommand extends BukkitCommand {

    public BaseCommand(String name) {
        super(name);
        if (!(this instanceof ChildCommand)) {
            CommandAPI.getInstance().newCommand(this);
            if (!getClass().isAnnotationPresent(Command.class)) {
                throw new RuntimeException("COMMAND ANNOTATION MISSING FROM: " + getClass());
            }
        }
    }

    public void postInit() {

    }

    public void addChildren(ChildCommand... children) {
        for (ChildCommand child : children) {
            this.children.put(child.getName(), child);
            child.getAliases().forEach(a -> this.children.put(a.toLowerCase(), child));
        }
    }

    public void removeChildren(ChildCommand... children) {
        for (ChildCommand child : children) {
            this.children.remove(child.getName());
            child.getAliases().forEach(a -> this.children.remove(a.toLowerCase()));
        }
    }

    @Override
    public String getName() {
        return getClass().getAnnotation(Command.class).name();
    }

    private final Map<String, ChildCommand> children = new LinkedHashMap<>();

    @Getter
    @Setter
    private boolean worksWithNoArgs = false;

    @Override
    public final boolean execute(CommandSender commandSender, String string, String[] args) {
        if ((args.length == 0 && !(this instanceof ChildCommand || worksWithNoArgs)) || (!children.isEmpty() && !children.containsKey(args[0].toLowerCase()))) {
            sendUseMessage(commandSender, string, args);
            return false;
        }
        if (children.isEmpty()) {
            return this.noChildrenExecute(commandSender, string, args);
        }
        String[] array = new String[args.length - 1];
        String s = "";
        if (array.length > 0) {
            array = Arrays.copyOfRange(args, 1, args.length);
            if (array.length == 1) {
                s = array[0];
            } else {
                s = string.split(args[0] + " ", 2)[0];
            }
        }
        return children.get(args[0].toLowerCase()).execute(commandSender, s, array);
    }


    protected abstract boolean noChildrenExecute(CommandSender commandSender, String string, String[] args);

    protected void sendUseMessage(CommandSender sender, String string, String[] args) {

    }
}
