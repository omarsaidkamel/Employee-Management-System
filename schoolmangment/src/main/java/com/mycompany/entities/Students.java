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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author omar.said
 */
@Entity
@Table(name = "Students")
@NamedQueries({
    @NamedQuery(name = "Students.findAll", query = "SELECT s FROM Students s"),
    @NamedQuery(name = "Students.findByStudentID", query = "SELECT s FROM Students s WHERE s.studentID = :studentID"),
    @NamedQuery(name = "Students.findByFullName", query = "SELECT s FROM Students s WHERE s.fullName = :fullName"),
    @NamedQuery(name = "Students.findByAge", query = "SELECT s FROM Students s WHERE s.age = :age"),
    @NamedQuery(name = "Students.findByGender", query = "SELECT s FROM Students s WHERE s.gender = :gender"),
    @NamedQuery(name = "Students.findByLevel", query = "SELECT s FROM Students s WHERE s.level = :level"),
    @NamedQuery(name = "Students.findByUsername", query = "SELECT s FROM Students s WHERE s.username = :username"),
    @NamedQuery(name = "Students.findByPassword", query = "SELECT s FROM Students s WHERE s.password = :password")})
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "StudentID")
    private Integer studentID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FullName")
    private String fullName;
    @Column(name = "Age")
    private Integer age;
    @Size(max = 50)
    @Column(name = "Gender")
    private String gender;
    @Column(name = "level")
    private Integer level;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Size(max = 20)
    @Column(name = "password")
    private String password;
    @ManyToMany(mappedBy = "studentsList", fetch = FetchType.LAZY)
    private List<Courses> coursesList;

    public Students() {
    }

    public Students(Integer studentID) {
        this.studentID = studentID;
    }

    public Students(Integer studentID, String fullName) {
        this.studentID = studentID;
        this.fullName = fullName;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
        hash += (studentID != null ? studentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Students)) {
            return false;
        }
        Students other = (Students) object;
        if ((this.studentID == null && other.studentID != null) || (this.studentID != null && !this.studentID.equals(other.studentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entities.Students[ studentID=" + studentID + " ]";
    }
    
}
