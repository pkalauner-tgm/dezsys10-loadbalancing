package at.kalaunermalik.dezsys10.loadbalancer.connection;

/**
 * Created by Paul on 18.02.2016.
 */
public abstract class CommandHandler {
    protected SocketHandler sh;

    public CommandHandler(SocketHandler sh) {
        this.sh = sh;
    }

   public abstract void handleCommand(ClientThread clientThread, String command);
}
