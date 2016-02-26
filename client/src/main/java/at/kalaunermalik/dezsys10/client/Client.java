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
    private static String lb_url = "localhost";
    private static final int LB_PORT = 17171;
    private ClientSocket cs;

    public Client() throws IOException {
        this.cs = new ClientSocket(lb_url, LB_PORT);
        this.cs.start();
    }

    private void sendNumber(int number) {
        this.cs.sendMessage(String.valueOf(number));
    }

    /**
     * Main-Method
     *
     * @param args CLI-args
     */
    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting Client...");

        if (args.length >= 1) {
            lb_url = args[0];
        } else
            LOGGER.info("No IP specified, using " + lb_url);

        int decimalPlaces = 100000;
        if (args.length >= 2) {
            try {
                if (Integer.parseInt(args[1]) <= 0) {
                    System.out.println("Invalid number, using " + decimalPlaces + " decimal places");
                } else {
                    decimalPlaces = Integer.parseInt(args[1]);
                }
                decimalPlaces = Integer.parseInt(args[1]);
            } catch (NumberFormatException nfe) {
                LOGGER.info("Invalid number, using " + decimalPlaces + " decimal places");
            }

        }
        new Client().sendNumber(decimalPlaces);
    }
}
