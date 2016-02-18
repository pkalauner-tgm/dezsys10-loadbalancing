package at.kalaunermalik.dezsys10.loadbalancer.connection;

import at.kalaunermalik.dezsys10.loadbalancer.CalculationRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Paul on 18.02.2016.
 */
public class ClientCommandHandler extends CommandHandler {
    private static final Logger LOGGER = LogManager.getLogger(ClientCommandHandler.class);

    public ClientCommandHandler(SocketHandler sh) {
        super(sh);
    }

    @Override
    public void handleCommand(ClientThread clientThread, String command) {
        LOGGER.debug("Handling command from client " + clientThread.getIp() + ": " + command);

       CalculationRequest request = sh.balancing.balance(clientThread);

        if (request.getCalculationServer() != null) {
            LOGGER.info("Sending calculation command to server " + request.getCalculationServer().getIp());
            request.getCalculationServer().sendCommand(request.getUuid().toString() + " " + command);
        } else {
            LOGGER.error("No servers available!");
        }
    }
}
