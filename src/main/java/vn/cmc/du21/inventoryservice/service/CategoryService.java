package vn.cmc.du21.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Category;
import vn.cmc.du21.inventoryservice.persistence.internal.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }
}
