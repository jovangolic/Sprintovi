package com.ftninformatika.jwd.modul3.test.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Stanje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String ime;
	
	@OneToMany(mappedBy = "stanje", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Zadatak> zadaci = new ArrayList<>();

	public Stanje() {
		super();
	}

	public Stanje(Long id, String ime, List<Zadatak> zadaci) {
		super();
		this.id = id;
		this.ime = ime;
		this.zadaci = zadaci;
	}
	
	
	public void removeStanje(Long id) {
		for(Zadatak z: zadaci) {
			if(z.getId() == id) {
				zadaci.remove(z);
				return;
			}
		}
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

	public List<Zadatak> getZadaci() {
		return zadaci;
	}

	public void setZadaci(List<Zadatak> zadaci) {
		this.zadaci = zadaci;
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
		Stanje other = (Stanje) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Stanje [id=" + id + ", ime=" + ime + "]";
	}
	
	
	
}
