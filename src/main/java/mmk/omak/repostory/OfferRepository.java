package mmk.omak.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{
	
}
