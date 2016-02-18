package at.kalaunermalik.dezsys10.loadbalancer;

import at.kalaunermalik.dezsys10.loadbalancer.connection.ClientThread;

import java.util.Set;

/**
 * Weighted Distribution Behaviour
 *
 * @author Paul Kalauner
 * @version 20161202.1
 */

public class WeightedDistributionBehaviour implements BalancingBehaviour{
    @Override
    public ClientThread chooseServer(Set<ClientThread> server) {
        return null; //TODO
    }
}
