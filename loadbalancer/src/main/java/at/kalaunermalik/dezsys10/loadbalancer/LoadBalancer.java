package at.kalaunermalik.dezsys10.loadbalancer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LoadBalancer
 *
 * @author Paul Kalauner
 * @version 20161202.1
 */
public class LoadBalancer {
    private static final Logger LOGGER = LogManager.getLogger(LoadBalancer.class);

    /**
     * Main-Method
     * @param args CLI-args
     */
    public static void main(String[] args) {
        LOGGER.info("Starting LoadBalancer...");
    }
}
