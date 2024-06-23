package mmk.omak.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
