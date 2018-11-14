import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(long employeeId) {
        if (employeeRepository.findById(employeeId).isPresent())
            return employeeRepository.findById(employeeId).get();
        else
            return new Employee();
    }

    @Override
    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    @Override
    public Boolean addEmployee(Employee employee){
        if (!employeeRepository.findById(employee.getUUID()).isPresent()) {
            employeeRepository.save(employee);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

}
