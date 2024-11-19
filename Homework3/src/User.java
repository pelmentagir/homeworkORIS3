public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String city;
    private String jobTitle;
    private Boolean isActive;

    public User(long id, String firstName, String lastName, Integer age, String city, String jobTitle, Boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.jobTitle = jobTitle;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }
}