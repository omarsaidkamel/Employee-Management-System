package JSFManagedBean;

import SessionBeans.DepSessionBeanLocal;
import com.mycompany.entities.Employees;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

//////////////////////////////////////////
import org.primefaces.model.file.UploadedFile;
import java.io.InputStream;
import java.io.Serializable;
///////////////////////////////////////////
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import org.primefaces.event.FileUploadEvent;
import SessionBeans.EmployeeSessionBeanLocal;
import com.mycompany.entities.Department;
import com.mycompany.entities.Attachments;
import com.mycompany.utils.ConfigClass;
import com.mycompany.utils.ExcelExporter;
import java.io.File;
import java.io.FileInputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

//////////////////////////////
///
@Named(value = "employeeManagedBean")
@ViewScoped
public class EmployeeManagedBean implements Serializable {

    List<Employees> employees;
    List<Employees> selectedEmployees;
    private UploadedFile file;
    Employees employee;
    private List<String> selectedJobFilters;
    private String selectedGenderFilter;
    private int departmentID;
    private List<Department> departments;
    private List<Attachments> attachmentsFiles = new ArrayList<>();
    private String selectedDepartmentFilter;
    private static final String UPLOAD_DIR_FILES = ConfigClass.get("upload.dir.files");
    private static final String UPLOAD_DIR_IMAGE = ConfigClass.get("upload.dir.img");


