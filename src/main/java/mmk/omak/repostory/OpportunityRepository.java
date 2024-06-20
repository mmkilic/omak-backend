package mmk.omak.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.Opportunity;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer>{
	
}
