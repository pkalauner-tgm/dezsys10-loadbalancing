package at.kalaunermalik.dezsys10.loadbalancer;

import at.kalaunermalik.dezsys10.loadbalancer.connection.ServerThread;

import java.util.Set;

/**
 * Least Connections Behaviour
 *
 * @author Patrick Malik 5BHIT
 * @version 20161202.1
 */
public class LeastConnectionsBehaviour implements BalancingBehaviour {
    @Override
    public ServerThread chooseServer(Set<ServerThread> server) {

    }
}
