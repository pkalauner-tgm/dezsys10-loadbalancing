package at.kalaunermalik.dezsys10.loadbalancer.connection;

import at.kalaunermalik.dezsys10.loadbalancer.Balancing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles new incoming connections
 *
 * @author Paul Kalauner 5BHIT
 * @version 20160204
 */
public class SocketHandler extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(SocketHandler.class);
    private ServerSocket serverSocket;
    private List<ClientThread> clients;
    CommandHandler ch;
    Balancing balancing;

    /**
     * Initializes the ServerSocket
     *
     * @param port port
     */
    public SocketHandler(Balancing balancing, int port) {
        LOGGER.info("Creating ServerSocket");
        this.balancing = balancing;
        this.clients = new ArrayList<>();
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            LOGGER.error("Error while creating ServerSocket", e);
        }
    }


    /**
     * Waits for new connections
     */
    @Override
    public void run() {
        try {
            while (true) {
                ClientThread ct = new ClientThread(this, serverSocket.accept());
                clients.add(ct);
                ct.start();
            }
        } catch (IOException e) {
            LOGGER.error("Exception while creating new ClientThread", e);
        }
    }

    /**
     * Removes a client from the Client Set
     *
     * @param ct the client which should be removed
     */
    public void removeClient(ClientThread ct) {
        this.clients.remove(ct);
    }

    /**
     * Sends a message to all clients
     *
     * @param message the message which should be sent
     */
    public void broadcast(String message) {
        for (ClientThread cur : clients) {
            cur.sendCommand(message);
        }
    }

    /**
     * Gets the number of available clients
     *
     * @return number of clients
     */
    public int getNumberOfClients() {
        return this.clients.size();
    }

    /**
     * Gets all clients
     *
     * @return list of all clients
     */
    public List<ClientThread> getClients() {
        return clients;
    }

    public void setCommandHandler(CommandHandler ch) {
        this.ch = ch;
    }
}
