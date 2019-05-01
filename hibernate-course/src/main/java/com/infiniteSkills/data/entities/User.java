package com.infiniteSkills.data.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.annotations.Formula;

@Entity
@Table(name="FINANCES_USER")
@Access(value=AccessType.PROPERTY)
public class User {

	private Long userId;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String emailAddress;
	private Date lastUpdatedDate;
	private String lastUpdatedBy;
	private Date createdDate;
	private String createdBy;
	private int age;
	private List<Address> adress = new ArrayList<Address>();
	private Credential credential;

	
	@OneToOne(mappedBy = "user")
	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	@ElementCollection
	@CollectionTable(name ="USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
	@AttributeOverrides({@AttributeOverride(name="addressLine1", column=@Column(name="USER_ADDRESS_LINE_1")),
	@AttributeOverride(name="addressLine2", column=@Column(name="USER_ADDRESS_LINE_2")),
	@AttributeOverride(name="zipCode", column=@Column(name="ZIP_CODE"))})
	public List<Address> getAdress() {
		return adress;
	}

	public void setAdress(List<Address> adress) {
		this.adress = adress;
	}
	@Formula("lower(datediff(curdate(), birth_date)/365)")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private boolean valid;
	
	@Transient//Para que se la sude a hiberante
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Id
	@GeneratedValue
	//@GeneratedValue(strategy=GenerationType.TABLE, generator = "user_table_generator")
	//@TableGenerator(name = "user_table_generator", table = "IFINANCES_KEYS", pkColumnName = "PK_NAME", valueColumnName = "PK_VALUE")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="BIRTH_DATE")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name="EMAIL_ADDRESS")
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name="LAST_UPDATED_DATE")
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@Basic //nullable lo mismo
	@Column(name="LAST_UPDATED_BY", nullable = false)
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name="CREATED_DATE", updatable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="CREATED_BY", updatable = false)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
