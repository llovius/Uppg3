import java.sql.Date;

public class WorkRole {
    private Integer roleID;
    private String title;
    private String description;
    private Double salary;
    public java.sql.Date creationDate;

    public WorkRole(Integer roleID, String title, String description, Double salary, Date creationDate) {
        this.roleID = roleID;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    public WorkRole(String title, String description, Double salary, Date creationDate) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    public WorkRole(Double salary, String description, String title) {
        this.salary = salary;
        this.description = description;
        this.title = title;
        this.creationDate = Date.valueOf(java.time.LocalDate.now());
    }

    public Integer getRoleID() {
        return roleID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getSalary() {
        return salary;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return
                "Roll-Id=" + roleID +
                ", Titel='" + title + '\'' +
                ", Beskrivning='" + description + '\'' +
                ", Lön=" + salary +
                ", Skapad=" + creationDate
                ;
    }
    public String toStringEjId() {
        return
                "Titel='" + title + '\'' +
                ", Beskrivning='" + description + '\'' +
                ", Lön=" + salary +
                ", Skapad=" + creationDate
                ;
    }
}
