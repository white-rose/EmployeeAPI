import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.util.List;

public class EmployeeController {

    @Autowired
    private EmployeeServiceInterface employeeService;

    @GetMapping("employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PostMapping("employee")
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee, UriComponentsBuilder builder)
    {
        boolean isSuccessful = employeeService.addEmployee(employee);
        if (isSuccessful)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(employee.getUUID()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee)
    {
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("employee/{uuid}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("uuid") Long uuid)
    {
        employeeService.deleteEmployee(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
