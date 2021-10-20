package co.edu.uniquindio.proyecto.beans;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import co.edu.uniquindio.entidadesProyecto.Cliente;
import co.edu.uniquindio.entidadesProyecto.Usuario;
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJB;

@Named("usuarioBean")
@RequestScoped
public class UsuarioBean {

	private Cliente cliente;
	private Usuario usuario;
	@EJB
	private PruebaEJB pruebaEJB;
	@NotNull
	private Integer codigoCliente;
	
	@Email(message = "El correo electronico no es valido")
	private String emailCliente;
	@Past
	private Date fechaDeNacimiento;
	@NotNull
	private String nombreCliente;
	@NotNull
	private String passwordCliente;
	@Size(max = 20)
	private String telefonoCliente;

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getPasswordCliente() {
		return passwordCliente;
	}

	public void setPasswordCliente(String passwordCliente) {
		this.passwordCliente = passwordCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public UsuarioBean() {
		cliente = new Cliente();
	}

	public void registrarCliente() {

		try {
			Cliente cliente = new Cliente();
			cliente.setCodigo(codigoCliente);
			cliente.setEmail(emailCliente);
			cliente.setFecha_nacimiento(fechaDeNacimiento);
			cliente.setNombre(nombreCliente);
			cliente.setPassword(passwordCliente);
			cliente.setTelefono(telefonoCliente);
			pruebaEJB.registrarUsuario(cliente);
			Usuario usuario = new Usuario();
			usuario.setCodigo(codigoCliente+1);
			usuario.setEmail(emailCliente);
			usuario.setNombre(nombreCliente);
			usuario.setPassword(passwordCliente);
			pruebaEJB.registrarUsuario(usuario);
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El registro fue exitoso");
			FacesContext.getCurrentInstance().addMessage("mensajes_bean", msj);

		} catch (Exception e) {

			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("mensajes_bean", msj);

		}

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
