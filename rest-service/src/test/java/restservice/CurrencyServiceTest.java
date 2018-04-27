package restservice;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyServiceTest {

	  @MockBean
	  private CurrencyRepository repository;
	 
	  
	    @Autowired
	    private CurrencyService service;
	 
	    @Test
	    public void TestGetMonthlyIncome() throws Exception{
	    	Currency currency = new Currency(3, "PL", "PLN", 19, 1200, 'A');
			Mockito.when(repository.findById(3)).thenReturn(currency);
	    	
			BigDecimal result = service.getNetMonthlyIncome(3, new BigDecimal("200"));
	    	
		     assertThat(result.toString())
		      .isEqualTo("2592.00");
	    }
}
