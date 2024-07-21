package mmk.omak.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.OfferLine;

@Repository
public interface OfferLineRepository extends JpaRepository<OfferLine, Long>{
	
}
