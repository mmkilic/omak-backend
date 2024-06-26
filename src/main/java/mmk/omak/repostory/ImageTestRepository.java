package mmk.omak.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.ImageTest;

@Repository
public interface ImageTestRepository extends JpaRepository<ImageTest, Integer>{
	
}
