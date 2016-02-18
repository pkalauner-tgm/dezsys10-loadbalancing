package at.kalaunermalik.dezsys10.loadbalancer;

import at.kalaunermalik.dezsys10.loadbalancer.connection.ServerThread;

import java.util.Set;

/**
 * Weighted Distribution Behaviour
 *
 * @author Paul Kalauner
 * @version 20161202.1
 */

public class WeightedDistributionBehaviour implements BalancingBehaviour{
    @Override
    public ServerThread chooseServer(Set server) {
        return null; //TODO
    }
}
