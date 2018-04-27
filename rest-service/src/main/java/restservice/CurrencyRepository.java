package restservice;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyRepository  extends CrudRepository<Currency, Long>{
	List<Currency> findAll();
	Currency findById(long id);
}
