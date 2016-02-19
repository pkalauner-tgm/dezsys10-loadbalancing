package at.kalaunermalik.dezsys10.loadbalancer.connection;

import at.kalaunermalik.dezsys10.loadbalancer.CalculationRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

/**
 * Created by Paul on 18.02.2016.
 */
public class ServerCommandHandler extends CommandHandler {
    private static final Logger LOGGER = LogManager.getLogger(ServerCommandHandler.class);

    public ServerCommandHandler(SocketHandler sh) {
        super(sh);
    }

    @Override
    public void handleCommand(ClientThread clientThread, String command) {
        LOGGER.debug("Handling command from server " + clientThread.getIp() + ": " + command);
        String arr[] = command.split(" ");
        CalculationRequest request = this.sh.balancing.getRequestByUuid(UUID.fromString(arr[0]));
        if (request != null) {
            LOGGER.info("Sending result to client " + request.getClient().getIp());
            request.getCalculationServer().finishedCalculation();
            request.getClient().sendCommand(arr[1]);
        } else {
            LOGGER.error("No request with uuid " + arr[0] + " found!");
        }
        this.sh.balancing.removeCalculationRequest(request);
    }
}
