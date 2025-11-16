package org.example.ecommerce.Service;

import lombok.AllArgsConstructor;
import org.example.ecommerce.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService
{
    ArrayList<Category> categories = new ArrayList<>();

    public void addCategory(Category category){
        categories.add(category);
    }

    public ArrayList<Category> getCategories(){
        return categories;
    }

    public boolean updateCategory(String id, Category category){
        for (int i = 0; i < categories.size(); i++){
            if (categories.get(i).getId().equals(id)){
                categories.set(i, category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(String id){
        for(Category c : categories){
            if (c.getId().equals(id)){
                categories.remove(c);
                return true;
            }
        }
        return false;
    }
}
