package net.ivoa.pdl.servicecaller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import CommonsObjects.GeneralParameter;

import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class PDRServiceCaller implements IserviceCaller {

	public String callService() {
		String serviceUrl = "http://opacity-cs.obspm.fr:8080/pdr/PDR?";
		// List<SingleParameter> paramList =
		// Utilities.getInstance().getService()
		// .getParameters().getParameter();

		// int j=0;
		// for (int i = 0; i < paramList.size(); i++) {
		// SingleParameter p = paramList.get(i);

		// List<GeneralParameter> currentParam = Utilities.getInstance()
		// .getuserProvidedValuesForParameter(p);

		// if (null != currentParam) {
		// String character = "";
		// if (j > 0) {
		// character = "&";
		// }
		// j++;
		// serviceUrl = serviceUrl + character + p.getName() + "="
		// + currentParam.get(0).getValue();
		// }
		// }
		// System.out.println(serviceUrl);
		serviceUrl = "http://opacity-cs.obspm.fr:8080/pdr/PDR?email=cmzwolf@gmail.com&dens=100&EOSChoice=density&thermalBalance=isothermal&radiationFieldIntensity=1&radiationSource=Galaxy&cloudSize=0.5";
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
			return "error in job submission";
		}

	}

}
