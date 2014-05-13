package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "onderwerpen")
public class Onderwerp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	@Length(min = 1, max = 45)
	@NotNull
	@NotBlank
	private String naam;
	@NotNull
	private boolean afwezig;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public boolean isAfwezig() {
		return afwezig;
	}

	public void setAfwezig(boolean afwezig) {
		this.afwezig = afwezig;
	}

	public Onderwerp(String naam, boolean afwezig) {
		this.naam = naam;
		this.afwezig = afwezig;
	}

	public Onderwerp() {

	}

}
