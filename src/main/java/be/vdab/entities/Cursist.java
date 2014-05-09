package be.vdab.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cursisten")
public class Cursist {
	@Id
	@GeneratedValue
	private long id;
	@NotNull
	@NotBlank
	@Length(min = 1, max = 45)
	private String voornaam;
	@NotNull
	@NotBlank
	@Length(min = 1, max = 45)
	private String familienaam;
	@NotNull
	@Temporal(TemporalType.DATE)
	@Past
	@DateTimeFormat(style = "S-")
	private Date geboorteDatum;
	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(style = "S-")
	private Date beginDatum;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style = "S-")
	private Date eindDatum;
	@Email
	@NotBlank
	@NotNull
	@Length(min = 1, max = 45)
	private String email;

	public Cursist(String voornaam, String familienaam, Date geboorteDatum,
			Date beginDatum, Date eindDatum, String email) {

		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.geboorteDatum = geboorteDatum;
		this.beginDatum = beginDatum;
		this.eindDatum = eindDatum;
		this.email = email;
	}

	public Cursist(String voornaam, String familienaam, Date geboorteDatum,
			Date beginDatum, String email) {

		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.geboorteDatum = geboorteDatum;
		this.beginDatum = beginDatum;
		this.email = email;
	}

	public Cursist(long id, String voornaam, String familienaam,
			Date geboorteDatum, Date beginDatum, Date eindDatum, String email) {
		this.id = id;
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.geboorteDatum = geboorteDatum;
		this.beginDatum = beginDatum;
		this.eindDatum = eindDatum;
		this.email = email;
	}

	public Cursist(long id, String voornaam, String familienaam,
			Date geboorteDatum, Date beginDatum, String email) {
		this.id = id;
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.geboorteDatum = geboorteDatum;
		this.beginDatum = beginDatum;
		this.email = email;
	}

	public Cursist() {

	}

	public long getId() {
		return id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public Date getGeboorteDatum() {
		return geboorteDatum;
	}

	public Date getBeginDatum() {
		return beginDatum;
	}

	public Date getEindDatum() {
		return eindDatum;
	}

	public String getEmail() {
		return email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public void setGeboorteDatum(Date geboorteDatum) {
		this.geboorteDatum = geboorteDatum;
	}

	public void setBeginDatum(Date beginDatum) {
		this.beginDatum = beginDatum;
	}

	public void setEindDatum(Date eindDatum) {
		this.eindDatum = eindDatum;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@AssertTrue
	public boolean isEindDatumOpOfNaBeginDatum() {
		if (eindDatum == null || beginDatum == null) {
			return true;
		}
		return eindDatum.after(beginDatum);
	}

}
