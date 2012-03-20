package test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.ivoa.gui.IntelligentGUI;
import net.ivoa.parameter.model.Service;
import net.ivoa.pdl.interpreter.utilities.UserMapper;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class GUITest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Service service = buildService();

		// CRUCIAL!! Initialize the utilities
		Utilities.getInstance().setService(service);
		Utilities.getInstance().setMapper(new UserMapper());

		IntelligentGUI gui = new IntelligentGUI(service);
		gui.createAndShowGUI();
	}

	private static Service buildService() {
		Service service = null;
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance("net.ivoa.parameter.model");
			Unmarshaller u = jaxbContext.createUnmarshaller();
			service = (Service) u.unmarshal(new File("PDL-Description.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return service;
	}

}
