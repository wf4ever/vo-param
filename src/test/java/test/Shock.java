package test;

import static net.ivoa.pdl.interpreter.utilities.ConstantUtils.mktconst;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import net.ivoa.parameter.model.Always;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.BelongToSet;
import net.ivoa.parameter.model.ConstraintOnGroup;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.If;
import net.ivoa.parameter.model.IfThenConditionalStatement;
import net.ivoa.parameter.model.Operation;
import net.ivoa.parameter.model.OperationType;
import net.ivoa.parameter.model.ParameterDependency;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.Parameters;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.parameter.model.Then;
import net.ivoa.parameter.model.ValueInRange;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.parameter.model.ValueSmallerThan;

public class Shock extends BaseExample {

        public static void main(String[] args) throws PropertyException,
                        FileNotFoundException, JAXBException {
                Shock example = new Shock();
                example.marshall();
        }

        @Override
        protected Service buildService() {
                Service service = factory
                                .createService()
                                .withServiceId(
                                                "http://pdl-calc.obspm.fr:8081/ParisDurham/OnlineCode")
                                .withServiceName("Paris-Durham shock code");
                service.setDescription("Simulates the physical, dynamical, and chemical structure of a shocked layer of the ISM.");

                // Creating input and output parameter group
                ParameterGroup inputsPG = factory.createParameterGroup().withName(
                                "inputs");
                ParameterGroup outputsPG = factory.createParameterGroup().withName(
                                "outputs");

                // Adding parameters to the list of service params
                Parameters parameterList = factory.createParameters();

                ParameterGroup shockParameter = factory.createParameterGroup()
                                .withName("shockParameters");

                ParameterGroup numericalParameters = factory.createParameterGroup()
                                .withName("numericalParameters");

                SingleParameter mail = factory.createSingleParameter();
                mail.setDependency(ParameterDependency.REQUIRED);
                mail.setName("mail");
                mail.setParameterType(ParameterType.STRING);
                mail.setSkosConcept("mail of the user");
                mail.setUnit("no unit");
                mail.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference mailRef = mkRef(mail);
                parameterList.getParameter().add(mail);

                SingleParameter shockType = factory.createSingleParameter();
                shockType.setDependency(ParameterDependency.REQUIRED);
                shockType.setName("shockType");
                shockType.setParameterType(ParameterType.STRING);
                String shockTypeConcept = "";
                shockTypeConcept += "<html><table>";
                shockTypeConcept += "<tr><td>name:</td> <td> shockType </td></tr>";
                shockTypeConcept += "<tr><td>definition:</td> <td> this is the type of the shock. The Paris-Durham model allows for </tr>";
                shockTypeConcept += "<tr><td> </td> <td> stationary C- or J-type, or approximate non stationary CJ-type shock</td></tr>";
                shockTypeConcept += "<tr><td> </td> <td> models to run. Accordingly choose either 'C', 'J', or 'CJ' </td></tr>";
                shockTypeConcept += "<tr><td>type: </td> string</tr>";
                shockTypeConcept += "<tr><td>recommended value:</td> <td>none</td></tr>";
                shockTypeConcept += "<tr><td>unit:</td><td>none</td></tr>";
                shockTypeConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                shockTypeConcept += "<tr><td>restrictions:</td><td>must be 'C', 'J', or 'CJ'</td></tr>";
                shockTypeConcept += "</table></html>";
                shockType.setSkosConcept(shockTypeConcept);
                shockType.setUnit("no unit");
                shockType.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference shockTypeRef = mkRef(shockType);
                parameterList.getParameter().add(shockType);

                SingleParameter Nfluids = factory.createSingleParameter();
                Nfluids.setDependency(ParameterDependency.REQUIRED);
                Nfluids.setName("Nfluids");
                Nfluids.setParameterType(ParameterType.INTEGER);
                String nFluidsConcept = "";
                nFluidsConcept += "<html><table>";
                nFluidsConcept += "<tr><td>name:</td><td>Nfluids</td></tr>";
                nFluidsConcept += "<tr><td>definition:</td><td> the number of fluids you must/want to take into account. In a J-type</td></tr>";
                nFluidsConcept += "<tr><td> </td><td>shock model, where there is no magnetic field, no differential </td></tr>";
                nFluidsConcept += "<tr><td> </td><td>behaviour is expected between neutral and charged fluids: one fluid </td></tr>";
                nFluidsConcept += "<tr><td> </td><td>is sufficient. In a CJ- or C-type model, the charged particles do </td></tr>";
                nFluidsConcept += "<tr><td> </td><td>interact with the magnetic field, contrary to the neutral ones,</td></tr>";
                nFluidsConcept += "     <tr><td> </td><td>hence the need for three fluids (neutral, charged, electrons).</td></tr>";
                nFluidsConcept += "     <tr><td>type:</td><td> integer</td></tr>";
                nFluidsConcept += "     <tr><td>recommended value:</td><td>'3' for C- and CJ-type models, '1' for J-type ones.</td></tr>";
                nFluidsConcept += "     <tr><td>unit:</td><td>none</td></tr>";
                nFluidsConcept += "     <tr><td>conditions:</td><td>yes</td></tr>";
                nFluidsConcept += "     <tr><td>restriction:</td><td> must be equal to '1' or '3'</td></tr>";
                nFluidsConcept += "     </table></html>";
                Nfluids.setSkosConcept(nFluidsConcept);
                Nfluids.setUnit("no unit");
                Nfluids.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference NfluidsRef = mkRef(Nfluids);
                parameterList.getParameter().add(Nfluids);

                SingleParameter Bbeta = factory.createSingleParameter();
                Bbeta.setDependency(ParameterDependency.REQUIRED);
                Bbeta.setName("Bbeta");
                Bbeta.setParameterType(ParameterType.REAL);
                String bBetaConcept = "";
                bBetaConcept += "<html><table>";
                bBetaConcept += "<tr><td>name:</td><td>Bbeta</td></tr>";
                bBetaConcept += "<tr><td>definition:</td><td>the magnetic field strength parameter. Note that this parameters</td></tr>";
                bBetaConcept += "<tr><td></td><td>sets the value of the component transverse to the direction of the</td> </tr>";
                bBetaConcept += "<tr><td></td><td>shock. This values varies as: </td></tr>";
                bBetaConcept += "<tr><td></td><td>Btransverse (microG) =  Bbeta * sqrt (nH (cm^-3)),</td></tr>";
                bBetaConcept += "<tr><td></td><td>where nH is the pre-shock density (see below).</td></tr>";
                bBetaConcept += "<tr><td>type:</td><td>real</td></tr>";
                bBetaConcept += "<tr><td>ecommended value:</td><td> '1' is a standard option (Crutcher, R.M., ApJ, 1999, 520, 706).</td></tr>";
                bBetaConcept += "<tr><td>unit:</td><td>microG/cm^(3/2)</td></tr>";
                bBetaConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                bBetaConcept += "<tr><td>restriction:</td><td>must be positive or null</td></tr>";
                bBetaConcept += "</table></html>";
                Bbeta.setSkosConcept(bBetaConcept);
                Bbeta.setUnit("micro Gauss cm^(3/2)");
                Bbeta.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference BbetaRef = mkRef(Bbeta);
                parameterList.getParameter().add(Bbeta);

                SingleParameter Vs = factory.createSingleParameter();
                Vs.setDependency(ParameterDependency.REQUIRED);
                Vs.setName("Vs");
                Vs.setParameterType(ParameterType.REAL);
                String shockVelocityConcept = "";
                shockVelocityConcept += "<html><table>";
                shockVelocityConcept += "<tr><td>name:</td><td>vs</td></tr>";
                shockVelocityConcept += "<tr><td>definition:</td><td>the shock velocity. Note that this value can not be chosen freely. </td></tr>";
                shockVelocityConcept += "<tr><td></td><td>In case of C-shock models, it can not exceed a 'critical' velocity </td></tr>";
                shockVelocityConcept += "<tr><td></td><td>that depends on the choice of other parameters such as the pre-shock </td></tr>";
                shockVelocityConcept += "<tr><td></td><td>density and magnetic field (and many others, see Flower & Pineau des </td></tr>";
                shockVelocityConcept += "<tr><td></td><td>Forêts, MNRAS, 2003, 343, 390, and Le bourlot et al., MNRAS, 2002, </td></tr>";
                shockVelocityConcept += "<tr><td></td><td>332, 985). For J-type shock models, the validity of the physics </td></tr>";
                shockVelocityConcept += "<tr><td></td><td>included in our models might be unsufficient for shock velocities </td></tr>";
                shockVelocityConcept += "<tr><td></td><td>above ~50 km/s.</td></tr>";
                shockVelocityConcept += "<tr><td>type:</td><td>real</td></tr>";
                shockVelocityConcept += "<tr><td>recommended value:</td><td>'25' is a safe option for standard inputs (for instance </td></tr>";
                shockVelocityConcept += "<tr><td></td><td>nH = 1e4 cm^-3, Bbeta = 1).</td></tr>";
                shockVelocityConcept += "<tr><td>unit:</td><td>km/s</td></tr>";
                shockVelocityConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                shockVelocityConcept += "<tr><td>restriction:</td><td>must be strictly positive</td></tr>";
                shockVelocityConcept += "</table></html>";
                Vs.setSkosConcept(shockVelocityConcept);
                Vs.setUnit("km/s");
                Vs.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference vsRef = mkRef(Vs);
                parameterList.getParameter().add(Vs);

                SingleParameter Vdi = factory.createSingleParameter();
                Vdi.setDependency(ParameterDependency.REQUIRED);
                Vdi.setName("Vdi");
                Vdi.setParameterType(ParameterType.REAL);
                String vdiConcept = "";
                vdiConcept += "<html><table>";
                vdiConcept += "<tr><td>name:</td><td>vdi</td></tr>";
                vdiConcept += "<tr><td>definition:</td><td>the initial drift velocity between ions and neutrals.</td></tr>";
                vdiConcept += "<tr><td>type:</td><td>real</td></tr>";
                vdiConcept += "<tr><td>recommended value:</td><td>'1e3'</td></tr>";
                vdiConcept += "<tr><td>unit:</td><td>cm/s</td></tr>";
                vdiConcept += "<tr><td>conditions:</td><td>no</td></tr>";
                vdiConcept += "<tr><td>restriction:</td><td>must be positive or null</td></tr>";
                vdiConcept += "</table></html>";
                Vdi.setSkosConcept(vdiConcept);
                Vdi.setUnit("cm/s");
                Vdi.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference VdiRef = mkRef(Vdi);
                parameterList.getParameter().add(Vdi);

                SingleParameter OpH2 = factory.createSingleParameter();
                OpH2.setDependency(ParameterDependency.REQUIRED);
                OpH2.setName("OpH2");
                OpH2.setParameterType(ParameterType.REAL);
                String opH2Concept = "";
                opH2Concept += "<html><table>";
                opH2Concept += "<tr><td>name:</td><td>opH2</td></tr>";
                opH2Concept += "<tr><td>definition:</td><td>the initial ortho-to-para ratio value for H2.</td></tr>";
                opH2Concept += "<tr><td>type:</td><td>real</td></tr>";
                opH2Concept += "<tr><td>recommended value:</td><td>'3', the LTE value.</td></tr>";
                opH2Concept += "<tr><td>unit:</td><td>none</td></tr>";
                opH2Concept += "<tr><td>conditions:</td><td>no</td></tr>";
                opH2Concept += "<tr><td>restriction:</td><td>must be positive or null</td></tr>";
                opH2Concept += "</table></html>";
                OpH2.setSkosConcept(opH2Concept);
                OpH2.setUnit("No unit");
                OpH2.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference OpH2Ref = mkRef(OpH2);
                parameterList.getParameter().add(OpH2);

                SingleParameter Ti = factory.createSingleParameter();
                Ti.setDependency(ParameterDependency.REQUIRED);
                Ti.setName("Ti");
                Ti.setParameterType(ParameterType.REAL);
                String tiConcept = "";
                tiConcept += "<html><table>";
                tiConcept += "<tr><td>name:</td><td>Ti</td></tr>";
                tiConcept += "<tr><td>definition:</td><td>the initial gas temperature. This is regardless of the considered </td></tr>";
                tiConcept += "<tr><td></td><td>number of fluids, as in the pre-shock region, the ions and neutral </td></tr>";
                tiConcept += "<tr><td></td><td>particles are not yet decoupled.</td></tr>";
                tiConcept += "<tr><td>type:</td><td>real</td></tr>";
                tiConcept += "<tr><td>recommended value:</td><td>'10'</td></tr>";
                tiConcept += "<tr><td>unit:</td><td>K</td></tr>";
                tiConcept += "<tr><td>conditions:</td><td>no</td></tr>";
                tiConcept += "<tr><td>restriction:</td><td>must be strictly positive</td></tr>";
                tiConcept += "</table></html>";
                Ti.setSkosConcept(tiConcept);
                Ti.setUnit("K");
                Ti.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference TiRef = mkRef(Ti);
                parameterList.getParameter().add(Ti);

                SingleParameter nHi = factory.createSingleParameter();
                nHi.setDependency(ParameterDependency.REQUIRED);
                nHi.setName("nHi");
                nHi.setParameterType(ParameterType.REAL);
                String nHiConcept = "";
                nHiConcept += "<html><table>";
                nHiConcept += "<tr><td>name:</td><td>nHi</td></tr>";
                nHiConcept += "<tr><td>definition:</td><td>the pre-shock density, i.e. the initial value for </td></tr>";
                nHiConcept += "<tr><td></td><td>n(H) + 2.0 n(H2) + n(H+).</td></tr>";
                nHiConcept += "<tr><td>type:</td><td>real</td></tr>";
                nHiConcept += "<tr><td>recommended value:</td><td>'1e4'</td></tr>";
                nHiConcept += "<tr><td>unit:</td><td>cm^-3</td></tr>";
                nHiConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                nHiConcept += "<tr><td>restriction:</td><td>must be strictly positive</td></tr>";
                nHiConcept += "</table></html>";
                nHi.setSkosConcept(nHiConcept);
                nHi.setUnit("cm^(-3)");
                nHi.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference nHiRef = mkRef(nHi);
                parameterList.getParameter().add(nHi);

                SingleParameter Tg = factory.createSingleParameter();
                Tg.setDependency(ParameterDependency.REQUIRED);
                Tg.setName("Tg");
                Tg.setParameterType(ParameterType.REAL);
                String tgConcept = "";
                tgConcept += "<html><table>";
                tgConcept += "<tr><td>name:</td><td>Tg</td></tr>";
                tgConcept += "<tr><td>definition:</td><td>the grain temperature. No thermal balance is done in the current </td></tr>";
                tgConcept += "<tr><td></td><td>version of the shock model for the grains, so their temperature </td></tr>";
                tgConcept += "<tr><td></td><td>remains at this value through the whole shocked layer. </td></tr>";
                tgConcept += "<tr><td>type:</td><td>real</td></tr>";
                tgConcept += "<tr><td>recommended value:</td><td>'15'</td></tr>";
                tgConcept += "<tr><td>unit:</td><td>K</td></tr>";
                tgConcept += "<tr><td>conditions:</td><td>no</td></tr>";
                tgConcept += "<tr><td>restriction:</td><td>must be strictly positive</td></tr>";
                tgConcept += "</table></html>";
                Tg.setSkosConcept(tgConcept);
                Tg.setUnit("K");
                Tg.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference TgRef = mkRef(Tg);
                parameterList.getParameter().add(Tg);

                SingleParameter Zeta = factory.createSingleParameter();
                Zeta.setDependency(ParameterDependency.REQUIRED);
                Zeta.setName("Zeta");
                Zeta.setParameterType(ParameterType.REAL);
                String zetaConcept = "";
                zetaConcept += "<html><table>";
                zetaConcept += "<tr><td>name:</td><td>Zeta</td></tr>";
                zetaConcept += "<tr><td>definition:</td><td>the ambient/environmental cosmic ray ionization rate. </td></tr>";
                zetaConcept += "<tr><td>type:</td><td>real</td></tr>";
                zetaConcept += "<tr><td>recommended value:</td><td>'5e-17'</td></tr>";
                zetaConcept += "<tr><td>unit:</td><td>s^-1</td></tr>";
                zetaConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                zetaConcept += "<tr><td>restriction:</td><td>must be strictly positive</td></tr>";
                zetaConcept += "</table></html>";
                Zeta.setSkosConcept(zetaConcept);
                Zeta.setUnit("s^(-1)");
                Zeta.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference ZetaRef = mkRef(Zeta);
                parameterList.getParameter().add(Zeta);

                SingleParameter NstepMax = factory.createSingleParameter();
                NstepMax.setDependency(ParameterDependency.REQUIRED);
                NstepMax.setName("NstepMax");
                NstepMax.setParameterType(ParameterType.INTEGER);
                String nStepMaxConcept = "";
                nStepMaxConcept += "<html><table>";
                nStepMaxConcept += "<tr><td>name:</td><td>NstepMax</td></tr>";
                nStepMaxConcept += "<tr><td>definition:</td><td>the maximum number of integration steps.</td></tr>";
                nStepMaxConcept += "<tr><td>type:</td><td>integer</td></tr>";
                nStepMaxConcept += "<tr><td>recommended value:</td><td>'10000' for a precise model, '1000' for a more rudimentary one.</td></tr>";
                nStepMaxConcept += "<tr><td>unit:</td><td>no unit</td></tr>";
                nStepMaxConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                nStepMaxConcept += "<tr><td>restriction:</td><td>must be strictly positive</td></tr>";
                nStepMaxConcept += "</table></html>";
                NstepMax.setSkosConcept(nStepMaxConcept);
                NstepMax.setUnit("No unit");
                NstepMax.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference NstepMaxRef = mkRef(NstepMax);
                parameterList.getParameter().add(NstepMax);

                SingleParameter NstepW = factory.createSingleParameter();
                NstepW.setDependency(ParameterDependency.REQUIRED);
                NstepW.setName("NstepW");
                NstepW.setParameterType(ParameterType.INTEGER);
                String nstepWConcept = "";
                nstepWConcept += "<html><table>";
                nstepWConcept += "<tr><td>name:</td><td>NstepW</td></tr>";
                nstepWConcept += "<tr><td>definition:</td><td>the number of steps between two outputs.</td></tr>";
                nstepWConcept += "<tr><td>type:</td><td>integer</td></tr>";
                nstepWConcept += "<tr><td>recommended value:</td><td>'5'</td></tr>";
                nstepWConcept += "<tr><td>unit:</td><td>no unit</td></tr>";
                nstepWConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                nstepWConcept += "<tr><td>restriction:</td><td>must be strictly positive</td></tr>";
                nstepWConcept += "</table></html>";
                NstepW.setSkosConcept(nstepWConcept);
                NstepW.setUnit("No unit");
                NstepW.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference NstepWRef = mkRef(NstepW);
                parameterList.getParameter().add(NstepW);

                SingleParameter NH2Lev = factory.createSingleParameter();
                NH2Lev.setDependency(ParameterDependency.REQUIRED);
                NH2Lev.setName("NH2Lev");
                NH2Lev.setParameterType(ParameterType.INTEGER);
                String nH2LevConcept = "";
                nH2LevConcept += "<html><table>";
                nH2LevConcept += "<tr><td>name:</td><td>NH2Lev</td></tr>";
                nH2LevConcept += "<tr><td>definition:</td><td>the number of H2 levels included.</td></tr>";
                nH2LevConcept += "<tr><td>type:</td><td>integer</td></tr>";
                nH2LevConcept += "<tr><td>recommended value:</td><td>'150'</td></tr>";
                nH2LevConcept += "<tr><td>unit:</td><td>no unit</td></tr>";
                nH2LevConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                nH2LevConcept += "<tr><td>restriction:</td><td>must be strictly positive</td></tr>";
                nH2LevConcept += "</table></html>";
                NH2Lev.setSkosConcept(nH2LevConcept);
                NH2Lev.setUnit("No unit");
                NH2Lev.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference NH2LevRef = mkRef(NH2Lev);
                parameterList.getParameter().add(NH2Lev);

                SingleParameter NH2Lines = factory.createSingleParameter();
                NH2Lines.setDependency(ParameterDependency.REQUIRED);
                NH2Lines.setName("NH2Lines");
                NH2Lines.setParameterType(ParameterType.INTEGER);
                String nH2LinesConcept = "";
                nH2LinesConcept += "<html><table>";
                nH2LinesConcept += "<tr><td>name:</td><td>NH2Lines</td></tr>";
                nH2LinesConcept += "<tr><td>definition:</td><td>the number of H2 lines displayed in the H2-related output file.</td></tr>";
                nH2LinesConcept += "<tr><td>type:</td><td>integer</td></tr>";
                nH2LinesConcept += "<tr><td>recommended value:</td><td>'200'</td></tr>";
                nH2LinesConcept += "<tr><td>unit:</td><td>no unit</td></tr>";
                nH2LinesConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                nH2LinesConcept += "<tr><td>restriction:</td><td>must be strictly positive</td></tr>";
                nH2LinesConcept += "</table></html>";
                NH2Lines.setSkosConcept(nH2LinesConcept);
                NH2Lines.setUnit("No unit");
                NH2Lines.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference NH2LinesRef = mkRef(NH2Lines);
                parameterList.getParameter().add(NH2Lines);

                SingleParameter iforH2 = factory.createSingleParameter();
                iforH2.setDependency(ParameterDependency.REQUIRED);
                iforH2.setName("iforH2");
                iforH2.setParameterType(ParameterType.INTEGER);
                String iforH2Concept = "";
                iforH2Concept += "<html><table>";
                iforH2Concept += "<tr><td>name:</td><td>iforH2</td></tr>";
                iforH2Concept += "<tr><td>definition:</td><td>a flag parameter for the model of H2 formation on grains. Its value </td></tr>";
                iforH2Concept += "<tr><td></td><td>can be 0, 1, 2, 3, or 4, corresponding to:</td></tr>";
                iforH2Concept += "<tr><td></td><td>0: 1/3 of 4.4781 eV in internal energy (=> 17249 K) (Allen, 1999)</td></tr>";
                iforH2Concept += "<tr><td></td><td>1: Proportional to Boltzman Distrib at 17249 K</td></tr>";
                iforH2Concept += "<tr><td></td><td>2: Dissociation limit : v = 14, J = 0,1 (4.4781 eV)</td></tr>";
                iforH2Concept += "<tr><td></td><td>3: v = 6, J = 0,1</td></tr>";
                iforH2Concept += "<tr><td></td><td>4: fraction = relative populations at t, initialised as </td></tr>";
                iforH2Concept += "<tr><td></td><td>H2_lev%density, and changed during integration</td></tr>";
                iforH2Concept += "<tr><td>type:</td><td>integer</td></tr>";
                iforH2Concept += "<tr><td>recommended value:</td><td>'1'</td></tr>";
                iforH2Concept += "<tr><td>unit:</td><td>no unit</td></tr>";
                iforH2Concept += "<tr><td>conditions:</td><td>no</td></tr>";
                iforH2Concept += "<tr><td>restriction:</td><td>must be equal to '0', '1', '2', '3', or '4'</td></tr>";
                iforH2Concept += "</table></html>";
                iforH2.setSkosConcept(iforH2Concept);
                iforH2.setUnit("No unit");
                iforH2.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference iforH2Ref = mkRef(iforH2);
                parameterList.getParameter().add(iforH2);

                SingleParameter ikinH2 = factory.createSingleParameter();
                ikinH2.setDependency(ParameterDependency.REQUIRED);
                ikinH2.setName("ikinH2");
                ikinH2.setParameterType(ParameterType.INTEGER);
                String ikinH2Concept = "";
                ikinH2Concept += "<html><table>";
                ikinH2Concept += "<tr><td>name:</td><td>ikinH2</td></tr>";
                ikinH2Concept += "<tr><td>definition:</td><td>a flag parameter for the kinetic energy of the newly formed H2. Its </td></tr>";
                ikinH2Concept += "<tr><td></td><td>value can be 1 or 2, corresponding to:</td></tr>";
                ikinH2Concept += "<tr><td></td><td>1: 0.5 * (4.4781 - internal)</td></tr>";
                ikinH2Concept += "<tr><td></td><td>2: Inf(1.4927 eV, 4.4781 - internal) </td></tr>";
                ikinH2Concept += "<tr><td>type:</td><td>integer</td></tr>";
                ikinH2Concept += "<tr><td>recommended value:</td><td>'1'</td></tr>";
                ikinH2Concept += "<tr><td>unit:</td><td>no unit</td></tr>";
                ikinH2Concept += "<tr><td>conditions:</td><td>no</td></tr>";
                ikinH2Concept += "<tr><td>restriction:</td><td>must be equal to '1' or '2'</td></tr>";
                ikinH2Concept += "</table></html>";
                ikinH2.setSkosConcept(ikinH2Concept);
                ikinH2.setUnit("No unit");
                ikinH2.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference ikinH2Ref = mkRef(ikinH2);
                parameterList.getParameter().add(ikinH2);

                SingleParameter xll = factory.createSingleParameter();
                xll.setDependency(ParameterDependency.REQUIRED);
                xll.setName("xll");
                xll.setParameterType(ParameterType.REAL);
                String xllConcept = "";
                xllConcept += "<html><table>";
                xllConcept += "<tr><td>name:</td><td>xll</td></tr>";
                xllConcept += "<tr><td>definition:</td><td>the characteristic viscous length.</td></tr>";
                xllConcept += "<tr><td>type:</td><td>real</td></tr>";
                xllConcept += "<tr><td>recommended value:</td><td>'1e9'</td></tr>";
                xllConcept += "<tr><td>unit:</td><td>cm</td></tr>";
                xllConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                xllConcept += "<tr><td>restriction:</td><td>must be strictly positive</td></tr>";
                xllConcept += "</table></html>";
                xll.setSkosConcept(xllConcept);
                xll.setUnit("cm");
                xll.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference xllRef = mkRef(xll);
                parameterList.getParameter().add(xll);

                SingleParameter epsV = factory.createSingleParameter();
                epsV.setDependency(ParameterDependency.REQUIRED);
                epsV.setName("epsV");
                epsV.setParameterType(ParameterType.REAL);
                String epsVConcept = "";
                epsVConcept += "<html><table>";
                epsVConcept += "<tr><td>name:</td><td>epsV</td></tr>";
                epsVConcept += "<tr><td>definition:</td><td>a parameter for the precision of the computation.</td></tr>";
                epsVConcept += "<tr><td>type:</td><td>real</td></tr>";
                epsVConcept += "<tr><td>recommended value:</td><td>'1e-8'</td></tr>";
                epsVConcept += "<tr><td>unit:</td><td>no unit</td></tr>";
                epsVConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                epsVConcept += "<tr><td>restriction:</td><td>must be strictly positive</td></tr>";
                epsVConcept += "</table></html>";
                epsV.setSkosConcept(epsVConcept);
                epsV.setUnit("No unit");
                epsV.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference epsVRef = mkRef(epsV);
                parameterList.getParameter().add(epsV);

                SingleParameter TimeJ = factory.createSingleParameter();
                TimeJ.setDependency(ParameterDependency.REQUIRED);
                TimeJ.setName("TimeJ");
                TimeJ.setParameterType(ParameterType.REAL);
                String timeJConcept = "";
                timeJConcept += "<html><table>";
                timeJConcept += "<tr><td>name:</td><td>TimeJ</td></tr>";
                timeJConcept += "<tr><td>definition:</td><td>a parameter that determines the age of the shock. For a C- or J-type </td></tr>";
                timeJConcept += "<tr><td></td><td>shock model, this parameter must be larger than the MaxTimeN one, </td></tr>";
                timeJConcept += "<tr><td></td><td>contrary to the non-stationary, CJ-type.</td></tr>";
                timeJConcept += "<tr><td>type:</td><td>real</td></tr>";
                timeJConcept += "<tr><td>recommended value:</td><td>see above</td></tr>";
                timeJConcept += "<tr><td>unit:</td><td>year</td></tr>";
                timeJConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                timeJConcept += "<tr><td>restriction:</td><td>must be strictly positive; see above</td></tr>";
                timeJConcept += "</table></html>";
                TimeJ.setSkosConcept(timeJConcept);
                TimeJ.setUnit("yr");
                mail.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference TimeJRef = mkRef(TimeJ);
                parameterList.getParameter().add(TimeJ);

                SingleParameter MaxTimeN = factory.createSingleParameter();
                MaxTimeN.setDependency(ParameterDependency.REQUIRED);
                MaxTimeN.setName("MaxTimeN");
                MaxTimeN.setParameterType(ParameterType.REAL);
                String maxTimeNConcept = "";
                maxTimeNConcept += "<html><table>";
                maxTimeNConcept += "<tr><td>name:</td><td>MaxTimeN</td></tr>";
                maxTimeNConcept += "<tr><td>definition:</td><td>the maximum shock duration.</td></tr>";
                maxTimeNConcept += "<tr><td>type:</td><td>real</td></tr>";
                maxTimeNConcept += "<tr><td>recommended value:</td><td>none</td></tr>";
                maxTimeNConcept += "<tr><td>unit:</td><td>year</td></tr>";
                maxTimeNConcept += "<tr><td>conditions:</td><td>yes</td></tr>";
                maxTimeNConcept += "<tr><td>restriction:</td><td>must be strictly positive; see above</td></tr>";
                maxTimeNConcept += "</table></html>";
                MaxTimeN.setSkosConcept(maxTimeNConcept);
                MaxTimeN.setUnit("yr");
                MaxTimeN.setDimension(mktconst("1", ParameterType.INTEGER));
                ParameterReference MaxTimeNRef = mkRef(MaxTimeN);
                parameterList.getParameter().add(MaxTimeN);

                // Adding the parameter list to the service
                service.setParameters(parameterList);

                // defining constraints for parameters

                // constraint on shock type
                AtomicParameterExpression shockTypeExpr = factory
                                .createAtomicParameterExpression().withParameterRef(
                                                shockTypeRef);

                Criterion shockTypeSet = new Criterion()
                                .withExpression(shockTypeExpr)
                                .withConditionType(
                                                new BelongToSet()
                                                                .withValue(mktconst("C", ParameterType.STRING))
                                                                .withValue(mktconst("J", ParameterType.STRING))
                                                                .withValue(mktconst("S", ParameterType.STRING)));

                AlwaysConditionalStatement shockTpeAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(shockTypeSet))
                                .withComment("Shock type must be C, J or S");

