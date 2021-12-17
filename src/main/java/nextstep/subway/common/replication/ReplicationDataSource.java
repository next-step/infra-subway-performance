package nextstep.subway.common.replication;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationDataSource extends AbstractRoutingDataSource {
    public static final String MASTER = "master";
    public static final String SLAVE = "slave";

    @Override
    protected Object determineCurrentLookupKey() {
        if (isReadOnly()) {
            return SLAVE;
        }
        return MASTER;
    }

    private boolean isReadOnly() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly();
    }
}
