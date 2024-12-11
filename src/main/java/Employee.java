public class Employee {
    private Integer employeeId;
    private String name;
    private String email;
    private String password;
    private WorkRole workRole;

    public Employee(Integer employeeId, String name,
                    String email, String password,
                    WorkRole workRole) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.workRole = workRole;
    }

    public Employee(String name, WorkRole workRole,
                    String password, String email) {
        this.name = name;
        this.workRole = workRole;
        this.password = password;
        this.email = email;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public WorkRole getWorkRole() {
        return workRole;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", workRole=" + workRole +
                '}';
    }
}
