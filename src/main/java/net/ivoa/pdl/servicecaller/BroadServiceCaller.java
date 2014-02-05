package net.ivoa.pdl.servicecaller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class BroadServiceCaller implements IserviceCaller {

	public String callService() {
		String serviceUrl = "http://opacity-cs.obspm.fr:8080/broadening/Broadening?";
		List<SingleParameter> paramList = Utilities.getInstance().getService()
				.getParameters().getParameter();
		for (int i = 0; i < paramList.size(); i++) {
			SingleParameter p = paramList.get(i);
			String character = "";
			if (i > 0) {
				character = "&";
			}
			serviceUrl = serviceUrl
					+ character
					+ p.getName()
					+ "="
					+ Utilities.getInstance()
							.getuserProvidedValuesForParameter(p).get(0)
							.getValue();
		}
		System.out.println(serviceUrl);

		try {

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new URL(serviceUrl).openConnection()
							.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			bufferedReader.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "error in submitting job!";
		}
		
	}

}
