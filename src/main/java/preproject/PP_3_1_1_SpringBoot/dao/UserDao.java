package preproject.PP_3_1_1_SpringBoot.dao;



import preproject.PP_3_1_1_SpringBoot.model.User;

import java.util.List;

public interface UserDao {

    List<User> index();

    User getById(int id);


    void addUser(User user);

    void updateUser(User user);

    void deleteUser(int id);
}
