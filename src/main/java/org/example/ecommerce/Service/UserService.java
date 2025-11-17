package org.example.ecommerce.Service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService
{

    ArrayList<User> users = new ArrayList<>();
    private final MerchantStockService merchantStockService;

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

    public String buy(String uid, String pid, String mid){
        String isPresent = merchantStockService.paidItem(pid, mid);
        if(isPresent!=null){
            return isPresent;
        }else {
            return null;
        }
    }

}
