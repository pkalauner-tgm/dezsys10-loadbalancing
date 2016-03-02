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
    private Map<String, ClientThread> sessionPersistence;

    /**
     * Starts sockets and sets BalancingBehaviour
     */
    public Balancing(BalancingBehaviour behaviour) {
        this.sessionPersistence = new HashMap<>();
        this.behaviour = behaviour;
        this.requests = new HashSet<>();
        this.initSockets();
        this.csh.start();
        this.ssh.start();
    }

    /**
     * Initializes sockets and CommandHandlers
     */
    private void initSockets() {
        this.csh = new SocketHandler(this, 17171);
        this.ssh = new SocketHandler(this, 17172);

        this.csh.setCommandHandler(new ClientCommandHandler(this.csh));
        this.ssh.setCommandHandler(new ServerCommandHandler(this.ssh));
    }

    /**
     * Handles Sessionpersistence and creates Calculationrequest with the chosen server
     *
     * @param client
     * @return
     */
    public CalculationRequest balance(ClientThread client) {
        if (ssh.getClients().isEmpty())
            return null;
        ClientThread chosenServer = LoadBalancer.PERSIST_SESSION ? sessionPersistence.get(client.getIp()) : null;

        if (chosenServer == null || !ssh.getClients().contains(chosenServer)) {
            chosenServer = this.behaviour.chooseServer(this.ssh.getClients());
            this.sessionPersistence.put(client.getIp(), chosenServer);
        }
        CalculationRequest request = new CalculationRequest(chosenServer, client);
        this.requests.add(request);
        return request;
    }

    public void removeCalculationRequest(CalculationRequest cr) {
        this.requests.remove(cr);
    }

    /**
     * Gets a CalculationRequest by the given UUID
     *
     * @param uuid UUID of t
     * @return CalculationRequest
     */
    public CalculationRequest getRequestByUuid(UUID uuid) {
        for (CalculationRequest request : this.requests)
            if (request.getUuid().equals(uuid))
                return request;
        return null;
    }
}
