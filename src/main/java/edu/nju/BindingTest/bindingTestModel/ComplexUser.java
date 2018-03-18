package edu.nju.BindingTest.bindingTestModel;

/**
 * @author Shenmiu
 * @date 2018/03/07
 * <p>
 * 复合POJO
 */
public class ComplexUser {

    private String name;

    private ContactInfo contactInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
