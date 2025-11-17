package org.example.ecommerce.Service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.Model.Merchant;
import org.example.ecommerce.Model.MerchantStock;
import org.example.ecommerce.Model.Product;
import org.example.ecommerce.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService
{

    private final ProductService productService;
    private final MerchantService merchantService;
    ArrayList<User> users = new ArrayList<>();
    private final MerchantStockService merchantStockService;
    ArrayList history = new ArrayList<>();



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
        ArrayList<MerchantStock> checkStocks = merchantStockService.getMerchantStocks();
        for (int i = 0; i <= checkStocks.size(); i++){
            boolean found = false;
            if(i == checkStocks.size()){
                return "invalid product id";
            }else {
                if(checkStocks.get(i).getProductId().equals(pid)){
                    for (int j = 0; j <= checkStocks.size(); j++){
                        if(j == checkStocks.size()){
                            return "invalid merchant id";
                        }else {
                            if(checkStocks.get(j).getMerchantId().equals(mid)){
                                found = true;
                                break;
                            }
                        }
                    }
                    if(found){
                        break;
                    }
                }
            }
        }
        Product p = productService.getProductById(pid);
        for(User u : users){
            if (u.getId().equals(uid)){
                if (p.getPrice() > u.getBalance()){
                    return "balance is too low";
                }else {
                    String result = merchantStockService.paidItem(pid, mid);
                    if (result != null){
                        return result;
                    }else {
                        u.setBalance(u.getBalance() - p.getPrice());
                        history.add(u.getId());
                        history.add(p);
                        return null;
                    }
                }
            }
        }
        return "user not found";
    }

    public boolean chargeBalance(String uid, int amount){
        for (User u : users){
            if (u.getId().equals(uid)){
                u.setBalance(u.getBalance() + amount);
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getHistoryById(String id){
        ArrayList reuslt = new ArrayList();
        for (int i = 0 ; i < history.size(); i++){
            if (history.get(i).equals(id)){
                reuslt.add(history.get(i+1));
            }
        }
        return reuslt;
    }

    public String giftBalance(String uid, String ruid, int amount){
        for (User u : users){
            if (u.getId().equals(uid)){
                if (u.getBalance() < amount){
                    return "balance is too low";
                }else {
                    for (User r : users){
                        if (r.getId().equals(ruid)){
                            u.setBalance(u.getBalance() - amount);
                            r.setBalance(r.getBalance() + amount);
                            return null;
                        }
                    }
                }
            }
        }
        return "user not found";
    }

    public String SellingLicence(String id){
        Random random = new Random();
        int i = random.nextInt(2);
        for (User u : users){
            if (u.getId().equals(id)){
                Merchant m = new Merchant(u.getId(), u.getUsername());
                if (i == 0){
                    return "not Aproved trying applying later";
                }
                merchantService.addMerchant(m);
                return null;
            }
        }
        return "user not found";
    }

    public String reviewProduct(String uid, String pid, String review){

        for (User u : users){
            if (u.getId().equals(uid)){
                Product p = productService.getProductById(pid);
                if(p != null){
                    review = "this review were written by "+u.getUsername()+" "+ review;
                    productService.addReview(pid, review);
                    return null;
                } else if (p == null) {
                    return "product not found";
                }
            }
        }
        return "user not found";
    }

    public String buy(String uid, String pid, String mid, int amount){
        ArrayList<MerchantStock> checkStocks = merchantStockService.getMerchantStocks();
        for (int i = 0; i <= checkStocks.size(); i++){
            boolean found = false;
            if(i == checkStocks.size()){
                return "invalid product id";
            }else {
                if(checkStocks.get(i).getProductId().equals(pid)){
                    for (int j = 0; j <= checkStocks.size(); j++){
                        if(j == checkStocks.size()){
                            return "invalid merchant id";
                        }else {
                            if(checkStocks.get(j).getMerchantId().equals(mid)){

                                found = true;
                                break;
                            }
                        }
                    }
                    if(found){
                        break;
                    }
                }
            }
        }
        Product get = productService.getProductById(pid);
        Product p = new Product(get.getId(),get.getName(),get.getPrice(),get.getCategoryId());
        for(User u : users){
            if (u.getId().equals(uid)){
                if (p.getPrice()*amount > u.getBalance()){
                    return "balance is too low";
                }else {
                    u.setBalance(u.getBalance() - p.getPrice()*amount);
                    String result = merchantStockService.paidItem(pid, mid, amount);
                    if (result != null){
                        return result;
                    }else {
                        history.add(u.getId());
                        p.setPrice(p.getPrice()*amount);
                        history.add(p);
                        return null;
                    }
                }
            }
        }
        return "user not found";
    }

}
