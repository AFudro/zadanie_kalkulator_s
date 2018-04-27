package restservice;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Service
public class CurrencyService {

	@Autowired
	CurrencyRepository currencyRepository;

	public BigDecimal getRate(Currency currency) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format("http://api.nbp.pl/api/exchangerates/rates/%c/%s?format=json",
				currency.getApiTable(), currency.getCurrency());
		JsonNode rootNode = restTemplate.getForObject(url, JsonNode.class);
		ArrayNode ratesNode = (ArrayNode) rootNode.get("rates");
		return new BigDecimal(ratesNode.get(0).get("mid").asText());
	}

	public List<Currency> getAll() {
		return currencyRepository.findAll();
	}

	public Currency findById(long id) {
		return currencyRepository.findById(id);
	}

	public BigDecimal getNetMonthlyIncome(long id, BigDecimal amount) throws IOException {
		Currency currency = currencyRepository.findById(id);
		BigDecimal rate;
		if (currency.getCountry().equals("PL"))
			rate = new BigDecimal("1.0");
		else
			rate = getRate(currency);

		BigDecimal percent = new BigDecimal(Double.toString((100 - currency.getTax()) / 100));
		BigDecimal income = (amount.multiply(new BigDecimal("22.00")))
				.subtract(new BigDecimal(Double.toString(currency.getFixedCost())));

		BigDecimal result = (rate.multiply(income.multiply(percent))).setScale(2, RoundingMode.HALF_UP);
		if (result.doubleValue() < 0)
			return new BigDecimal("0");
		else
			return result;
	}
}
