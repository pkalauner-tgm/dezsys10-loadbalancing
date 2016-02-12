package at.kalaunermalik.dezsys10.loadbalancer;

import at.kalaunermalik.dezsys10.loadbalancer.connection.ClientSocketHandler;
import at.kalaunermalik.dezsys10.loadbalancer.connection.ServerSocketHandler;
import at.kalaunermalik.dezsys10.loadbalancer.connection.ServerThread;

/**
 * Actual balancer
 *
 * @author Paul Kalauner 5BHIT
 * @version 20161202.1
 */
public class Balancing {
    private BalancingBehaviour behaviour;
    private ClientSocketHandler csh;
    private ServerSocketHandler ssh;

    public Balancing(BalancingBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    private void initSockets() {
        this.csh = new ClientSocketHandler(17171);
        this.ssh = new ServerSocketHandler(17172);
    }

    public void balance() {
        ServerThread chosenServer = this.behaviour.chooseServer(this.ssh.getServer());
    }
}
