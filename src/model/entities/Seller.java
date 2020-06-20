package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Seller implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer Id;
	private String Name;
	private String Email;
	private Date BirthDate;
	private Double BaseSalary;
	
	private Department departament;
	
	public Seller() {
	}

	public Seller(Integer id, String name, String email, Date birthDate, Double baseSalary, Department departament) {
		Id = id;
		Name = name;
		Email = email;
		BirthDate = birthDate;
		BaseSalary = baseSalary;
		this.departament = departament;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}

	public Double getBaseSalary() {
		return BaseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		BaseSalary = baseSalary;
	}

	public Department getDepartament() {
		return departament;
	}

	public void setDepartament(Department departament) {
		this.departament = departament;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seller [Id=" + Id + ", Name=" + Name + ", Email=" + Email + ", BirthDate=" + BirthDate + ", BaseSalary="
				+ BaseSalary + ", departament=" + departament + "]";
	}
	
}
