package vn.cmc.du21.inventoryservice.presentation.external.mapper;

import vn.cmc.du21.inventoryservice.persistence.internal.entity.Category;
import vn.cmc.du21.inventoryservice.presentation.external.response.CategoryResponse;

public class CategoryMapper {
    private CategoryMapper()
    {
        super();
    }

    public static CategoryResponse convertCategoryToCategoryResponse(Category category){
        return new CategoryResponse(category.getCategoryId(), category.getCategoryName());
    }
}
