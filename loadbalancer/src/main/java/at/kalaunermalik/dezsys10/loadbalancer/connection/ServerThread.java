package at.kalaunermalik.dezsys10.loadbalancer.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Represents one Server, which calculates pi
 *
 * @author Paul Kalauner 5BHIT
 * @version 20160204
 */
public class ServerThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(ServerThread.class);
    private ServerSocketHandler sh;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Initializes the ClientThread with the given socket
     *
     * @param socket socket
     */
    public ServerThread(ServerSocketHandler sh, Socket socket) {
        LOGGER.info("New Client connected: " + socket.getInetAddress());
        this.sh = sh;
        this.socket = socket;
        this.initIO();
    }

    /**
     * Sends a command to the client
     *
     * @param cmd the command which should be sent
     */
    public void sendCommand(String cmd) {
        LOGGER.debug("Sending command to " + socket.getInetAddress() + ": " + cmd);
        this.out.println(cmd);
    }

    /**
     * Initializes Writer and Reader
     */
    private void initIO() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            LOGGER.error("Error while initializing writer and reader " + e.getMessage());
        }
    }

    private void handleCommand(String inputLine) {
    }

    /**
     * Waits for commands
     */
    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                this.handleCommand(inputLine);
            }
        } catch (Exception e) {
            LOGGER.error("Error " + e.getMessage());
        }
        LOGGER.info("Client disconnected: " + socket.getInetAddress());
        try {
            socket.close();
        } catch (IOException e) {
            LOGGER.error("Error while closing socket " + e.getMessage());
        }
    }

}
