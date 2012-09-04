package net.ivoa.gui.dynamicLabel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class SkossConverter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new SkossConverter().getDescriptionBySkos("http://purl.org/astronomy/vocab/Algorithms/CentralDifferenceScheme");

	}

	private void getDescriptionBySkos(String skosUrl) {
		try {

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
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
