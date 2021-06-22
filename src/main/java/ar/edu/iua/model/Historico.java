package ar.edu.iua.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import springfox.documentation.spring.web.json.Json;

@Entity
@Table(name = "historico")
public class Historico implements Serializable, UserDetails{
	
	private static final long serialVersionUID = -2096655693932225923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_historico;
	
	@Column(length = 100, nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaHoraRecepcion;

	@Column(length = 65555,nullable = false)
	private String rawData;

	@Column(length = 100,nullable = false)
	private String categoria;
	
	@Column(length = 100,nullable = false)
	private String subCategoria;
	
	@Column(length = 100,nullable = false)
	private String indicador;
	
	@Column(length = 100,nullable = false)
	private double valor;
	

	//-------Setters and Getters---------

	public double getValor() {
		return valor;
	}

	public void setValor(double d) {
		this.valor = d;
	}

	public long getId_historico() {
		return id_historico;
	}

	public void setId_historico(long id_historico) {
		this.id_historico = id_historico;
	}

	public Date getFechaHoraRecepcion() {
		return fechaHoraRecepcion;
	}

	public void setFechaHoraRecepcion(Date fechaHoraRecepcion) {
		this.fechaHoraRecepcion = fechaHoraRecepcion;
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String string) {
		this.rawData = string;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	@Override
	public String toString() {
		return "{id_historico :" + id_historico + ", fechaHoraRecepcion:" + fechaHoraRecepcion + ", categoria:"
				+ categoria + ", subCategoria:" + subCategoria + ", indicador:" + indicador + ", valor:" + valor+"}" ;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
		
}
