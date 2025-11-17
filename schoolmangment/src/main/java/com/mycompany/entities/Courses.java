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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "Courses")
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c"),
    @NamedQuery(name = "Courses.findByCourseID", query = "SELECT c FROM Courses c WHERE c.courseID = :courseID"),
    @NamedQuery(name = "Courses.findByCourseName", query = "SELECT c FROM Courses c WHERE c.courseName = :courseName"),
    @NamedQuery(name = "Courses.findByDescription", query = "SELECT c FROM Courses c WHERE c.description = :description"),
    @NamedQuery(name = "Courses.findByCredits", query = "SELECT c FROM Courses c WHERE c.credits = :credits")})
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CourseID")
    private Integer courseID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CourseName")
    private String courseName;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;
    @Column(name = "Credits")
    private Integer credits;
    @JoinTable(name = "StudentCourses", joinColumns = {
        @JoinColumn(name = "CourseID", referencedColumnName = "CourseID")}, inverseJoinColumns = {
        @JoinColumn(name = "StudentID", referencedColumnName = "StudentID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Students> studentsList;
    @JoinColumn(name = "TeacherID", referencedColumnName = "TeacherID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Teachers teacherID;

    public Courses() {
    }

    public Courses(Integer courseID) {
        this.courseID = courseID;
    }

    public Courses(Integer courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public List<Students> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Students> studentsList) {
        this.studentsList = studentsList;
    }

    public Teachers getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Teachers teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseID != null ? courseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        if ((this.courseID == null && other.courseID != null) || (this.courseID != null && !this.courseID.equals(other.courseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entities.Courses[ courseID=" + courseID + " ]";
    }
    
}
