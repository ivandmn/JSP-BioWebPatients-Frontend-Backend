import java.util.List;
import static junit.framework.TestCase.assertEquals;
import patients.model.IPatientsDAO;
import patients.model.Patient;
import patients.model.PatientsMemoryDAO;

/**
 *
 * @author alumne
 */
public class PatientTestMain {
    public static void main(String[] args) {
        IPatientsDAO daoPatients = new PatientsMemoryDAO();
        int expectedNumPatients = 1;
        String rhNeg = "-";
        List<Patient> resultList = daoPatients.listPatientsByRH(rhNeg);
        System.out.println("Num Pacientes RH Neg. " + expectedNumPatients);
    }
}
