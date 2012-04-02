package net.ivoa.pdl.servicecaller;

import net.ivoa.parameter.model.Service;

public class ServiceCallerFactory {

	private static final String BroadServiceName = "Lerma-Broadening-Service";

	private static final ServiceCallerFactory instance = new ServiceCallerFactory();

	private static final String OpacityServiceName = "Lerma-Opacity-Service";

	private static final String PDRServiceName = "Meudon-PDR-Service";
	
	
	public static ServiceCallerFactory getInstance() {
		return instance;
	}

	private ServiceCallerFactory() {
	}

	public IserviceCaller buildCaller(Service service){
		if(service.getServiceName().equalsIgnoreCase(BroadServiceName)){
			return new BroadServiceCaller();
		}
		
		if(service.getServiceName().equalsIgnoreCase(OpacityServiceName)){
			return new OpacityServiceCaller();
		}
		
		if(service.getServiceName().equalsIgnoreCase(PDRServiceName)){
			return new PDRServiceCaller();
		}
		return null;
	}
}
