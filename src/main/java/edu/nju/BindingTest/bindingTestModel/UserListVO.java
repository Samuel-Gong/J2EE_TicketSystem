package edu.nju.BindingTest.bindingTestModel;

import java.util.List;

/**
 * @author Shenmiu
 * @date 2018/03/07
 * <p>
 * 用来包装List对象
 */
public class UserListVO {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
