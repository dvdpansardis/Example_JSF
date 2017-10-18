package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

@SuppressWarnings("serial")
public class Autorizador implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent evento) {

		FacesContext context = evento.getFacesContext();
		
		String nomePagina = context.getViewRoot().getViewId();
		
		System.out.println(nomePagina);
		
		if("/login.xhtml".equals(nomePagina)) return;
		
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		if(usuarioLogado != null) return;
		
		
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(context, null, "/login.xhtml?faces-redirect=true");
		context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
