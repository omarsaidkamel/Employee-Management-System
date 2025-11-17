package JSFManagedBean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Named(value = "adminManagedBean")
@ViewScoped
public class AdminManagedBean implements Serializable {

     public String getIncludePath(String tabParam) {
        if (tabParam.equals("1")) {
            return "/UI/DepartmentScreen.xhtml";
        } else {
            return "/UI/EmployeeAdminScreen.xhtml";
        } 
    }
}
