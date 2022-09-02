package com.dillip.api.response;

public class ConsumeUniversityBody {
	private String stateprovince;
	private String country;
	private String[] web_pages;
	private String name;
	private String alpha_two_code;
	private String[] domains;
	public ConsumeUniversityBody() {
		super();
	}
	public ConsumeUniversityBody(String stateprovince, String country, String[] web_pages, String name,
			String alpha_two_code, String[] domains) {
		super();
		this.stateprovince = stateprovince;
		this.country = country;
		this.web_pages = web_pages;
		this.name = name;
		this.alpha_two_code = alpha_two_code;
		this.domains = domains;
	}
	public String getStateprovince() {
		return stateprovince;
	}
	public void setStateprovince(String stateprovince) {
		this.stateprovince = stateprovince;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String[] getWeb_pages() {
		return web_pages;
	}
	public void setWeb_pages(String[] web_pages) {
		this.web_pages = web_pages;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlpha_two_code() {
		return alpha_two_code;
	}
	public void setAlpha_two_code(String alpha_two_code) {
		this.alpha_two_code = alpha_two_code;
	}
	public String[] getDomains() {
		return domains;
	}
	public void setDomains(String[] domains) {
		this.domains = domains;
	}
	
	
	
}
