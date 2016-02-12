package at.kalaunermalik.dezsys10.loadbalancer.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

/**
 * Handles new incoming connections
 *
 * @author Paul Kalauner 5BHIT
 * @version 20160204
 */
public class ClientSocketHandler extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(ClientSocketHandler.class);
    private ServerSocket serverSocket;
    private Set<ClientThread> clients;

    /**
     * Initializes the ServerSocket
     *
     * @param port port
     */
    public ClientSocketHandler(int port) {
        LOGGER.info("Creating ServerSocket");
        this.clients = new HashSet<>();
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

    public Set<ClientThread> getClients() {
        return clients;
    }
}
