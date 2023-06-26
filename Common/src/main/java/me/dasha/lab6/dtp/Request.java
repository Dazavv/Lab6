package me.dasha.lab6.dtp;

import me.dasha.lab6.collectionClasses.SpaceMarine;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private String commandName;
    private String args = "";
    private SpaceMarine object = null;

    public String getCommandName() {
        return commandName;
    }

    public String getArgs() {
        return args;
    }

    public SpaceMarine getObject() {
        return object;
    }

    public Request(ResponseStatus OK, String commandName, SpaceMarine help) {
        this.commandName = commandName.trim();
    }

    public Request(String commandName, String args) {
        this.commandName = commandName.trim();
        this.args = args;
    }

    public Request(String commandName, SpaceMarine object) {
        this.commandName = commandName.trim();
        this.object = object;
    }

    public Request(String commandName, String args, SpaceMarine object) {
        this.commandName = commandName.trim();
        this.args = args.trim();
        this.object = object;
    }

    public boolean isEmpty() {
        return commandName.isEmpty() && args.isEmpty() && object == null;
    }

    @Override
    public String toString() {
        return "Request[" + commandName +
                (args.isEmpty()
                        ? ""
                        : "," + args ) +
                ((object == null)
                        ? "]"
                        : "," + object + "]");
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandName, args, object);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Request request)) return false;
        return Objects.equals(commandName, request.commandName) && Objects.equals(args, request.args) && Objects.equals(object, request.object);
    }
}
