package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public RedirectView efetuandoLogin() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (new UsuarioDao().existe(usuario)) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			return new RedirectView("livro");
		}
	
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado"));
		
		return new RedirectView("login");
	}

	public RedirectView deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return new RedirectView("login");
	}

}
