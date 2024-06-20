package mmk.omak.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.Line;

@Repository
public interface LineRepository extends JpaRepository<Line, Long>{
	
}
