package at.kalaunermalik.dezsys10.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

/**
 * Server
 *
 * @author Paul Kalauner 5BHIT
 * @version 20161202.1
 */
public class Server {
    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private static final int DECIMAL_PLACES = 150000;
    private static final String LB_URL = "localhost";
    private static final int LB_PORT = 17172;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Server() throws IOException {
        this.initSockets();
    }

    /**
     * Main-Method
     * @param args CLI-args
     */
    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting Server...");
        Server server = new Server();
        System.out.println(new PiCalculation().calculatePi(DECIMAL_PLACES));
    }

    private void initSockets() throws IOException {
        this.socket = new Socket(LB_URL,LB_PORT);
        this.out = new PrintWriter(socket.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
