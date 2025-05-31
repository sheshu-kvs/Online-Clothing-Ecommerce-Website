package com.sheshu.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userid;

    @Column(name = "username", unique = true, nullable = false, length = 255)
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 255, message = "Username must be between 3 and 255 characters")
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "email", unique = true, length = 100)
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "mobile", nullable = false, length = 15)
    @NotBlank(message = "Mobile number is required")
    @Size(max = 15, message = "Mobile number must be less than 15 characters")
    @Pattern(regexp = "^[0-9]+$", message = "Mobile number must contain only digits")
    private String mobile;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Address is required")
    private String address;

    @Column(name = "city", length = 50)
    @Size(max = 50, message = "City must be less than 50 characters")
    private String city;

    @Column(name = "state", length = 50)
    @Size(max = 50, message = "State must be less than 50 characters")
    private String state;

    @Column(name = "pincode", length = 10)
    @Size(max = 10, message = "Pincode must be less than 10 characters")
    private String pincode;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "userid")
    )
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();
    
    public User() {
        this.enabled = true;
        this.roles = new HashSet<>();
        this.roles.add("USER"); // Store without ROLE_ prefix
    }

    // Getters and Setters (same as yours)

    public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	 @Override
	    public boolean isEnabled() {
	        return this.enabled;
	    }
	
	 @Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
	     return this.roles.stream()
	         .map(role -> new SimpleGrantedAuthority(role.startsWith("ROLE_") ? role : "ROLE_" + role))
	         .collect(Collectors.toList());
	 }

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}



    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles.stream()
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .collect(Collectors.toSet());
    }

    // Implement UserDetails methods
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

   
}
  


