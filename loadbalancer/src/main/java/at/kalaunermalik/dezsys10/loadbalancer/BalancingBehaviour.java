package at.kalaunermalik.dezsys10.loadbalancer;

import at.kalaunermalik.dezsys10.loadbalancer.connection.ClientThread;

import java.util.Set;

/**
 * Represents a Balancing Behaviour
 *
 * @author Paul Kalauner 5BHIT
 * @version 20161202.1
 */
public interface BalancingBehaviour {

    /**
     * Choses the server
     * @param server all connected servers
     */
    ClientThread chooseServer(Set<ClientThread> server);
}
