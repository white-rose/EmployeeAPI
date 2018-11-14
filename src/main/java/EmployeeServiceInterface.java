import java.util.List;

public interface EmployeeServiceInterface {

    List<Employee> getAllEmployees();
    Employee getEmployeeById(long employeeId);
    Boolean addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);

}
