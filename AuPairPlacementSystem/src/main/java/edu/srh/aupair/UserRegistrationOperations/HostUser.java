package edu.srh.aupair.UserRegistrationOperations;

import java.sql.Date;

public class HostUser {
	
	private String personType;
	private String firstName;
	private String lastName;
	private String emailid;
	private String contactNo;
	private String gender;
	private String maritalStatus;
	private String languages;
	private String proficiency;
	private int countryCurrencyId;
	private String address;
	private String city;
	private int postCode;
	private String title;
	private String aboutMe;
	private String passportNumber; 
	private boolean isActive; 
	private String hashedUserPassword;
	private Date latestOnlineTime;
	boolean IS_SALARY_PROVIDED;
	private int NUMBER_OF_KIDS; 
	private int AGE_OF_KIDS; 
	private boolean HAS_PHYSICAL_DISABILITY;
	
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getProficiency() {
		return proficiency;
	}
	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}
	public int getCountryCurrencyId() {
		return countryCurrencyId;
	}
	public void setCountryCurrencyId(int countryCurrencyId) {
		this.countryCurrencyId = countryCurrencyId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getHashedUserPassword() {
		return hashedUserPassword;
	}
	public void setHashedUserPassword(String hashedUserPassword) {
		this.hashedUserPassword = hashedUserPassword;
	}
	public Date getLatestOnlineTime() {
		return latestOnlineTime;
	}
	public void setLatestOnlineTime(Date latestOnlineTime) {
		this.latestOnlineTime = latestOnlineTime;
	}
	public boolean isIS_SALARY_PROVIDED() {
		return IS_SALARY_PROVIDED;
	}
	public void setIS_SALARY_PROVIDED(boolean iS_SALARY_PROVIDED) {
		IS_SALARY_PROVIDED = iS_SALARY_PROVIDED;
	}
	public int getNUMBER_OF_KIDS() {
		return NUMBER_OF_KIDS;
	}
	public void setNUMBER_OF_KIDS(int nUMBER_OF_KIDS) {
		NUMBER_OF_KIDS = nUMBER_OF_KIDS;
	}
	public int getAGE_OF_KIDS() {
		return AGE_OF_KIDS;
	}
	public void setAGE_OF_KIDS(int aGE_OF_KIDS) {
		AGE_OF_KIDS = aGE_OF_KIDS;
	}
	public boolean isHAS_PHYSICAL_DISABILITY() {
		return HAS_PHYSICAL_DISABILITY;
	}
	public void setHAS_PHYSICAL_DISABILITY(boolean hAS_PHYSICAL_DISABILITY) {
		HAS_PHYSICAL_DISABILITY = hAS_PHYSICAL_DISABILITY;
	}
	
	
	
	
	
}