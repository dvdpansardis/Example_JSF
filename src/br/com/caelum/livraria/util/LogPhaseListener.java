package br.com.caelum.livraria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@SuppressWarnings("serial")
public class LogPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		System.out.println("PHASE: " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.ANY_PHASE;
	}

}
