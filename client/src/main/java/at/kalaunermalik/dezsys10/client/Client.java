package at.kalaunermalik.dezsys10.client;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client
 *
 * @author Paul Kalauner 5BHIT
 * @version 20161202.1
 */
public class Client {
    private static final Logger LOGGER = LogManager.getLogger(Client.class);
    private static final String LB_URL = "localhost";
    private static final int LB_PORT = 17171;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client() throws IOException {
        this.initSockets();
    }

    /**
     * Main-Method
     * @param args CLI-args
     */
    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting Client...");
        Client client = new Client();
    }

    private void sendNumber(int number){

    }

    private void initSockets() throws IOException {
        this.socket = new Socket(LB_URL, LB_PORT);
        this.out = new PrintWriter(socket.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
