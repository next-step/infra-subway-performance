package nextstep.subway.common.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    private static final Logger log = LoggerFactory.getLogger(ReplicationRoutingDataSource.class);

    public static final String DATASOURCE_KEY_MASTER = "master";
    public static final String DATASOURCE_KEY_SLAVE = "slave";

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        final String selectedKey = (isReadOnly) ? DATASOURCE_KEY_SLAVE : DATASOURCE_KEY_MASTER;
        log.debug("Routing datasource='{}'", selectedKey);
        return selectedKey;
    }
}
