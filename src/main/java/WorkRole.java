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

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "WorkRole{" +
                "roleID=" + roleID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", creationDate=" + creationDate +
                '}';
    }
}
