package at.kalaunermalik.dezsys10.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Server
 *
 * @author Paul Kalauner 5BHIT
 * @version 20161202.1
 */
public class Server {
    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private static final int DECIMAL_PLACES = 150000;

    /**
     * Main-Method
     * @param args CLI-args
     */
    public static void main(String[] args) {
        LOGGER.info("Starting Server...");

        System.out.println(new PiCalculation().calculatePi(DECIMAL_PLACES));
    }
}
