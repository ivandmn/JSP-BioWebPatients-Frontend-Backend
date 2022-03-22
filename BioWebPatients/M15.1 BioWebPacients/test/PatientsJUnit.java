/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import static junit.framework.TestCase.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import patients.model.IPatientsDAO;
import patients.model.Patient;
import patients.model.PatientsMemoryDAO;

/**
 * Nota: Estos tests estan contextualizados, en un contenido concreto de la 
 * base de datos. Mirad el constructor.
 * @author amoros
 */
public class PatientsJUnit {
    
    IPatientsDAO daoPatients;
    
    public PatientsJUnit() {
        daoPatients = new PatientsMemoryDAO();
        /* 
        patients.add(
                new Patient("Flavio","Larrea Ayala","gen-man","A",'+',165,80));
            patients.add(
                new Patient("Débora","Aramburu Galvez","gen-wom","O",'+',170,70));
            patients.add(
                new Patient("Albano","Danilo Pla Gallo","gen-oth","AB",'-',180,71));
            patients.add(
                new Patient("Dorothy","Vaughan","gen-wom","B",'+',172,75));
            patients.add(
                new Patient("Mary","Jackson","gen-wom","A",'+',164,60));
            patients.add(
                new Patient("George","Mendel","gen-man","AB",'+',160,60));
        */
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testListDefaultPatients() {
        int expectedNumPatients = 3;
        String expectedNameFirstPatient = "Flavio";
        List<Patient> resultList = daoPatients.listAllPatients();
        assertEquals(expectedNumPatients, resultList.size());
        assertEquals(expectedNameFirstPatient, resultList.get(0).getName());
    }
    
    /*
    @Test
    public void testListRhNegPatients() {
        int expectedNumPatients = 1;
        String rhNeg = "-";
        List<Patient> resultList = daoPatients.listPatientsByRH(rhNeg);
        assertEquals(expectedNumPatients, resultList.size());
    }
*/
}
