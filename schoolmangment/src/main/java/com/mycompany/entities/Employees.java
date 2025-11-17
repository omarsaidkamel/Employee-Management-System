/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author omar.said
 */
@Entity
@Table(name = "Employees")
@NamedQueries({
   /* @NamedQuery(
    name = "Employees.findAll",
    query = "SELECT DISTINCT e FROM Employees e " +
            "LEFT JOIN FETCH e.depID " +
            "LEFT JOIN FETCH e.attachmentsList"
),*/
    @NamedQuery(name = "Employees.findAll", query = "SELECT DISTINCT e FROM Employees e LEFT JOIN FETCH e.depID LEFT JOIN FETCH e.attachmentsList"),
    @NamedQuery(name = "Employees.findByEmployeeID", query = "SELECT e FROM Employees e WHERE e.employeeID = :employeeID"),
    @NamedQuery(name = "Employees.findByUsername", query = "SELECT e FROM Employees e WHERE e.username = :username"),
    @NamedQuery(name = "Employees.findByPassword", query = "SELECT e FROM Employees e WHERE e.password = :password"),
    @NamedQuery(name = "Employees.findByFullName", query = "SELECT e FROM Employees e WHERE e.fullName = :fullName"),
    @NamedQuery(name = "Employees.findByGender", query = "SELECT e FROM Employees e WHERE e.gender = :gender"),
    @NamedQuery(name = "Employees.findByAge", query = "SELECT e FROM Employees e WHERE e.age = :age"),
    @NamedQuery(name = "Employees.findByJob", query = "SELECT e FROM Employees e WHERE e.job = :job"),
    @NamedQuery(name = "Employees.findByCreationDate", query = "SELECT e FROM Employees e WHERE e.creationDate = :creationDate"),
    @NamedQuery(name = "Employees.findByActive", query = "SELECT e FROM Employees e WHERE e.active = :active"),
    @NamedQuery(name = "Employees.findByEmail", query = "SELECT e FROM Employees e WHERE e.email = :email")})
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private Integer employeeID;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @Size(min = 1, max = 200)
    @Column(name = "FullName")
    private String fullName;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "Age")
    private Integer age;
    @Size(max = 100)
    @Column(name = "Job")
    private String job;
    @Size(max = 500)
    @Column(name = "EmployeeImage")
    private String employeeImage;
    @Column(name = "Creation_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "Active")
    private Boolean active;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(name = "Email")
    private String email;
    @JoinColumn(name = "DepID", referencedColumnName = "DepartmentID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Department depID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeID", fetch = FetchType.LAZY)
    private List<Attachments> attachmentsList = new ArrayList<>();

    public Employees() {
    }

    public Employees(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Employees(Integer employeeID, String username, String password, String fullName) {
        this.employeeID = employeeID;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }
    
    public String getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(String employeeImage) {
        this.employeeImage = employeeImage;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepID() {
        return depID;
    }

    public void setDepID(Department depID) {
        this.depID = depID;
    }

    public List<Attachments> getAttachmentsList() {
        return attachmentsList;
    }

    public void setAttachmentsList(List<Attachments> attachmentsList) {
        this.attachmentsList = attachmentsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeID != null ? employeeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.employeeID == null && other.employeeID != null) || (this.employeeID != null && !this.employeeID.equals(other.employeeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entities.Employees[ employeeID=" + employeeID + " ]";
    }
    
}
