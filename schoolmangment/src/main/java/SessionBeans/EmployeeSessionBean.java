package SessionBeans;

import com.mycompany.entities.Employees;
import com.mycompany.entities.Attachments;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EmployeeSessionBean implements EmployeeSessionBeanLocal {

    @PersistenceContext(name = "my_persistence_unit")
    EntityManager em;
    
    @Override
    public List<Employees> getEmployee() {
       return em.createNamedQuery("Employees.findAll").getResultList();
        
    }

    @Override
    public Employees addorupdateEmployee(Employees e) {
       if (e.getEmployeeID() == null) {
            em.persist(e); 
            return e;
        } else {
            return em.merge(e);
        }
    }

    @Override
    public void removeEmployee(int id) {
        Employees e = em.find(Employees.class, id);
        em.remove(e);
    }

    @Override
    public void removeEmployees(List<Employees> list) {
        for (Employees emp : list) {
            em.remove(em.merge(emp));
        }
    }

    @Override
    public Employees findById(int e) {
        
        TypedQuery<Employees> obj=  em.createNamedQuery("Employees.findByEmployeeID",Employees.class);
        obj.setParameter("employeeID", e);
      return  obj.getSingleResult();
    }
   
    @Override
    public Attachments findByName(String s) {
        TypedQuery<Attachments> obj=  em.createNamedQuery("findByFileName",Attachments.class);
        obj.setParameter("FileName", s);
        return  obj.getSingleResult();
    }

    @Override
    public void addOrEditFiles(Attachments f) {
        if (f.getFileID() == null) {
            em.persist(f);  
        } else {
            em.merge(f);  // just update, don’t remove automatically
        }
    }

    @Override
    public List<Attachments> getAttachments() {
      return em.createNamedQuery("Attachments.findAll").getResultList();
    }

    @Override
    public void deleteFiles(Attachments fc) {
        Attachments f = em.merge(fc);
        em.remove(f);
    }
}
