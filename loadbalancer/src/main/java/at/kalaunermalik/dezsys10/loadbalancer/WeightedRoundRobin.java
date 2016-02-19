package at.kalaunermalik.dezsys10.loadbalancer;

import at.kalaunermalik.dezsys10.loadbalancer.connection.ClientThread;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Weighted Distribution Behaviour
 *
 * @author Paul Kalauner
 * @version 20161202.1
 */

public class WeightedRoundRobin implements BalancingBehaviour {
    private static final Logger LOGGER = LogManager.getLogger(WeightedRoundRobin.class);

    private List<Pair<Integer, Integer>> weights;
    private int[] intweights;

    public WeightedRoundRobin(int... weights) {
        if (weights.length == 0)
            throw new IllegalArgumentException("No weights given");

        LOGGER.info("Using WeightedDistributionBehaviour: " + Arrays.toString(weights));
        this.intweights = weights;
        this.weights = new ArrayList<>();
        this.prepareList();
    }

    private void prepareList() {
        for (int i = 0; i < intweights.length; i++) {
            //weights.add(i, Pair.of((int) (((double) intweights[i] / IntStream.of(intweights).sum()) * 10), i));
            weights.add(i, Pair.of(intweights[i], i));
        }
    }

    @Override
    public ClientThread chooseServer(List<ClientThread> server) {
        Collections.sort(weights, (a, b) -> {
            double d1 = (double) server.get(a.getRight()).getOpenCalculations() / a.getLeft();
            double d2 = (double) server.get(b.getRight()).getOpenCalculations() / b.getLeft();
            return d1 < d2 ? -1 : d1 > d2 ? 1 : a.getLeft() > b.getLeft() ? -1 : a.getLeft() < b.getLeft() ? 1 : a.getRight() < b.getRight() ? -1 : a.getRight() > b.getRight() ? 1 : 0;
        });
        return server.get(weights.get(0).getRight());
    }
}
