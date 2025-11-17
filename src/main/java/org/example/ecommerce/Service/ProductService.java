package org.example.ecommerce.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.ecommerce.Model.Category;
import org.example.ecommerce.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService
{

    private final CategoryService categoryService;
    ArrayList<Product> products = new ArrayList<>();
    ArrayList reviews = new ArrayList();

    public boolean addProduct(Product product){
        ArrayList<Category> categories = categoryService.getCategories();
        for (Category category : categories) {
            if(category.getId().equals(product.getCategoryId())){
                products.add(product);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String updateProduct(String id, Product product){
        ArrayList<Category> categories = categoryService.getCategories();
        for (int i = 0 ; i < products.size(); i++){
            if(products.get(i).getId().equals(id)){
                for (int j = 0 ; j < categories.size()+1; j++) {
                    if(j == categories.size()){
                        return "invalid category id";
                    }else {
                        if (categories.get(j).getId().equals(product.getCategoryId())){
                            products.set(i,product);
                            return null;
                        }
                    }
                }
            }
        }
        return "invalid product id";
    }

    public boolean deleteProduct(String id){
        for (Product p : products){
            if (p.getId().equals(id)){
                products.remove(p);
                return true;
            }
        }
        return false;
    }

    public Product getProductById(String id){
        for (Product p : products){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public void addReview(String id, String review){
        reviews.add(id);
        reviews.add(review);
    }

    public ArrayList reviewsOfProduct(String id){
        ArrayList result = new ArrayList();
        for(int i = 0 ; i < reviews.size(); i++){
            if(reviews.get(i).equals(id)){
                result.add(reviews.get(i+1));
            }
        }
        return result;
    }

    public ArrayList<Product> getProductsUnderPrice(double price){
        ArrayList<Product> result = new ArrayList<>();
        for(Product p : products){
            if(p.getPrice() <= price){
                result.add(p);
            }
        }
        return result;
    }
}
