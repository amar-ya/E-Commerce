package org.example.ecommerce.Service;

import org.example.ecommerce.Model.Merchant;
import org.example.ecommerce.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService
{
    private final MerchantStockService merchantStockService;
    ArrayList<Merchant> merchants = new ArrayList<>();

    public MerchantService(MerchantStockService merchantStockService) {
        this.merchantStockService = merchantStockService;
    }

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

    public String addMerchantStockItems(String mId,String pId, int stock){
        ArrayList<MerchantStock> ms = merchantStockService.getMerchantStocks();
        for (MerchantStock m : ms) {
            if(m.getMerchantId().equals(mId)){
                if (m.getProductId().equals(pId)){
                    m.setStock(m.getStock() + stock);
                    return null;
                }else {
                    return "invalid product id";
                }
            }
        }
        return "invalid merchant id";
    }
}
