package net.ivoa.pdl.interpreter.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.ivoa.parameter.model.SingleParameter;


public class SkossConverter {

	private static final SkossConverter instance = new SkossConverter();

	private SkossConverter() {
		super();
		initialiseSkossMap();
	}

	public static SkossConverter getInstance() {
		return instance;
	}
	
	private Map<String, String> urlSkossDescription;
	
	private void initialiseSkossMap(){
		urlSkossDescription = new HashMap<String, String>();
		List<SingleParameter> parameterList = Utilities.getInstance().getService().getParameters().getParameter();
		List<SkosCaller> callerList = new ArrayList<SkosCaller>();
		List<Thread> treadList = new ArrayList<Thread>();
		
		for(SingleParameter currentParam : parameterList){
			SkosCaller currentCaller = new SkosCaller(currentParam.getSkosConcept());
			callerList.add(currentCaller);
			treadList.add(new Thread(currentCaller));
	//		urlSkossDescription.put(currentParam.getSkossConcept(), getDescriptionBySkos(currentParam.getSkossConcept()));
		}
		for(int i = 0 ; i< parameterList.size(); i++){
			treadList.get(i).start();
		}
		for(int i = 0 ; i< parameterList.size(); i++){
			try {
				treadList.get(i).join();
				urlSkossDescription.put(parameterList.get(i).getSkosConcept(), callerList.get(i).getSkosDescription());
			} catch (InterruptedException e) {
				urlSkossDescription.put(parameterList.get(i).getSkosConcept(), parameterList.get(i).getSkosConcept());
				e.printStackTrace();
			}
		}
		
	}
	
	
	public String getSkosDescriptionBySkosURI(String uri){
		return urlSkossDescription.get(uri);
	}
	

}
