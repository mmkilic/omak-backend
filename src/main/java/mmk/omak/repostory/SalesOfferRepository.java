package mmk.omak.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.SalesOffer;

@Repository
public interface SalesOfferRepository extends JpaRepository<SalesOffer, Integer>{
	
}
