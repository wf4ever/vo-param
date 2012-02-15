package test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import net.ivoa.gui.AllGroupsPanel;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.SingleParameter;

public class GuiTest extends JFrame {

	public GuiTest(List<ParameterGroup> listGroupParam,
			List<SingleParameter> allParameter) {
		setTitle("IVOA GUI TEST");
		setSize(1200, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new AllGroupsPanel(listGroupParam, allParameter));
	}

	public static void main(String[] args) {
		List<SingleParameter> listeParams = new ArrayList<SingleParameter>();

		// Creating the mass parameter
		SingleParameter mass = new SingleParameter();
		mass.setName("mass");
		mass.setParameterType(ParameterType.INTEGER);
//		mass.setPrecision("0.0001");
		mass.setSkossConcept("SKOSS_MASS");
		mass.setUnit("kg");

		// Creating the time parameter
		SingleParameter time = new SingleParameter();
		time.setName("time");
		time.setParameterType(ParameterType.REAL);
//		time.setPrecision("0.0001");
		time.setSkossConcept("SKOSS_TIME");
		time.setUnit("s");

		// Creating the kinetic energy parameter
		SingleParameter Kenergy = new SingleParameter();
		Kenergy.setName("E");
		Kenergy.setParameterType(ParameterType.REAL);
//		Kenergy.setPrecision("0.0001");
		Kenergy.setSkossConcept("SKOSS_ENERGY");
		Kenergy.setUnit("J");

		// Creating the displacement parameter
		SingleParameter distance = new SingleParameter();
		distance.setName("distance");
		distance.setParameterType(ParameterType.REAL);
//		distance.setPrecision("0.0001");
		distance.setSkossConcept("SKOSS_LENGHT");
		distance.setUnit("m");

		// Creating the X component of speed
		SingleParameter speedX = new SingleParameter();
		speedX.setName("speedX");
		speedX.setParameterType(ParameterType.REAL);
//		speedX.setPrecision("0.0001");
		speedX.setSkossConcept("SKOSS_SPEED_COMPONENT");
		speedX.setUnit("m/s");

		// Creating the Y component of speed
		SingleParameter speedY = new SingleParameter();
		speedY.setName("speedY");
		speedY.setParameterType(ParameterType.REAL);
//		speedY.setPrecision("0.0001");
		speedY.setSkossConcept("SKOSS_SPEED_COMPONENT");
		speedY.setUnit("m/s");

		// Creating the Z component of speed
		SingleParameter speedZ = new SingleParameter();
		speedZ.setName("speedZ");
		speedZ.setParameterType(ParameterType.REAL);
	//	speedZ.setPrecision("0.0001");
		speedZ.setSkossConcept("SKOSS_SPEED_COMPONENT");
		speedZ.setUnit("m/s");

		listeParams.add(speedX);
		listeParams.add(speedY);
		listeParams.add(speedZ);
		listeParams.add(mass);
		listeParams.add(time);
		listeParams.add(Kenergy);

		ParameterGroup speed = new ParameterGroup();
		speed.setName("speed");
		speed.getParameterRef().add(mkRef(speedX));
		speed.getParameterRef().add(mkRef(speedY));
		speed.getParameterRef().add(mkRef(speedZ));

		ParameterGroup input = new ParameterGroup();
		input.setName("input");
		input.getParameterRef().add(mkRef(mass));
		input.getParameterRef().add(mkRef(time));
		input.getParameterGroup().add(speed);

		ParameterGroup output = new ParameterGroup();
		output.setName("output");
		output.getParameterRef().add(mkRef(Kenergy));
		output.getParameterRef().add(mkRef(distance));

		ParameterGroup all = new ParameterGroup();
		all.setName("allParameter");
		all.getParameterGroup().add(input);
		all.getParameterGroup().add(output);
		
		List<ParameterGroup> listeGroups = new ArrayList<ParameterGroup>();
		listeGroups.add(all);
		
		GuiTest test = new GuiTest(listeGroups, listeParams);
		test.setVisible(true);
	}

	private static ParameterReference mkRef(SingleParameter par) {
		ParameterReference ref = new ParameterReference();
		ref.setParameterName(par.getName());
		return ref;
	}
}
