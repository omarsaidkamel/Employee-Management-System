package SessionBeans;

import com.mycompany.entities.Department;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class DepSessionBean implements DepSessionBeanLocal {
    @PersistenceContext(name = "my_persistence_unit")
    EntityManager em;
        
    @Override
    public Department findDepartmentById(Integer id) { 
        if (id == null) {
            return null;
        }
        return em.find(Department.class, id);
    }
    
    @Override
    public List<Department> getDepartment() {
       return em.createNamedQuery("Department.findAll", Department.class).getResultList();
    }

    @Override
    public Department addorupdateDepartmanrt(Department d) {
       if (d.getDepartmentID()== null) {
            em.persist(d); 
            return d;
        } else {
            return em.merge(d);
        }
    }

    @Override
    public void removeDepatrment(int id) {
        Department e = em.find(Department.class, id);
        em.remove(e);
    }

}
