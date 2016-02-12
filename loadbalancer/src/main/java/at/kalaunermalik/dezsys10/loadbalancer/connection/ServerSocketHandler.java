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
public class ServerSocketHandler extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(ServerSocketHandler.class);
    private ServerSocket serverSocket;
    private Set<ServerThread> server;

    /**
     * Initializes the ServerSocket
     *
     * @param port port
     */
    public ServerSocketHandler(int port) {
        LOGGER.info("Creating ServerSocket");
        this.server = new HashSet<>();
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
                ServerThread ct = new ServerThread(this, serverSocket.accept());
                server.add(ct);
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
        this.server.remove(ct);
    }

    /**
     * Sends a message to all server
     *
     * @param message the message which should be sent
     */
    public void broadcast(String message) {
        for (ServerThread cur : server) {
            cur.sendCommand(message);
        }
    }

    /**
     * Gets the number of available server
     *
     * @return number of server
     */
    public int getNumberOfClients() {
        return this.server.size();
    }

    public Set<ServerThread> getServer() {
        return server;
    }
}
