package riftdev.rift.commands;

import java.util.List;

public abstract class ChildCommand extends BaseCommand {

    protected ChildCommand() {
        super("");
    }

    public abstract String getName();

    public abstract List<String> getAliases();

}
