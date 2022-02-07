package vn.cmc.du21.inventoryservice.presentation.external.mapper;

import vn.cmc.du21.inventoryservice.common.DateTimeUtil;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;
import vn.cmc.du21.inventoryservice.presentation.external.response.ProductResponse;

public class ProductMapper {
    private ProductMapper()
    {
        super();
    }

    public static ProductResponse convertProductToProductResponse(Product product)
    {
        String createTime =  product.getCreateTime() == null ? null : DateTimeUtil.timestampToString( product.getCreateTime());
        return new ProductResponse(product.getProductId(), product.getProductName(), product.getProductSearch(),
                product.getQuantitative(), product.getDescription(), createTime, product.getProductImage(),
                product.getCategory().getCategoryId());
    }
}
