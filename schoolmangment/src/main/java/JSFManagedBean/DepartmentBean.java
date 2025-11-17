package JSFManagedBean;

import SessionBeans.DepSessionBeanLocal;
import com.mycompany.entities.Department;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named("departmentBean")
@ViewScoped
public class DepartmentBean implements Serializable {

    private List<Department> departments = new ArrayList<>();
    private Department department = new Department();

    @EJB
    DepSessionBeanLocal dsbl;
    
    @PostConstruct
    public void init() {
        this.department = new Department();
        this.departments = dsbl.getDepartment();
    }

    // prepare new
    public void prepareAdd() {
        department = new Department();
    }

    // prepare edit
    public void prepareEdit(Department d) {
        this.department = d;
    }

    // save new or update existing
    public void save() {
        if (department.getDepartmentID() == null) {
            departments.add(department);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Department Inserted"));
            PrimeFaces.current().ajax().update("messages");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Success", "Department Edited"));
            PrimeFaces.current().ajax().update("messages");
        }
        
        dsbl.addorupdateDepartmanrt(department);
    }

    // delete single department
    public void delete(Department d) {
        departments.remove(d);
        dsbl.removeDepatrment(d.getDepartmentID());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Success", "Department Deleted"));
        PrimeFaces.current().ajax().update("messages");
    }

    // getters & setters
    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
