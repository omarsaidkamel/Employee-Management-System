/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author omar.said
 */
@Entity
@Table(name = "Attachments")
@NamedQueries({
    @NamedQuery(name = "Attachments.findAll", query = "SELECT a FROM Attachments a"),
    @NamedQuery(name = "Attachments.findByFileID", query = "SELECT a FROM Attachments a WHERE a.fileID = :fileID"),
    @NamedQuery(name = "Attachments.findByFileName", query = "SELECT a FROM Attachments a WHERE a.fileName = :fileName"),
    @NamedQuery(name = "Attachments.findByFilePath", query = "SELECT a FROM Attachments a WHERE a.filePath = :filePath"),
    @NamedQuery(name = "Attachments.findByUploadDate", query = "SELECT a FROM Attachments a WHERE a.uploadDate = :uploadDate")})
public class Attachments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FileID")
    private Integer fileID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FileName")
    private String fileName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "FilePath")
    private String filePath;
    @Column(name = "UploadDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employees employeeID;

    public Attachments() {
    }

    public Attachments(Integer fileID) {
        this.fileID = fileID;
    }

    public Attachments(Integer fileID, String fileName, String filePath) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Employees getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employees employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.fileName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Attachments other = (Attachments) obj;
        return Objects.equals(this.fileName, other.fileName);
    }

  

    @Override
    public String toString() {
        return "com.mycompany.entities.Attachments[ fileID=" + fileID + " ]";
    }
    
}
