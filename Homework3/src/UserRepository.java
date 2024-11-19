import java.util.List;

public interface UserRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);
    List<User> findAllByCity(String city);
    List<User> findAllByJobTitle(String jobTitle);
    List<User> findAllByActiveStatus(boolean isActive);
}
