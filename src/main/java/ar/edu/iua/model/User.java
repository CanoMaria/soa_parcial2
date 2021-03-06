package ar.edu.iua.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	private static final long serialVersionUID = 4647570640764087147L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 80, nullable = false)
	private String nombre;

	@Column(length = 300, nullable = false, unique = true)
	private String email;

	@Column(length = 80, nullable = false)
	private String apellido;

	@Column(length = 30, nullable = false, unique = true)
	private String username;

	@Column(length = 100)
	private String password;

	@ManyToOne
	@JoinColumn(name = "id_rol_principal")
	private Rol rolPrincipal;

	public Rol getRolPrincipal() {
		return rolPrincipal;
	}

	public void setRolPrincipal(Rol rolPrincipal) {
		this.rolPrincipal = rolPrincipal;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "id_user", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_rol", referencedColumnName = "id") })
	private Set<Rol> roles;

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	@Column(columnDefinition = "tinyint default 1")
	private boolean accountNonExpired = true;

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(columnDefinition = "tinyint default 1")
	private boolean accountNonLocked = true;

	@Column(columnDefinition = "tinyint default 1")
	private boolean credentialsNonExpired = true;

	@Column(columnDefinition = "tinyint default 1")
	private boolean enabled;

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		/*
		 * List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>(); for(Rol
		 * role:getRoles()) { authorities.add(new
		 * SimpleGrantedAuthority(role.getRol())); }
		 */

		List<GrantedAuthority> authorities = getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRol()))
				.collect(Collectors.toList());

		return authorities;
	}

	@Transient
	public String getNombreCompleto() {
		return String.format("%s, %s", getApellido(), getNombre());
	}

	public String checkAccount(PasswordEncoder passwordEncoder, String password) {
		if (!passwordEncoder.matches(password, getPassword()))
			return "BAD_PASSWORD";
		if (!isEnabled())
			return "ACCOUNT_NOT_ENABLED";
		if (!isAccountNonLocked())
			return "ACCOUNT_LOCKED";
		if (!isCredentialsNonExpired())
			return "CREDENTIALS_EXPIRED";
		if (!isAccountNonExpired())
			return "ACCOUNT_EXPIRED";

		return null;
	}

	@Transient
	private String sessionToken;

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	/*
	 * 
	 * R1-------- R2-------- R3-------- .stream()
	 * 
	 * R1-------- R2-------- R3--------
	 * 
	 * .collect()
	 * 
	 * R1-------- R2-------- R3--------
	 * 
	 */

}
