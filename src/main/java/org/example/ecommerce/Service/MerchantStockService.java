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
        for (int i = 0; i < products.size()+1; i++) {
            if (i == products.size()+1){
                return "1";
            }else {
                for (int j = 0; j < merchants.size()+1; j++){
                    if (j== merchants.size()+1){
                        return "2";
                    }else {
                        if(products.get(i).getId().equals(merchantStock.getProductId())
                                &&
                                merchants.get(j).getId().equals(merchantStock.getMerchantId()
                                )){
                            merchantStocks.add(merchantStock);
                        }
                    }
                }
            }
        }
        return "3";
    }

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public String updateMerchantStock(String id, MerchantStock merchantStock){
        ArrayList<Product> products = productService.getProducts();
        ArrayList<Merchant> merchants = merchantService.getMerchants();
        for (int i = 0; i < products.size()+1; i++) {
            if (i == products.size()+1){
                return "1";
            }else {
                for (int j = 0; j < merchants.size()+1; j++){
                    if (j== merchants.size()+1){
                        return "2";
                    }else {
                        if(products.get(i).getId().equals(merchantStock.getProductId())
                                &&
                                merchants.get(j).getId().equals(merchantStock.getMerchantId()
                                )){
                            merchantStocks.set(i,merchantStock);
                        }
                    }
                }
            }
        }
        return "3";
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
}
