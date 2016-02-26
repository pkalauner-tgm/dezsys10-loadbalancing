package at.kalaunermalik.dezsys10.server;

import at.kalaunermalik.dezsys10.server.connection.ClientSocket;
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
    private static String lb_url = "localhost";
    private static final int LB_PORT = 17172;

    /**
     * Main-Method
     * @param args CLI-args
     */
    public static void main(String[] args) throws IOException {
        LOGGER.info("Starting Server...");

        if (args.length >= 1) {
            lb_url = args[0];
        } else
          LOGGER.info("No IP specified, using " + lb_url);

        new ClientSocket(lb_url, LB_PORT);
    }
}
