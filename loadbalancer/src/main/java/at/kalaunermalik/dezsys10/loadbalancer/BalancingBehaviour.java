package at.kalaunermalik.dezsys10.loadbalancer;

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
     * @param server
     */
    Object chooseServer(Set server);
}
