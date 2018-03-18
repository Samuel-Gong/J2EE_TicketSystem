package edu.nju.BindingTest.bindingTestModel;

/**
 * @author Shenmiu
 * @date 2018/03/07
 * <p>
 * 用作复合POJO中的一个属性
 */
public class ContactInfo {

    private String tel;

    private String address;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
