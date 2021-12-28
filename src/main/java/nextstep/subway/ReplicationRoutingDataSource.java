package nextstep.subway;

import org.springframework.jdbc.datasource.lookup.*;
import org.springframework.transaction.support.*;

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
