package com.sgg.suivisprod.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Booth {
	
	@Min(value = 1)
	private int		boothId;
	
	@Pattern(regexp = "^(https:\\/\\/|http:\\/\\/|www.)")
	private String	boothUrl;
	
	@Min(value = 0)
	@Max(value = 1000)
	private int		nbBefore;
	
	@Min(value = 0)
	@Max(value = 1000)
	private int		nbAfter;
	
	private String	category;
	
	@Size(min = 3, max = 10)
	private String	statutCom;
	
	@Size(min = 1, max = 1)
	private String	statutIVP;
	private String	companyName;

	public int getBoothId() {
		return boothId;
	}

	public void setBoothId(int boothId) {
		this.boothId = boothId;
	}

	public String getBoothUrl() {
		return boothUrl;
	}

	public void setBoothUrl(String boothUrl) {
		this.boothUrl = boothUrl;
	}

	public int getNbBefore() {
		return nbBefore;
	}

	public void setNbBefore(int nbBefore) {
		this.nbBefore = nbBefore;
	}

	public int getNbAfter() {
		return nbAfter;
	}

	public void setNbAfter(int nbAfter) {
		this.nbAfter = nbAfter;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatutCom() {
		return statutCom;
	}

	public void setStatutCom(String statutCom) {
		this.statutCom = statutCom;
	}

	public String getStatutIVP() {
		return statutIVP;
	}

	public void setStatutIVP(String statutIVP) {
		this.statutIVP = statutIVP;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;

		if (getClass() != obj.getClass()) return false;

		Booth other = (Booth) obj;

		return boothId == other.boothId;
	}

}
