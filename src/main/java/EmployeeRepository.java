import org.springframework.data.repository.CrudRepository;

/*
By extending crud repositories, Create, Update, Read and Delete methods can be called.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
