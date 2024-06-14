package mmk.omak.repostory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer>{
	
	@Query("SELECT c from Currency c where c.code=(:code)")
	Optional<Currency> getByCode(@Param("code") String code);
}
