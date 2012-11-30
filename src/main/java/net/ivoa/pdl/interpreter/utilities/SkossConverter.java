package net.ivoa.pdl.interpreter.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
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
		for(SingleParameter currentParam : parameterList){
			System.out.println("retriving skos for parameter "+currentParam.getName());
			urlSkossDescription.put(currentParam.getSkossConcept(), getDescriptionBySkos(currentParam.getSkossConcept()));
		}
	}
	
	
	public String getSkosDescriptionBySkosURI(String uri){
		return urlSkossDescription.get(uri);
	}
	

	private String getDescriptionBySkos(String skosUrl) {
		String toReturn = "";
		try {
			String requestResult;
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new URL(skosUrl).openConnection()
							.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			bufferedReader.close();
			requestResult = sb.toString();
			int begin = requestResult.indexOf("<skos:definition xml:lang=\"en\">")+31;
			int end = requestResult.indexOf("</skos:definition>");
			toReturn = requestResult.substring(begin, end);
			String [] temp = toReturn.split(" ");
			toReturn = "<html>";
			for(int i =0;i < temp.length; i++){
				toReturn = toReturn + " " + temp[i];
				if ((i+1)%4==0){
					toReturn = toReturn+ "<br>";
				}
			}
			toReturn = toReturn + "</html>";
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("error retriving "+skosUrl);
			toReturn = skosUrl;
		}
		return toReturn;
	}

}
