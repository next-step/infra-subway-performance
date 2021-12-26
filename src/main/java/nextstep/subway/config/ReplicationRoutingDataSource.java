package nextstep.subway.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
    static final String DATASOURCE_KEY_MASTER = "master";
    static final String DATASOURCE_KEY_SLAVE = "slave";

    @Override
    protected Object determineCurrentLookupKey() {

        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        return (isReadOnly) ? DATASOURCE_KEY_SLAVE : DATASOURCE_KEY_MASTER;
    }
}
