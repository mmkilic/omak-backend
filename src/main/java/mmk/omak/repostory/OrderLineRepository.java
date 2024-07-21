package mmk.omak.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long>{
	
}
