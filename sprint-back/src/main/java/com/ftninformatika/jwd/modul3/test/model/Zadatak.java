package com.ftninformatika.jwd.modul3.test.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Zadatak {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String ime;
	
	@Column(nullable = false)
	private String zaduzeni;
	
	@Column
	private Integer bodovi;
	
	@ManyToOne()
	@JoinColumn(name = "sprint_id")
	private Sprint sprint;
	
	@ManyToOne
	@JoinColumn(name = "stanje_id")
	private Stanje stanje;

	public Zadatak() {
		super();
	}

	public Zadatak(Long id, String ime, String zaduzeni, Integer bodovi, Sprint sprint, Stanje stanje) {
		super();
		this.id = id;
		this.ime = ime;
		this.zaduzeni = zaduzeni;
		this.bodovi = bodovi;
		this.sprint = sprint;
		this.stanje = stanje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getZaduzeni() {
		return zaduzeni;
	}

	public void setZaduzeni(String zaduzeni) {
		this.zaduzeni = zaduzeni;
	}

	public Integer getBodovi() {
		return bodovi;
	}

	public void setBodovi(Integer bodovi) {
		this.bodovi = bodovi;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public Stanje getStanje() {
		return stanje;
	}

	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zadatak other = (Zadatak) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Zadatak [id=" + id + ", ime=" + ime + ", zaduzeni=" + zaduzeni + ", bodovi=" + bodovi + ", sprint="
				+ sprint + ", stanje=" + stanje + "]";
	}
	
	
}
