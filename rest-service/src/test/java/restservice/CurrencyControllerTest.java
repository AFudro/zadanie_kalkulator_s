package restservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private CurrencyService service;

	@Test
	public void TestGetAll() throws Exception {
		mvc.perform(get("/currencies")).andExpect(status().isOk());
	}

	@Test
	public void TestGetMonthlyIncome() throws Exception {
		Mockito.when(service.getNetMonthlyIncome(3, new BigDecimal("200"))).thenReturn(new BigDecimal("2592"));

		mvc.perform(get("/net-monthly-income?id=3&amount=200")).andExpect(status().isOk())
				.andExpect(content().string("2592"));

	}

	@Test
	public void TestGetById() throws Exception {
		Mockito.when(service.findById(1)).thenReturn(new Currency(1, "PL", "PLN", 19, 1200, 'A'));
		
		mvc.perform(get("/currencies/1")).andExpect(status().isOk())
		.andExpect(jsonPath("$.id", CoreMatchers.is(1)));
	}

}
