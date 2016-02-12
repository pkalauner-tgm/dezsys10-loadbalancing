package at.kalaunermalik.dezsys10.loadbalancer;

/**
 * Actual balancer
 *
 * @author Paul Kalauner 5BHIT
 * @version 20161202.1
 */
public class Balancing {
    private BalancingBehaviour behaviour;

    public Balancing(BalancingBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    public void balance() {
        this.behaviour.chooseServer();
    }
}
