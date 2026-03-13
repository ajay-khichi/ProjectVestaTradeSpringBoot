package com.ignishers.pojo;


import com.ignishers.enums.AccountStatus;
import com.ignishers.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {

    @Column(nullable = false, unique = true)
    private String employeeCode;

    @PrePersist
    protected void onAdminCreate() {
        super.setRole(Role.ADMIN);
        super.setAccountStatus(AccountStatus.VERIFIED);
    }

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Long id, String email, String password, String firstName, String lastName, Role role,
			String imgPath, AccountStatus accountStatus) {
		super(id, email, password, firstName, lastName, role,imgPath , accountStatus);
		// TODO Auto-generated constructor stub
	}
	

	public Admin(Long id, String firstName, String lastName, String email, String password, Role role, String imgPath,
			AccountStatus accountStatus, String employeeCode) {
		super(id, firstName, lastName, email, password, role, imgPath, accountStatus);
		this.employeeCode = employeeCode;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
    
}
