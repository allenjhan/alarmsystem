package Model;

/**
 * Created by ahan on 6/2/17.
 */
public class Customer {
    protected String id;
    protected String name;
    protected String address;
    protected String emergencyNumber1;
    protected String emergencyNumber2;
    protected String customerID;
    protected String effectiveDate;
    protected UsernameAndPassword account;

    public Customer(String id, String name, String address, String emergencyNumber1, String emergencyNumber2,
             String customerID, String effectiveDate){
        this.id = id;
        this.name = name;
        this.address = address;
        this.emergencyNumber1 = emergencyNumber1;
        this.emergencyNumber2 = emergencyNumber2;
        this.account = new UsernameAndPassword();
        setCustomerID(customerID);
        account.setUsername(customerID);
        this.effectiveDate = effectiveDate;
    }

    /**
     * Getter for the contract ID of the customer
     * @return the contract ID
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the contract ID of the customer
     * @param id the contract ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the name of the customer
     * @return name of customer
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the customer
     * @param name name of customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for address of customer
     * @return address of customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for address of customer
     * @param address address of customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter of emergency number 1.
     * @return emergency number 1
     */
    public String getEmergencyNumber1() {
        return emergencyNumber1;
    }

    /**
     * Setter of emergency number 1
     * @param emergencyNumber1 emergency number 1
     */
    public void setEmergencyNumber1(String emergencyNumber1) {
        this.emergencyNumber1 = emergencyNumber1;
    }

    /**
     * Getter of emergency number 2
     * @return emergency number 2
     */
    public String getEmergencyNumber2() {
        return emergencyNumber2;
    }

    /**
     * Setter of emergency number 2
     * @param emergencyNumber2 emergency number 2
     */
    public void setEmergencyNumber2(String emergencyNumber2) {
        this.emergencyNumber2 = emergencyNumber2;
    }

    /**
     * Getter of customer ID, used as login username.
     * @return customer ID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Setter of customer ID, used as login username
     * @param customerID customerID
     * @return true if set was successful, false otherwise
     */
    public boolean setCustomerID(String customerID) {

        if (UsernameAndPassword.isValidUsernameOrPassword(customerID)) {
            this.customerID = customerID;
            account.setUsername(customerID);
            return true;
        }else
            return false;
    }

    /**
     * Getter of the effective date of service
     * @return effective date of service
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Setter of the effective date of service
     * @param effectiveDate effective date of service
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * Getter of account, containing username and password
     * @return account, which holds username and password
     */
    public UsernameAndPassword getAccount() {
        return account;
    }

    /**
     * Setter of account, containing username and password
     * @param account holds the username and password
     */
    public void setAccount(UsernameAndPassword account) {
        this.account = account;
    }
}
