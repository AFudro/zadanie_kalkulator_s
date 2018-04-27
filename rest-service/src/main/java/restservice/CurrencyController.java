package restservice;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;

	@GetMapping("/currencies")
	public List<Currency> getAll() {
		return currencyService.getAll();
	}

	@GetMapping("/currencies/{id}")
	public Currency getById(@PathVariable("id") long id) {
		return currencyService.findById(id);
	}

	@GetMapping("/net-monthly-income")
	public BigDecimal getNetMonthlyIncome(@RequestParam(value = "id", required = true) long id,
			@RequestParam(value = "amount", required = true) BigDecimal amount) throws IOException {

		return currencyService.getNetMonthlyIncome(id, amount);
	}

}
