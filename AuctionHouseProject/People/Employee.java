package AuctionHouseProject.People;

/**
 * The type Employee.
 * <p>
 * Class that describes the behaviour of an employee. It is characterized by an id.
 */
public abstract class Employee {
    /**
     * The Employee's id.
     */
    private int id;

    /**
     * Instantiates a new Employee.
     */
    public Employee() {
    }

    /**
     * Instantiates a new Employee.
     *
     * @param id the employee's id
     */
    public Employee(int id) {
        this.id = id;
    }

    /**
     * Gets the employee's id.
     *
     * @return the employee's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the employee's id.
     *
     * @param id the employee's id
     */
    public void setId(int id) {
        this.id = id;
    }
}
