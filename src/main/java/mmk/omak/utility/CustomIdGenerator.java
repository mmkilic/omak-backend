package mmk.omak.utility;

import java.lang.reflect.Member;
import java.time.OffsetDateTime;
import java.util.EnumSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import static org.hibernate.internal.util.ReflectHelper.getPropertyType;

public class CustomIdGenerator implements BeforeExecutionGenerator{
	private static final long serialVersionUID = 8411886285438102916L;
	private final CounterService counterService;
	
	public CustomIdGenerator(CustomId config, Member idMember, CustomIdGeneratorCreationContext creationContext) {

        final Class<?> propertyType = getPropertyType(idMember);

        if (!config.tableName().isBlank()) {
            counterService = new PersistentCounterService(config.tableName(), config.initialNum());
        } else {
            counterService = new InMemoryCounterService(config.initialNum());
        }

        if (!int.class.isAssignableFrom(propertyType)) {
            throw new HibernateException("Unanticipated return type [" + propertyType.getName() + "] for CustomId conversion");
        }
    }
	
	
	@Override
	public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
		var now = OffsetDateTime.now();
		int month = now.getMonthValue();
		int year = now.getYear() % 100; 
		int num = counterService.nextInteger(session);
		
		return Integer.valueOf(String.format("%02d%02d%d", year, month, num));
	}
	
	@Override
	public EnumSet<EventType> getEventTypes() {
		return EventTypeSets.INSERT_ONLY;
	}
	
	interface CounterService {
        int nextInteger(SharedSessionContractImplementor session);
    }
}
