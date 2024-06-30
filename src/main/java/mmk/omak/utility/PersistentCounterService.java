package mmk.omak.utility;

import java.time.LocalDateTime;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

public class PersistentCounterService implements CustomIdGenerator.CounterService{
	private final int DEFAULT_COUNTER_START;
	private String tableName;
    
    public PersistentCounterService(String tableName, int initialNum) {
    	this.tableName = tableName;
    	DEFAULT_COUNTER_START = initialNum;
    }
    
    @Override
    public int nextInteger(SharedSessionContractImplementor session) {
        var statelessSession = session.isStatelessSession() ? session.asStatelessSession() : session.getSession();
        statelessSession.createNativeMutationQuery("""
	            CREATE TABLE IF NOT EXISTS %s (
	            	month integer primary key not null,
	                counter_value integer not null default %d);
	            """.formatted(tableName, DEFAULT_COUNTER_START))
                .executeUpdate();
        
        var now = LocalDateTime.now();
        int month = Integer.valueOf(String.format("%d%02d", now.getYear(), now.getMonthValue()));
        
        var list = statelessSession.createNativeQuery("""
        			SELECT counter_value FROM %s WHERE month = (:month);
        		""".formatted(tableName), Integer.class)
        		.setParameter("month", month)
                .setFetchSize(1)
                .getResultList();
        
        if (list.isEmpty()) {
            statelessSession.createNativeMutationQuery("""
            		INSERT INTO %s(month, counter_value) VALUES (:month, :counter);
            	""".formatted(tableName))
            	.setParameter("month", month)
            	.setParameter("counter", DEFAULT_COUNTER_START+1)
            	.executeUpdate();
            return DEFAULT_COUNTER_START+1;
        }

        statelessSession.createNativeMutationQuery("""
        			UPDATE %s SET counter_value = counter_value + 1 WHERE month = (:month);
        		""".formatted(tableName))
        		.setParameter("month", month)
                .executeUpdate();

        return list.get(0) + 1;
    }
}
