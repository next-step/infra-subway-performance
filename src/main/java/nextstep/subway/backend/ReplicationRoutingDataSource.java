package nextstep.subway.backend;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Profile({"local","prod"})
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
    public static final String DATASOURCE_KEY_MASTER = "master";
    public static final String DATASOURCE_KEY_SLAVE = "slave";

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        return (isReadOnly)
            ? DATASOURCE_KEY_SLAVE
            : DATASOURCE_KEY_MASTER;
    }
}