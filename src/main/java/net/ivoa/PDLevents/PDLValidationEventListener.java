package net.ivoa.PDLevents;

import java.util.EventListener;

public interface PDLValidationEventListener extends EventListener {
	public void PDLEventOccurred(PDLValidationEvent evt);	
}
