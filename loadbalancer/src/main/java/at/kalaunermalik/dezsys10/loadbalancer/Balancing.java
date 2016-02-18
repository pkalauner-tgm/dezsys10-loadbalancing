package at.kalaunermalik.dezsys10.loadbalancer;

import at.kalaunermalik.dezsys10.loadbalancer.connection.ClientCommandHandler;
import at.kalaunermalik.dezsys10.loadbalancer.connection.ClientThread;
import at.kalaunermalik.dezsys10.loadbalancer.connection.ServerCommandHandler;
import at.kalaunermalik.dezsys10.loadbalancer.connection.SocketHandler;

import java.util.*;

/**
 * Actual balancer
 *
 * @author Paul Kalauner 5BHIT
 * @version 20161202.1
 */
public class Balancing {
    private BalancingBehaviour behaviour;
    private SocketHandler csh;
    private SocketHandler ssh;
    private Set<CalculationRequest> requests;

    public Balancing(BalancingBehaviour behaviour) {
        this.behaviour = behaviour;
        this.requests = new HashSet<>();
        this.initSockets();
        this.csh.start();
        this.ssh.start();
    }

    private void initSockets() {
        this.csh = new SocketHandler(this, 17171);
        this.ssh = new SocketHandler(this, 17172);

        this.csh.setCommandHandler(new ClientCommandHandler(this.csh));
        this.ssh.setCommandHandler(new ServerCommandHandler(this.ssh));
    }

    public CalculationRequest balance(ClientThread client) {
        ClientThread chosenServer = this.behaviour.chooseServer(this.ssh.getClients());
        CalculationRequest request = new CalculationRequest(chosenServer, client);
        this.requests.add(request);
        return request;
    }

    public void removeCalculationRequest(CalculationRequest cr) {
        this.requests.remove(cr);
    }

    public CalculationRequest getRequestByUuid(UUID uuid) {
        for (CalculationRequest request : this.requests)
            if (request.getUuid().equals(uuid))
                return request;
        return null;
    }
}
