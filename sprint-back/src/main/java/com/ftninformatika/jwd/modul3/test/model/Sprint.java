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
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String ime;
	
	@Column(name = "ukupno_bodova")
	private String ukupnoBodova;
	
	@OneToMany(mappedBy = "sprint",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Zadatak> zadaci = new ArrayList<>();

	public Sprint() {
		super();
	}

	public Sprint(Long id, String ime, String ukupnoBodova) {
		super();
		this.id = id;
		this.ime = ime;
		this.ukupnoBodova = ukupnoBodova;
	}
	
	public void addZadatak(Zadatak zadatak) {
		this.zadaci.add(zadatak);
		this.setUkupnoBodova(Integer.parseInt(getUkupnoBodova())+zadatak.getBodovi()+"");
	}
	
	
	public void removeZadatak(Long id) {
		for(Zadatak z: zadaci) {
			if(z.getId() == id) {
				this.zadaci.remove(z);
				//smanjivanje bodova
				this.setUkupnoBodova(Integer.parseInt(getUkupnoBodova()) - z.getBodovi()+"");
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

	public String getUkupnoBodova() {
		return ukupnoBodova;
	}

	public void setUkupnoBodova(String ukupnoBodova) {
		this.ukupnoBodova = ukupnoBodova;
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
		Sprint other = (Sprint) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Sprint [id=" + id + ", ime=" + ime + ", ukupnoBodova=" + ukupnoBodova + "]";
	}

	
	
	
}
