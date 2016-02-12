package at.kalaunermalik.dezsys10.loadbalancer;

/**
 * Represents a Balancing Behaviour
 *
 * @author Paul Kalauner 5BHIT
 * @version 20161202.1
 */
public interface BalancingBehaviour {

    /**
     * Choses the server
     */
    void chooseServer();
}
