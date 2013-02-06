package net.ivoa.pdl.servicecaller;

import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class DefaultServiceCaller implements IserviceCaller {

	public String callService() {
		String serviceUrl = Utilities.getInstance().getService().getServiceId()
				+ "?";

		List<SingleParameter> paramList = Utilities.getInstance().getService()
				.getParameters().getParameter();

		for (int i = 0; i < paramList.size(); i++) {
			try {

				SingleParameter p = paramList.get(i);
				String character = "";
				if (i > 0) {
					character = "&";
				}

				String paramName = p.getName();
				String paramValue = Utilities.getInstance()
						.getuserProvidedValuesForParameter(p).get(0).getValue();
				if (p.getParameterType().equals(ParameterType.STRING)) {
					paramValue = URLEncoder.encode(paramValue, "UTF-8");
				}

				serviceUrl = serviceUrl + character + paramName + "="
						+ paramValue;

			} catch (Exception e) {
				// TODO: do nothing
			}
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
			return "errors";
		}
	}
}
