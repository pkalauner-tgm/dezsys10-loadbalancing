package at.kalaunermalik.dezsys10.server.connection;

import at.kalaunermalik.dezsys10.server.PiCalculation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;

/**
 * Connection to the load balancer
 *
 * @author Paul Kalauner 5BHIT
 * @version 20160204
 */
public class ClientSocket {
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
            this.startListening();
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
        LOGGER.debug("Received message from server: " + message);
        new Thread(() -> {
            String arr[] = message.split(" ");
            int decimalPlaces = Integer.parseInt(arr[1]);
            LOGGER.info("Calculating pi to " + decimalPlaces + " decimal places");
            BigDecimal res = PiCalculation.calculatePi(decimalPlaces);
            sendMessage(arr[0] + " " + res.toString());
        }).start();
    }

    /**
     * Starts to listen from the server in a loop
     */
    public void startListening() {
        String fromServer;
        try {
            while (true) {
                while ((fromServer = in.readLine()) != null) {
                    this.handleMessage(fromServer);
                }
                LOGGER.error("Connection to load balancer lost. Trying to connect again...");
                // Try to reconnect every 20 seconds
                Thread.sleep(20000);
                this.connect();
            }
        } catch (IOException e) {
            LOGGER.error("Exception while listening to server");
        } catch (InterruptedException ie) {
            LOGGER.error("Exception while Thread.sleep()");
        }
    }
}
