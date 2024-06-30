package mmk.omak.utility;

import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

public class InMemoryCounterService implements CustomIdGenerator.CounterService{
	private final AtomicInteger id;
	
	public InMemoryCounterService(int initialNum) {
		id = new AtomicInteger(initialNum);
	}
	
    @Override
    public int nextInteger(SharedSessionContractImplementor session) {
        return id.incrementAndGet();
    }
}
