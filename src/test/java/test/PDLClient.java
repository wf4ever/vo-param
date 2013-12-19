package test;

import java.io.File;
import java.io.IOException;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.ivoa.gui.IntelligentGUI;
import net.ivoa.gui.UnifiedGUI;
import net.ivoa.parameter.model.Service;
import net.ivoa.pdl.interpreter.utilities.SkossConverter;
import net.ivoa.pdl.interpreter.utilities.UserMapper;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class PDLClient {

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		Service service = buildService();
		
		
		// take the menu bar off the jframe
		System.setProperty("apple.laf.useScreenMenuBar", "true");

		// set the name of the application menu item
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "PDL Client for ParisDurhamShock");

		// set the look and feel
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		
		// CRUCIAL!! Initialize the utilities
		Utilities.getInstance().setService(service);
		Utilities.getInstance().setMapper(new UserMapper());
		
		ToolTipManager.sharedInstance().setDismissDelay(60000);
		
		// try to initialize the map from the config file
		try {
			Utilities.getInstance().getValuesFromConfigFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		IntelligentGUI gui = new IntelligentGUI(service);
		
		//Initialise the description of parameters from their Skos concepts
		SkossConverter.getInstance();
		
		System.out.println(Thread.currentThread()+" is the current tread");
		
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
