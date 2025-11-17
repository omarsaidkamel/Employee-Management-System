package SessionBeans;

import com.mycompany.entities.Department;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author omar.said
 */
@Local
public interface DepSessionBeanLocal {

    public List<Department> getDepartment();

    public Department addorupdateDepartmanrt(Department d);

    public void removeDepatrment(int id);
    
    Department findDepartmentById(Integer id);   
}
