package mmk.omak.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.ProductBrand;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Integer>{
	
}
