package co.edu.uniquindio.proyecto.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.AuthenticationException;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import co.edu.uniquindio.entidadesProyecto.Cliente;
import co.edu.uniquindio.entidadesProyecto.Inmobiliaria;
import co.edu.uniquindio.entidadesProyecto.Usuario;
import co.edu.uniquindio.proyectoInmobiliaria.PruebaEJB;

@Named("seguridadBean")
@SessionScoped
public class SeguridadBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private boolean autenticado;
	private boolean esInmobiliaria;
	private boolean forget;
	private String emailLogin;
	private String passwordLogin;
	@EJB
	private PruebaEJB pruebaEJB;

	@PostConstruct
	public void inicializar() {

		usuario = new Usuario();
		autenticado = false;
		esInmobiliaria = false;
		forget=false;

	}
	public String desplegar()
	{
		try {
			pruebaEJB.enviarCorreoRecuperacion(emailLogin);
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Contraseña enviada a su correo");
			FacesContext.getCurrentInstance().addMessage("mensaje_sesion", msj);
			return "index?faces-redirect=true";
		} catch (Exception e) {
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Error al verificar correo");
			FacesContext.getCurrentInstance().addMessage("mensaje_sesion", msj);
		}
		return "";
	}
	public String autenticarUsuario() {

		try {
			usuario = pruebaEJB.autenticarUsuario(emailLogin, passwordLogin);
			autenticado = true;
			if(usuario instanceof Inmobiliaria)
			{
				esInmobiliaria=true;
			}
			return "index?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("mensaje_sesion", msj);
		}

		return "";
	}
	
	public String cerrarSesion()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index?faces-redirect=true";
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEmailLogin() {
		return emailLogin;
	}

	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	public String getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}

	public boolean isEsInmobiliaria() {
		return esInmobiliaria;
	}

	public void setEsInmobiliaria(boolean esInmobiliaria) {
		this.esInmobiliaria = esInmobiliaria;
	}
	public boolean isForget() {
		return forget;
	}
	public void setForget(boolean forget) {
		this.forget = forget;
	}
	
}