                // constraint on Bbeta
                AtomicParameterExpression BbetaExpr = factory
                                .createAtomicParameterExpression().withParameterRef(BbetaRef);

                // constraint on the NH2Lev
                AtomicParameterExpression NH2LevExpr = factory
                                .createAtomicParameterExpression().withParameterRef(NH2LevRef);

                Criterion BBetaLimits = new Criterion().withExpression(BbetaExpr)
                                .withConditionType(
                                                new ValueLargerThan().withReached(true).withValue(
                                                                mktconst("0", ParameterType.REAL)));
               
                AlwaysConditionalStatement BbetaLimitsAlways = new AlwaysConditionalStatement()
                .withAlways(new Always().withCriterion(BBetaLimits))
                .withComment(
                                "Bbeta must be always positive or null");
               
                Criterion NH2LevRange = new Criterion()
                                .withExpression(NH2LevExpr)
                                .withConditionType(
                                                new ValueInRange()
                                                                .withSup(
                                                                                new ValueSmallerThan()
                                                                                                .withReached(true)
                                                                                                .withValue(
                                                                                                                mktconst(
                                                                                                                                "160",
                                                                                                                                ParameterType.INTEGER)))
                                                                .withInf(
                                                                                new ValueLargerThan()
                                                                                                .withReached(true)
                                                                                                .withValue(
                                                                                                                mktconst(
                                                                                                                                "0",
                                                                                                                                ParameterType.INTEGER))));

