package org.example.ecommerce.Service;

import org.example.ecommerce.Model.Merchant;
import org.example.ecommerce.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService
{

    ArrayList<Merchant> merchants = new ArrayList<>();



    public void addMerchant(Merchant merchant){
        merchants.add(merchant);
    }

    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    public boolean updateMerchant(String id, Merchant merchant){
        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId().equals(id)){
                merchants.set(i, merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(String id){
        for(Merchant m : merchants){
            if (m.getId().equals(id)){
                merchants.remove(m);
                return true;
            }
        }
        return false;
    }


}
