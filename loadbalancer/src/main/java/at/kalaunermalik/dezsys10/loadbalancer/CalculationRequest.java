package at.kalaunermalik.dezsys10.loadbalancer;

import at.kalaunermalik.dezsys10.loadbalancer.connection.ClientThread;

import java.util.UUID;

/**
 * Created by Paul on 18.02.2016.
 */
public class CalculationRequest {
    private UUID uuid;
    private ClientThread calculationServer;
    private ClientThread client;

    public CalculationRequest(ClientThread calculationServer, ClientThread client) {
        this.uuid = UUID.randomUUID();
        this.calculationServer = calculationServer;
        this.client = client;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ClientThread getCalculationServer() {
        return calculationServer;
    }

    public ClientThread getClient() {
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalculationRequest that = (CalculationRequest) o;

        return uuid.equals(that.uuid);

    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