                AlwaysConditionalStatement NH2LevRangeAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(NH2LevRange))
                                .withComment(
                                                "NH2Lev value must be between 0 and 160 (both limits could be reached)");

                // constraint on Vs
                AtomicParameterExpression vsExpr = factory
                                .createAtomicParameterExpression().withParameterRef(vsRef);

                Criterion vsPos = new Criterion().withExpression(vsExpr)
                                .withConditionType(
                                                new ValueLargerThan().withReached(false).withValue(
                                                                mktconst("0", ParameterType.REAL)));

                AlwaysConditionalStatement vsPosAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(vsPos)).withComment(
                                                "vs must be strictly positive");

                // constraint on vdi
                AtomicParameterExpression vdiExpr = factory
                                .createAtomicParameterExpression().withParameterRef(VdiRef);

                Criterion vdiPos = new Criterion().withExpression(vdiExpr)
                                .withConditionType(
                                                new ValueLargerThan().withReached(true).withValue(
                                                                mktconst("0", ParameterType.REAL)));

                AlwaysConditionalStatement vdiPosAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(vdiPos)).withComment(
                                                "vdi must be positive (not stricltly)");

                // constraint on OpH2
                AtomicParameterExpression OpH2Expr = factory
                                .createAtomicParameterExpression().withParameterRef(OpH2Ref);

                Criterion OpH2Pos = new Criterion().withExpression(OpH2Expr)
                                .withConditionType(
                                                new ValueLargerThan().withReached(true).withValue(
                                                                mktconst("0", ParameterType.REAL)));

                AlwaysConditionalStatement OpH2PosAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(OpH2Pos)).withComment(
                                                "OpH2 must be positive (not stricltly)");

                // constraint on Ti
                AtomicParameterExpression TiExpr = factory
                                .createAtomicParameterExpression().withParameterRef(TiRef);

                Criterion TiPos = new Criterion().withExpression(TiExpr)
                                .withConditionType(
                                                new ValueLargerThan().withReached(true).withValue(
                                                                mktconst("0", ParameterType.REAL)));

                AlwaysConditionalStatement TiPosAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(TiPos)).withComment(
                                                "Ti must be positive (not stricltly)");

                // constraint on nHi
                AtomicParameterExpression nHiExpr = factory
                                .createAtomicParameterExpression().withParameterRef(nHiRef);

                Criterion nHiPos = new Criterion().withExpression(nHiExpr)
                                .withConditionType(
                                                new ValueLargerThan().withReached(false).withValue(
                                                                mktconst("0", ParameterType.REAL)));

                AlwaysConditionalStatement nHiPosAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(nHiPos)).withComment(
                                                "nHi must be strictly positive");

                // constraint on Tg
                AtomicParameterExpression TgExpr = factory
                                .createAtomicParameterExpression().withParameterRef(TgRef);

                Criterion TgPos = new Criterion().withExpression(TgExpr)
                                .withConditionType(
                                                new ValueLargerThan().withReached(true).withValue(
                                                                mktconst("0", ParameterType.REAL)));

                AlwaysConditionalStatement TgPosAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(TgPos)).withComment(
                                                "Tg must be positive (not strictly)");

                // constraint on the fluids number
                AtomicParameterExpression NfluidExpr = factory
                                .createAtomicParameterExpression().withParameterRef(NfluidsRef);

                Criterion NfluidSet = new Criterion()
                                .withExpression(NfluidExpr)
                                .withConditionType(
                                                new BelongToSet()
                                                                .withValue(mktconst("1", ParameterType.INTEGER))
                                                                .withValue(mktconst("2", ParameterType.INTEGER))
                                                                .withValue(mktconst("3", ParameterType.INTEGER)));

                AlwaysConditionalStatement NfluidAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(NfluidSet)).withComment(
                                                "Number of fluid must be equal to 1, 2 or 3");

                // constraint on Zeta
                AtomicParameterExpression ZetaExpr = factory
                                .createAtomicParameterExpression().withParameterRef(ZetaRef);

                Criterion ZetaRange = new Criterion()
                                .withExpression(ZetaExpr)
                                .withConditionType(
                                                new ValueInRange()
                                                                .withSup(
                                                                                new ValueSmallerThan()
                                                                                                .withReached(true)
                                                                                                .withValue(
                                                                                                                mktconst(
                                                                                                                                "1e-14",
                                                                                                                                ParameterType.REAL)))
                                                                .withInf(
                                                                                new ValueLargerThan()
                                                                                                .withReached(true)
                                                                                                .withValue(
                                                                                                                mktconst(
                                                                                                                                "0",
                                                                                                                                ParameterType.REAL))));

                AlwaysConditionalStatement ZetaRangeAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(ZetaRange))
                                .withComment(
                                                "Zeta value must be between 0 and 1e-14 (both limits could be reached)");

                // constraint on NstepMax
                AtomicParameterExpression NStepMaxExpr = factory
                                .createAtomicParameterExpression()
                                .withParameterRef(NstepMaxRef);

                Criterion NStepMaxRange = new Criterion()
                                .withExpression(NStepMaxExpr)
                                .withConditionType(
                                                new ValueInRange()
                                                                .withSup(
                                                                                new ValueSmallerThan().withReached(
                                                                                                false).withValue(
                                                                                                mktconst("1e6",
                                                                                                                ParameterType.REAL)))
                                                                .withInf(
                                                                                new ValueLargerThan()
                                                                                                .withReached(false)
                                                                                                .withValue(
                                                                                                                mktconst(
                                                                                                                                "0",
                                                                                                                                ParameterType.REAL))));

                AlwaysConditionalStatement NStepMaxRangeAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(NStepMaxRange))
                                .withComment(
                                                "NStepMax value must be between 0 and 1e6 (both limits could not be reached)");

                // constraint on NStepW
                AtomicParameterExpression NStepWExpr = factory
                                .createAtomicParameterExpression().withParameterRef(NstepWRef);

                Criterion NStepWRange = new Criterion()
                                .withExpression(NStepWExpr)
                                .withConditionType(
                                                new ValueInRange()
                                                                .withSup(
                                                                                new ValueSmallerThan().withReached(
                                                                                                false).withValue(
                                                                                                mktconst("1e4",
                                                                                                                ParameterType.REAL)))
                                                                .withInf(
                                                                                new ValueLargerThan()
                                                                                                .withReached(false)
                                                                                                .withValue(
                                                                                                                mktconst(
                                                                                                                                "0",
                                                                                                                                ParameterType.REAL))));

                AlwaysConditionalStatement NStepWRangeAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(NStepWRange))
                                .withComment(
                                                "NStepW value must be between 0 and 1e4 (both limits could not be reached)");

                // constraint on NH2Lines
                AtomicParameterExpression NH2LinesExpr = factory
                                .createAtomicParameterExpression()
                                .withParameterRef(NH2LinesRef);

                Criterion NH2LinesRange = new Criterion()
                                .withExpression(NH2LinesExpr)
                                .withConditionType(
                                                new ValueInRange()
                                                                .withSup(
                                                                                new ValueSmallerThan()
                                                                                                .withReached(true)
                                                                                                .withValue(
                                                                                                                mktconst(
                                                                                                                                "500",
                                                                                                                                ParameterType.INTEGER)))
                                                                .withInf(
                                                                                new ValueLargerThan()
                                                                                                .withReached(false)
                                                                                                .withValue(
                                                                                                                mktconst(
                                                                                                                                "0",
                                                                                                                                ParameterType.INTEGER))));

                AlwaysConditionalStatement NH2LinesRangeAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(NH2LinesRange))
                                .withComment("NH2Lines value must be >0 and <= 500");

                // constraint on iforH2
                AtomicParameterExpression iforH2Expr = factory
                                .createAtomicParameterExpression().withParameterRef(iforH2Ref);

                Criterion iforH2Set = new Criterion()
                                .withExpression(iforH2Expr)
                                .withConditionType(
                                                new BelongToSet()
                                                                .withValue(mktconst("1", ParameterType.INTEGER))
                                                                .withValue(mktconst("2", ParameterType.INTEGER))
                                                                .withValue(mktconst("3", ParameterType.INTEGER))
                                                                .withValue(mktconst("4", ParameterType.INTEGER)));

                AlwaysConditionalStatement iforH2SetAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(iforH2Set)).withComment(
                                                "iforH2 must be equal to 1,2,3 or 4");

                // constraint on ikinH2
                AtomicParameterExpression ikinH2Expr = factory
                                .createAtomicParameterExpression().withParameterRef(ikinH2Ref);

                Criterion ikinH2Set = new Criterion()
                                .withExpression(ikinH2Expr)
                                .withConditionType(
                                                new BelongToSet()
                                                                .withValue(mktconst("1", ParameterType.INTEGER))
                                                                .withValue(mktconst("2", ParameterType.INTEGER)));

                AlwaysConditionalStatement ikinH2SetAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(ikinH2Set)).withComment(
                                                "iforH2 must be equal to 1 or 2");

                // constraint on TimeJ
                AtomicParameterExpression TimeJExpr = factory
                                .createAtomicParameterExpression().withParameterRef(TimeJRef);

                Criterion TimeJRange = new Criterion()
                                .withExpression(TimeJExpr)
                                .withConditionType(
                                                new ValueInRange()
                                                                .withSup(
                                                                                new ValueSmallerThan().withReached(
                                                                                                false).withValue(
                                                                                                mktconst("1e8",
                                                                                                                ParameterType.REAL)))
                                                                .withInf(
                                                                                new ValueLargerThan()
                                                                                                .withReached(false)
                                                                                                .withValue(
                                                                                                                mktconst(
                                                                                                                                "0",
                                                                                                                                ParameterType.REAL))));

                AlwaysConditionalStatement TimeJRangeAlways = new AlwaysConditionalStatement()
                                .withAlways(new Always().withCriterion(TimeJRange))
                                .withComment("TimeJRange value must be >0 and < 1e8");

                // defining some conditional criteria
                AtomicParameterExpression shockTypeExpr2 = factory
                                .createAtomicParameterExpression().withParameterRef(
                                                shockTypeRef);

                AtomicParameterExpression NfluidExpr2 = factory
                                .createAtomicParameterExpression().withParameterRef(NfluidsRef);

                AtomicParameterExpression MaxTimeNExpr = factory
                                .createAtomicParameterExpression()
                                .withParameterRef(MaxTimeNRef);

                Criterion shockTypeSetC = new Criterion()
                                .withExpression(shockTypeExpr2).withConditionType(
                                                new BelongToSet().withValue(mktconst("C",
                                                                ParameterType.STRING)));

                Criterion shockTypeSetJ = new Criterion()
                                .withExpression(shockTypeExpr2).withConditionType(
                                                new BelongToSet().withValue(mktconst("J",
                                                                ParameterType.STRING)));

                Criterion shockTypeSetS = new Criterion()
                                .withExpression(shockTypeExpr2).withConditionType(
                                                new BelongToSet().withValue(mktconst("S",
                                                                ParameterType.STRING)));

                Criterion Nfluid2ou3 = new Criterion()
                                .withExpression(NfluidExpr2)
                                .withConditionType(
                                                new BelongToSet()
                                                                .withValue(mktconst("2", ParameterType.INTEGER))
                                                                .withValue(mktconst("3", ParameterType.INTEGER)));

                Criterion Nfluid1 = new Criterion().withExpression(NfluidExpr2)
                                .withConditionType(
                                                new BelongToSet().withValue(mktconst("1",
                                                                ParameterType.INTEGER)));

                Criterion MaxTimeNStatique = new Criterion().withExpression(
                                MaxTimeNExpr).withConditionType(
                                new ValueLargerThan().withReached(true).withValue(
                                                mktconst("1e8", ParameterType.REAL)));

                AtomicParameterExpression TimeJMINUSMaxTimeN = factory
                                .createAtomicParameterExpression().withParameterRef(TimeJRef);
                TimeJMINUSMaxTimeN.withOperation(new Operation().withOperationType(
                                OperationType.MINUS).withExpression(MaxTimeNExpr));

                Criterion TimeJMINUSMaxTimeNRange = new Criterion().withExpression(
                                TimeJMINUSMaxTimeN).withConditionType(
                                new ValueLargerThan().withReached(false).withValue(
                                                mktconst("0", ParameterType.REAL)));

                IfThenConditionalStatement ifShockCNfluid2or3 = new IfThenConditionalStatement()
                                .withIf(new If().withCriterion(shockTypeSetC))
                                .withThen(new Then().withCriterion(Nfluid2ou3))
                                .withComment(
                                                "if shock type equal to C, than Nfluids must be 2 or 3");

                IfThenConditionalStatement ifShockCTimeJMINUSMaxTimeNRange = new IfThenConditionalStatement()
                                .withIf(new If().withCriterion(shockTypeSetC))
                                .withThen(new Then().withCriterion(TimeJMINUSMaxTimeNRange))
                                .withComment("if shock type equal to C, than MaxTimeN-TimeJ>0");

                IfThenConditionalStatement ifShockJNfluid1 = new IfThenConditionalStatement()
                                .withIf(new If().withCriterion(shockTypeSetJ))
                                .withThen(new Then().withCriterion(Nfluid1))
                                .withComment("if shock type equal to J, than Nfluids must be 1");

                IfThenConditionalStatement ifShockSMaxTimeLimit = new IfThenConditionalStatement()
                                .withIf(new If().withCriterion(shockTypeSetS))
                                .withThen(new Then().withCriterion(MaxTimeNStatique))
                                .withComment("If Shock type equal to S, then MaxTimeN>1e8");

                // Storing parameters into groups
                shockParameter.getParameterRef().add(shockTypeRef);
                shockParameter.getParameterRef().add(NfluidsRef);
                shockParameter.getParameterRef().add(BbetaRef);
                shockParameter.getParameterRef().add(vsRef);
                shockParameter.getParameterRef().add(VdiRef);
                shockParameter.getParameterRef().add(OpH2Ref);
                shockParameter.getParameterRef().add(TiRef);
                shockParameter.getParameterRef().add(nHiRef);
                shockParameter.getParameterRef().add(TgRef);
                shockParameter.getParameterRef().add(ZetaRef);

                shockParameter.withConstraintOnGroup(new ConstraintOnGroup()
                                .withConditionalStatement(shockTpeAlways)
                                .withConditionalStatement(BbetaLimitsAlways)
                                .withConditionalStatement(NfluidAlways)
                                .withConditionalStatement(vsPosAlways)
                                .withConditionalStatement(vdiPosAlways)
                                .withConditionalStatement(OpH2PosAlways)
                                .withConditionalStatement(TiPosAlways)
                                .withConditionalStatement(nHiPosAlways)
                                .withConditionalStatement(TgPosAlways)
                                .withConditionalStatement(ifShockCNfluid2or3)
                                .withConditionalStatement(ifShockCTimeJMINUSMaxTimeNRange)
                                .withConditionalStatement(ifShockJNfluid1)
                                .withConditionalStatement(ifShockSMaxTimeLimit));

                numericalParameters.getParameterRef().add(NstepMaxRef);
                numericalParameters.getParameterRef().add(NstepWRef);
                numericalParameters.getParameterRef().add(NH2LevRef);
                numericalParameters.getParameterRef().add(NH2LinesRef);
                numericalParameters.getParameterRef().add(iforH2Ref);
                numericalParameters.getParameterRef().add(ikinH2Ref);
                numericalParameters.getParameterRef().add(xllRef);
                numericalParameters.getParameterRef().add(epsVRef);
                numericalParameters.getParameterRef().add(TimeJRef);
                numericalParameters.getParameterRef().add(MaxTimeNRef);

                numericalParameters.withConstraintOnGroup(new ConstraintOnGroup()
                                .withConditionalStatement(NH2LevRangeAlways)
                                .withConditionalStatement(NStepMaxRangeAlways)
                                .withConditionalStatement(NStepWRangeAlways)
                                .withConditionalStatement(NH2LinesRangeAlways)
                                .withConditionalStatement(iforH2SetAlways)
                                .withConditionalStatement(ikinH2SetAlways)
                                .withConditionalStatement(TimeJRangeAlways));

                // inputsPG.getParameterRef().add(mailRef);

                inputsPG.getParameterGroup().add(shockParameter);
                inputsPG.getParameterGroup().add(numericalParameters);

                service.withInputs(inputsPG);
                service.withOutputs(outputsPG);

                return service;
        }
}
