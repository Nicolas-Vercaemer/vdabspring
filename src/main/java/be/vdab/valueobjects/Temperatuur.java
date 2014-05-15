package be.vdab.valueobjects;

import java.math.BigDecimal;
import java.util.Date;

public class Temperatuur {
	private BigDecimal temperatuur;
	private Date lastUpdate;

	public Temperatuur(BigDecimal temperatuur, Date lastUpdate) {
		this.temperatuur = temperatuur;
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getTemperatuur() {
		return temperatuur;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

}