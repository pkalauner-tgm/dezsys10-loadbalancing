package at.kalaunermalik.dezsys10.loadbalancer;

import at.kalaunermalik.dezsys10.loadbalancer.connection.ClientThread;

import java.util.List;

/**
 * Least Connections Behaviour
 *
 * @author Patrick Malik 5BHIT
 * @version 20161202.1
 */
public class LeastConnectionsBehaviour implements BalancingBehaviour {
    @Override
    public ClientThread chooseServer(List<ClientThread> server) {
        return server.get(0); //TODO
    }
}
