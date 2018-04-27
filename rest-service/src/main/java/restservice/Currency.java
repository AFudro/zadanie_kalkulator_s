package restservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "currency")
public class Currency {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String country;
	private String currency;
	@JsonIgnore
	private double tax;
	@JsonIgnore
	@Column(name = "fixed_cost")
	private double fixedCost;
	@JsonIgnore
	@Column(name = "api_table")
	private char apiTable;

	public Currency() {
	}

	public Currency(long id, String country, String currency, double tax, double fixedCost, char apiTable) {
		super();
		this.id = id;
		this.country = country;
		this.currency = currency;
		this.tax = tax;
		this.fixedCost = fixedCost;
		this.apiTable = apiTable;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(double fixedCost) {
		this.fixedCost = fixedCost;
	}

	public char getApiTable() {
		return apiTable;
	}

	public void setApiTable(char apiTable) {
		this.apiTable = apiTable;
	}

}
