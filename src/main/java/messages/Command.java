package messages;


/**
 * @author Sarp Cagin Erdogan
 * @author Mark Ringer
 */
public enum Command {
    ERROR("error");

    private final String name;

    Command(String commandName) {
        name = commandName;
    }
    public boolean equals(Command that){
        return this.name.equals(that.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    static public Command parseCommand(String input){
        switch (input){
            default -> {
                return Command.ERROR;
            }
        }
    }
}

