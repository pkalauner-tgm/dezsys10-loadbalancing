package at.kalaunermalik.dezsys10.loadbalancer.connection;

/**
 * Handles commands
 *
 * @author Paul Kalauner 5BHIT
 * @version 20160219.1
 */
public abstract class CommandHandler {
    protected SocketHandler sh;

    /**
     * Initializes the CommandHandler with the given SocketHandler
     * @param sh SocketHandler
     */
    public CommandHandler(SocketHandler sh) {
        this.sh = sh;
    }

    /**
     * Handles the command
     *
     * @param clientThread sender of the command
     * @param command command to handle
     */
   public abstract void handleCommand(ClientThread clientThread, String command);
}
