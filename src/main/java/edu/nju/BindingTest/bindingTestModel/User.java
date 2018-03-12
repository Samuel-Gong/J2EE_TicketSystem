package edu.nju.BindingTest.bindingTestModel;

/**
 * @author Shenmiu
 * @date 2018/03/07
 *
 * 用于测试实体类绑定的POJO类
 */
public class User {

    private String name;

    private int age;

    private boolean graduated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }
}
