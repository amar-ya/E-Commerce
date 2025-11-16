package org.example.ecommerce.Service;

import org.example.ecommerce.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService
{

    ArrayList<User> users = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public boolean updateUser(String id, User user){
        for (int i =0; i< users.size(); i++){
            if (users.get(i).getId().equals(id)){
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for (User u : users){
            if (u.getId().equals(id)){
                users.remove(u);
                return true;
            }
        }
        return false;
    }


}