    @EJB
    EmployeeSessionBeanLocal adminsession;
    @EJB
    DepSessionBeanLocal dsbl;
    
    
    @PostConstruct
    public void init() {
        this.employee = new Employees();
        this.employees = adminsession.getEmployee();
        this.selectedEmployees = new ArrayList<>();
        try {
            this.departments = dsbl.getDepartment();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    ///////////////////////////////////
    public void handleFilesUpload(FileUploadEvent event) {
        Attachments f = new Attachments();
        UploadedFile file = event.getFile();
        if (file != null) {
            try {
                String fileName = new File(file.getFileName()).getName();
                String empIdDir = employee.getEmployeeID().toString()+ "-Files\\";
                
                String dirEmp = UPLOAD_DIR_FILES + empIdDir ;
                // Ensure upload dir exists
                File uploadDir = new File(dirEmp);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                
                Attachments attachments = new Attachments();
                attachments.setFileName(fileName);
                attachments.setFilePath(empIdDir);
                attachments.setUploadDate(new Date());
                attachments.setEmployeeID(employee);
                attachmentsFiles.add(attachments);
                
                employee.getAttachmentsList().add(attachments);
                
                // Save file DB
                adminsession.addOrEditFiles(attachments);
                adminsession.addorupdateEmployee(employee);

                File targetFile = new File(uploadDir, file.getFileName());
                
                // Save file
                try (InputStream input = file.getInputStream(); FileOutputStream output = new FileOutputStream(targetFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = input.read(buffer)) > 0) {
                        output.write(buffer, 0, length);
                    }
                }

                FacesMessage msg = new FacesMessage("Success",
                        empIdDir + "-IMG." + " uploaded to " + dirEmp);
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } catch (IOException e) {
                FacesMessage msg = new FacesMessage("Upload error", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.err.println("e.getMessage()e.getMessage()" + e.getMessage());
            }

        }
    }

    public void deleteFile(Attachments file) {
      try {
          String empId = employee.getEmployeeID().toString();
          String dirEmp = UPLOAD_DIR_FILES + empId + "-Files\\";
          File targetFile = new File(dirEmp, file.getFileName());
          employee.getAttachmentsList().remove(file);
          adminsession.addorupdateEmployee(employee);
          adminsession.deleteFiles(file);
        
          // Delete the file from disk
          if (targetFile.exists()) {
              if (targetFile.delete()) {
                  FacesContext.getCurrentInstance().addMessage(null,
                          new FacesMessage("Deleted", file.getFileName() + " removed from " + dirEmp));
              } else {
                  FacesContext.getCurrentInstance().addMessage(null,
                          new FacesMessage(FacesMessage.SEVERITY_WARN, "Delete failed",
                                  "Could not delete " + file.getFileName()));
              }
          } else {
              FacesContext.getCurrentInstance().addMessage(null,
                      new FacesMessage(FacesMessage.SEVERITY_WARN, "File not found",
                              file.getFileName() + " does not exist on disk."));
          }

      } catch (Exception e) {
          FacesContext.getCurrentInstance().addMessage(null,
                  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", e.getMessage()));
      }
      attachmentsFiles.remove(file);
  }

    // getters & setters
    public List<Attachments> getAttachmentsFiles() {
        return employee.getAttachmentsList();
    }

    public StreamedContent download(Attachments file) {
        String empid = employee.getEmployeeID().toString();
        String dirEmp = UPLOAD_DIR_FILES + empid + "-Files\\";
        File savedFile = new File(dirEmp, file.getFileName());

        return DefaultStreamedContent.builder()
                .name(savedFile.getName())
                .contentType(getMimeType(savedFile))
                .stream(() -> {
                    try {
                        return new FileInputStream(savedFile);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .build();
    }

    // Helper method
    private String getMimeType(File file) {
        try {
            return Files.probeContentType(file.toPath());
        } catch (IOException e) {
            return "application/octet-stream"; // fallback
        }
    }
    
    public void sendEmailWithAttachments() throws MessagingException, IOException {
        // Gmail SMTP configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // enable TLS
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // TLS port

        final String senderEmail = "osk09876@gmail.com";   // YOUR Gmail
        final String senderPassword = "ihei zzsb znur lxis"; // Gmail App Password
        final String receiverEmail = "pofax43276@bitmens.com"; // Receiver

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Create email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
        message.setSubject("Employee Report with Attachments");

        // Body
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText("Hello,\n\nPlease find attached the files for employee: " 
                         + this.employee.getFullName() + "\n");

        // Multipart container
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);

        // Loop employee attachments
        if (this.employee.getAttachmentsList() != null) {
            for (Attachments att : this.employee.getAttachmentsList()) {
                File file = new File(UPLOAD_DIR_FILES+att.getFilePath()+att.getFileName());
                if (file.exists()) {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.attachFile(file); // simpler than manual stream
                    attachmentPart.setFileName(att.getFileName()); // keep original name
                    multipart.addBodyPart(attachmentPart);
                } else {
                        FacesMessage msg = new FacesMessage("Attachment not found: ", att.getFilePath()+att.getFileName());
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        System.err.println("Attachment not found: " + att.getFilePath()+att.getFileName());
                        return;
                }
            }
        }

        // Set content
        message.setContent(multipart);

        // Send
        Transport.send(message);

        System.out.println("Email sent successfully with " 
                            + (this.employee.getAttachmentsList() != null ? this.employee.getAttachmentsList().size() : 0) 
                            + " attachment(s).");
        FacesMessage msg = new FacesMessage("Sucsses", "Email sent successfully with " 
                            + (this.employee.getAttachmentsList() != null ? this.employee.getAttachmentsList().size() : 0) 
                            + " attachment(s).");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        
    }


    ////////////////////////////////////
    
    public void handleImageUpload(FileUploadEvent event) {

        Employees emp = (Employees) event.getComponent().getAttributes().get("employee");
        
        String empid;
        if (emp.getEmployeeID() != null) {
            empid = emp.getEmployeeID().toString();
        } else if (!employees.isEmpty()) {
            int lastId = employees.get(employees.size() - 1).getEmployeeID();
            empid = String.valueOf(lastId + 1);
        } else {
            empid = "1"; 
        }

        try {
            UploadedFile file = event.getFile();
            String fileName = new File(file.getFileName()).getName();
           
            String dirEmp = UPLOAD_DIR_IMAGE + empid + "-IMG\\";
            // Ensure upload dir exists
            File uploadDir = new File(dirEmp);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Save file
            File targetFile = new File(uploadDir, fileName);
            try (InputStream input = file.getInputStream(); FileOutputStream output = new FileOutputStream(targetFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
            }
            emp.setEmployeeImage(empid + "-IMG\\"+fileName);
            System.err.println("empid + \"-IMG\\\\\"+fileName" +empid + "-IMG\\"+fileName);
            adminsession.addorupdateEmployee(emp);

            FacesMessage msg = new FacesMessage("Success",
                    empid + " uploaded to " + dirEmp);
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e) {
            FacesMessage msg = new FacesMessage("Upload error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void insertEmployee() {
        //Add
        if (this.employee.getEmployeeID() == null) {

            if (employees.contains(employee)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The Inserted Email or UserName Was Found Before"));
                PrimeFaces.current().ajax().update("mainForm:messages");
                return;
            }
            Date d = new Date();
            this.employee.setCreationDate(d);
            Employees emp = adminsession.addorupdateEmployee(employee);
            this.employees.add(emp);

            Department depObj = emp.getDepID();

            System.err.println("depObjdepObjdepObj Add " + depObj.getEmployeesList().size());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employees Inserted"));
            PrimeFaces.current().ajax().update("mainForm:dt-products");
            PrimeFaces.current().ajax().update("mainForm:messages");
            PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");

            //Edit
        } else {

            Employees empOldObj = adminsession.findById(employee.getEmployeeID());
            Department oldDep = empOldObj.getDepID();
            Department newDep = employee.getDepID();

            boolean flag = oldDep.equals(newDep);
            //System.err.println("Flag"+flag);
            if (!flag) {
                oldDep.getEmployeesList().remove(empOldObj);
                dsbl.addorupdateDepartmanrt(oldDep);
            }

            adminsession.addorupdateEmployee(employee);

            if (!flag) {
                if (!newDep.getEmployeesList().contains(employee)) {
                    newDep.getEmployeesList().add(employee);
                    dsbl.addorupdateDepartmanrt(newDep);
                }
            }
            System.err.println("Old dep after edit " + oldDep.getDepartmentName() + " size=" + oldDep.getEmployeesList().size());
            System.err.println("New dep after edit " + newDep.getDepartmentName() + " size=" + newDep.getEmployeesList().size());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employees Updated"));
            PrimeFaces.current().ajax().update("mainForm:dt-products");
            PrimeFaces.current().ajax().update("mainForm:messages");
            PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
        }
        this.employee = new Employees();
        PrimeFaces.current().ajax().update("mainForm:dt-products");
    }

    public boolean hasSelectedEmployees() {
        return (null != selectedEmployees && !selectedEmployees.isEmpty());
    }

    public void deleteEmployee() {
        Employees e = this.employee;
        System.out.println("Deleted Object: " + this.employee.toString());
        if (this.employee == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No employee selected"));
            return;
        }
        adminsession.removeEmployee(e.getEmployeeID());
        this.employees.remove(e);
        this.employee = new Employees();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employee Removed"));
        PrimeFaces.current().ajax().update("mainForm:delete-products-button");
        PrimeFaces.current().ajax().update("mainForm:dt-products");
        PrimeFaces.current().ajax().update("mainForm:messages");
    }

    public String getMessageDelete() {
        if (hasSelectedEmployees()) {
            int size = this.selectedEmployees.size();
            return size > 1 ? size + " Employee selected" : "1 Employee selected";
        }
        return "Delete";
    }

    public void deleteSelectedEmployees() {
        adminsession.removeEmployees(selectedEmployees);
        this.employees.removeAll(this.selectedEmployees);
        this.selectedEmployees = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employees Removed"));
        PrimeFaces.current().ajax().update("mainForm:delete-products-button");
        PrimeFaces.current().ajax().update("mainForm:dt-products");
        PrimeFaces.current().ajax().update("mainForm:messages");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void exportToPDF(List<Employees> employeeList) throws FileNotFoundException {
        // Path to output PDF
        String pdfPath = "D:\\custom_pdf_example.pdf";

        // Create a new document with A4 size
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();

            // Define font styles (customize colors, fonts, sizes)
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
            Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 11);

            // Add a title
            Paragraph title = new Paragraph("User InmainFromation Report", headerFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            // Add some space after title
            document.add(new Paragraph("\n"));

            // Create a table with 6 columns (for each header field)
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100); // Full width
            table.setSpacingBefore(5f); // Space before table
            table.setSpacingAfter(5f);

            // Set column widths (optional)
            float[] columnWidths = {2f, 3f, 3f, 1.5f, 1f, 2f};
            table.setWidths(columnWidths);

            // Create header cells
            String[] headers = {"Username", "Email", "FullName", "Gender", "Age", "Job"};
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Paragraph(header, tableHeaderFont));
                headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                headerCell.setBackgroundColor(Color.blue); // light gray background
                headerCell.setPadding(5f);
                table.addCell(headerCell);
            }

            // Add data rows to table
            for (Employees e : employees) {
                PdfPCell Username = new PdfPCell(new Paragraph(e.getUsername(), tableCellFont));
                Username.setPadding(5f);
                table.addCell(Username);
                PdfPCell Email = new PdfPCell(new Paragraph(e.getEmail(), tableCellFont));
                Email.setPadding(5f);
                table.addCell(Email);
                PdfPCell FullName = new PdfPCell(new Paragraph(e.getFullName(), tableCellFont));
                FullName.setPadding(5f);
                table.addCell(FullName);
                PdfPCell Gender = new PdfPCell(new Paragraph(e.getGender(), tableCellFont));
                Gender.setPadding(5f);
                table.addCell(Gender);
                PdfPCell Age = new PdfPCell(new Paragraph(String.valueOf(e.getAge()), tableCellFont));
                Age.setPadding(5f);
                table.addCell(Age);
                PdfPCell Job = new PdfPCell(new Paragraph(e.getJob(), tableCellFont));
                Job.setPadding(5f);
                table.addCell(Job);

            }

            // Add table to document
            document.add(table);

            // Close document
            document.close();

            System.out.println("PDF created successfully at " + pdfPath);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();

        if (uploadedFile != null) {
            try (InputStream input = uploadedFile.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

                List<Employees> importedEmployees = new ArrayList<>();
                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false; // Skip header
                        continue;
                    }

                    String[] fields = line.split(",");

                    if (fields.length >= 7) {
                        Employees emp = new Employees();
                        System.out.println(emp.getAge());
                        emp.setUsername(fields[0].trim());
                        emp.setEmail(fields[1].trim());
                        emp.setFullName(fields[2].trim());
                        emp.setGender(fields[3].trim());
                        emp.setAge(Integer.parseInt(fields[4].trim()));
                        emp.setJob(fields[5].trim());
                        emp.setPassword(fields[6].trim());
                        emp.setActive(Boolean.TRUE);

                        importedEmployees.add(emp);
                        employee = emp;
                        insertEmployee();
                        employee = new Employees();
                    }
                }

                this.employees.addAll(importedEmployees); // Add to your existing list
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", importedEmployees.size() + " employees imported."));
                PrimeFaces.current().ajax().update("mainForm:dt-products");
                PrimeFaces.current().ajax().update("mainForm:messages");
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to process file: " + e.getMessage()));
                PrimeFaces.current().ajax().update("mainForm:messages");
            }
        }
    }

    public void export() {
        try {
            byte[] excelBytes = ExcelExporter.exportPeople(employees);

            // Send as email attachment
            sendEmailWithAttachment(excelBytes);

            System.out.println("Excel exported and emailed successfully!");

        } catch (IOException | javax.mail.MessagingException e) {
            System.out.println("E = " + e);
            e.printStackTrace();
        }
    }

    private void sendEmailWithAttachment(byte[] fileData) throws MessagingException {
        // Gmail SMTP configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // enable TLS
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // TLS port

        final String senderEmail = "osk09876@gmail.com";   // MY Gmail
        final String senderPassword = "ihei zzsb znur lxis";    // app password 
        final String receiverEmail = "pofax43276@bitmens.com"; // resiver

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Create email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
        message.setSubject("Employee Report");

        // Body
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText("Hello,\n\nPlease find attached the latest Employee Report.\n");

        String filename = "people-report.xlsx";
        // Attachment
        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.setFileName(filename);
        attachmentPart.setContent(fileData, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);

        // Send
        Transport.send(message);

        System.out.println("Email sent successfully with attachment!");
    }
    
    
    
    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
   
    public String getSelectedDepartmentFilter() {
        return selectedDepartmentFilter;
    }

    public void setSelectedDepartmentFilter(String selectedDepartmentFilter) {
        this.selectedDepartmentFilter = selectedDepartmentFilter;
    }
    
    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    

    public String getSelectedGenderFilter() {
        return selectedGenderFilter;
    }

    public void setSelectedGenderFilter(String selectedGenderFilter) {
        this.selectedGenderFilter = selectedGenderFilter;
    }

    public List<String> getSelectedJobFilters() {
        return selectedJobFilters;
    }

    public void setSelectedJobFilters(List<String> selectedJobFilters) {
        this.selectedJobFilters = selectedJobFilters;
    }

    public void endEdit() {
        this.employee = new Employees();
        PrimeFaces.current().ajax().update("mainForm:employeeDialog");
    }

   
    public List<Employees> getSelectedEmployees() {
        return selectedEmployees;
    }

    public void setSelectedEmployees(List<Employees> selectedEmployees) {
        this.selectedEmployees = selectedEmployees;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
}
