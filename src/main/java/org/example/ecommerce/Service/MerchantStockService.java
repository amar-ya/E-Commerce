package org.example.ecommerce.Service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.Model.Merchant;
import org.example.ecommerce.Model.MerchantStock;
import org.example.ecommerce.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService
{

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    private final ProductService productService;
    private final MerchantService merchantService;

    public String addMerchantStock(MerchantStock merchantStock){
        ArrayList<Product> products = productService.getProducts();
        ArrayList<Merchant> merchants = merchantService.getMerchants();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(merchantStock.getProductId())) {
                for(int j = 0; j < merchants.size()+1; j++){
                    if(j == merchants.size()){
                        return "invalid merchant id";
                    }else {
                        if (merchants.get(j).getId().equals(merchantStock.getMerchantId())) {
                            merchantStocks.add(merchantStock);
                            return null;
                        }
                    }
                }
            }
        }
        return "invalid product id";
    }

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }



    public String updateMerchantStock(String id, MerchantStock merchantStock){
        ArrayList<Product> products = productService.getProducts();

        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantId().equals(id)) {
                for (int j = 0; j < products.size()+1; j++) {
                    if (j == products.size()){
                        return "invalid product id";
                    }else{
                        if (products.get(i).getId().equals(merchantStock.getProductId())){
                            merchantStocks.set(i,merchantStock);
                            return null;
                        }
                    }
                }
            }
        }
        return "invalid merchant id";
    }

    public boolean deleteMerchantStock(String id){
        for (MerchantStock ms : merchantStocks){
            if (ms.getId().equals(id)){
                merchantStocks.remove(ms);
                return true;
            }
        }
        return false;
    }

    public String paidItem(String pid, String mid){
        for(MerchantStock ms : merchantStocks){
            if(ms.getProductId().equals(pid)){
                if (ms.getMerchantId().equals(mid)){
                    ms.setStock(ms.getStock() - 1);
                    return null;
                }else {
                    return "invalid merchant id";
                }
            }
        }
        return "invalid product id";
    }

}
