/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SessionBeans;

import com.mycompany.entities.Employees;
import com.mycompany.entities.Attachments;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author omar.said
 */
@Local
public interface EmployeeSessionBeanLocal {
    Employees findById(int e);
    Attachments findByName(String s);
    List<Employees> getEmployee();
    List<Attachments> getAttachments();
    Employees addorupdateEmployee(Employees e);
    void addOrEditFiles(Attachments f);
    void removeEmployee(int id);
    void deleteFiles(Attachments fc);
    void removeEmployees(List<Employees> e);
   
}
