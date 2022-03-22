/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patients.model;

import java.util.List;

/**
 *
 * @author alumne
 */
public class TestPatientsDAO {
    
    public static void main(String[] args) {
        IPatientsDAO daoPatients = new PatientsMemoryDAO();
        List<Patient> resultList = daoPatients.listAllPatients();
        System.out.println(resultList.get(0));
    }
}
