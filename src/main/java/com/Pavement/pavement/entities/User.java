package com.Pavement.pavement.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Pavement.pavement.dto.JwtAuthenticationResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;



@Data
@Entity
@Table(name= "USERS")
public class User  implements UserDetails{

	@Id
	@SequenceGenerator(
			name="User_sequence",
			sequenceName="User_sequence",
			allocationSize =1
			)
	
	@GeneratedValue(
			strategy= GenerationType.SEQUENCE,
			generator ="User_sequence"
			)
	private int id;
	
	@Transient
	private JwtAuthenticationResponse jwtAuthentication;
	
	@Column
	private String firstName,secondName,email,password;
	
	@JsonManagedReference(value="users")	
	@ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name="USER_ROLES",
				joinColumns = @JoinColumn(name="user_id",referencedColumnName="id"),
				inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="id"))
	private Collection<Roles>roles=new HashSet<>();

	@JsonIgnore
	@JsonManagedReference(value="admissibility_User")
	@OneToMany(mappedBy = "user")
	private List<Admissibility> admissibility;
	
	public List<Admissibility> getAdmissibility() {
		return admissibility;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getEmail() {
		return email;
	}

	public Collection<Roles> getRoles() {
		return roles;
	}
	
	
	public JwtAuthenticationResponse getJwtToken() {
		return jwtAuthentication;
	}

	public void setAdmissibility(List<Admissibility> admissibility) {
		this.admissibility = admissibility;
	}

	public void setJwtToken(JwtAuthenticationResponse jwtAuthentication) {
		this.jwtAuthentication = jwtAuthentication;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public void setRoles(Collection<Roles> roles) {
		this.roles = roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		  return roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
