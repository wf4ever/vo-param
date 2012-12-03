package net.ivoa.pdl.interpreter.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class SkosCaller implements Runnable{
	
	public SkosCaller(String skosConcept) {
		super();
		this.skosUrl = skosConcept;
	}

	private String skosUrl;
	@SuppressWarnings("unused")
	private String skosDesscription;
	
	public String getSkosDescription(){
		return this.skosDesscription;
	}
	
	public void run() {
		System.out.println(Thread.currentThread() +" retriving skos for parameter "+skosUrl);
		String serverResponse = "";
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
			serverResponse = requestResult.substring(begin, end);
			String [] temp = serverResponse.split(" ");
			serverResponse = "<html>";
			for(int i =0;i < temp.length; i++){
				serverResponse = serverResponse + " " + temp[i];
				if ((i+1)%4==0){
					serverResponse = serverResponse+ "<br>";
				}
			}
			serverResponse = serverResponse + "</html>";
			this.skosDesscription = serverResponse;
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("error retriving "+skosUrl);
			this.skosDesscription = skosUrl;
		}
		
	}

}
