/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author omar.said
 */
@Entity
@Table(name = "Teachers")
@NamedQueries({
    @NamedQuery(name = "Teachers.findAll", query = "SELECT t FROM Teachers t"),
    @NamedQuery(name = "Teachers.findByTeacherID", query = "SELECT t FROM Teachers t WHERE t.teacherID = :teacherID"),
    @NamedQuery(name = "Teachers.findByFullName", query = "SELECT t FROM Teachers t WHERE t.fullName = :fullName"),
    @NamedQuery(name = "Teachers.findByAge", query = "SELECT t FROM Teachers t WHERE t.age = :age"),
    @NamedQuery(name = "Teachers.findByGender", query = "SELECT t FROM Teachers t WHERE t.gender = :gender"),
    @NamedQuery(name = "Teachers.findByFacility", query = "SELECT t FROM Teachers t WHERE t.facility = :facility"),
    @NamedQuery(name = "Teachers.findByUsername", query = "SELECT t FROM Teachers t WHERE t.username = :username"),
    @NamedQuery(name = "Teachers.findByPassword", query = "SELECT t FROM Teachers t WHERE t.password = :password")})
public class Teachers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TeacherID")
    private Integer teacherID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FullName")
    private String fullName;
    @Column(name = "Age")
    private Integer age;
    @Size(max = 100)
    @Column(name = "Gender")
    private String gender;
    @Size(max = 100)
    @Column(name = "Facility")
    private String facility;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Size(max = 20)
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "teacherID", fetch = FetchType.LAZY)
    private List<Courses> coursesList;

    public Teachers() {
    }

    public Teachers(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public Teachers(Integer teacherID, String fullName) {
        this.teacherID = teacherID;
        this.fullName = fullName;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
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

    public List<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (teacherID != null ? teacherID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teachers)) {
            return false;
        }
        Teachers other = (Teachers) object;
        if ((this.teacherID == null && other.teacherID != null) || (this.teacherID != null && !this.teacherID.equals(other.teacherID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entities.Teachers[ teacherID=" + teacherID + " ]";
    }
    
}
