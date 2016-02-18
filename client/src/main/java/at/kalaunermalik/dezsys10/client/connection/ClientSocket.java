package at.kalaunermalik.dezsys10.client.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Connection to the load balancer
 *
 * @author Paul Kalauner 5BHIT
 * @version 20160204
 */
public class ClientSocket extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(ClientSocket.class);

    private Socket socket;

    private String hostname;
    private int port;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Initializes the ClientSocket
     *
     * @param hostname hostname of the load balancer
     * @param port     port of the load balancer
     */
    public ClientSocket(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        this.connect();
    }

    private void connect() {
        try {
            this.socket = new Socket(hostname, port);
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            LOGGER.info("Connected to load balancer on " + hostname + ":" + port);
        } catch (IOException e) {
            LOGGER.error("Error while connecting to load balancer");
        }
    }

    /**
     * Sends a message to the server
     *
     * @param message the message which should be sent
     */
    public void sendMessage(String message) {
        LOGGER.debug("Sending message to load balancer: " + message);
        this.out.println(message);
    }

    /**
     * Handles the received message
     *
     * @param message the received message
     */
    private void handleMessage(String message) {
        LOGGER.debug("Received result");
        System.out.println(message);
    }

    /**
     * Tries to get one message from the load balancer
     */
    public void run() {
        try {
            String fromServer = in.readLine();
            this.handleMessage(fromServer);
            this.sendMessage("disconnect");
        } catch (IOException ie) {
            LOGGER.error("Exception while reading from server " + ie.getMessage());
        }
    }
}
