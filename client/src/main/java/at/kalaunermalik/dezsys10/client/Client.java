package at.kalaunermalik.dezsys10.client;


import at.kalaunermalik.dezsys10.client.connection.ClientSocket;
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
    private ClientSocket cs;

    public Client() throws IOException {
        this.cs = new ClientSocket(LB_URL, LB_PORT);
        this.cs.start();
    }

    private void sendNumber(int number){
        this.cs.sendMessage(String.valueOf(number));
    }

    /**
     * Main-Method
     * @param args CLI-args
     */
    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting Client...");
        int decimalPlaces = 100000;
        if (args.length >= 1) {
            try {
                decimalPlaces = Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid number, using " + decimalPlaces + " decimal places");
            }
        }
        new Client().sendNumber(decimalPlaces);
    }
}
