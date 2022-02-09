package vn.cmc.du21.inventoryservice.presentation.external.mapper;

import vn.cmc.du21.inventoryservice.common.DateTimeUtil;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.ProductSize;
import vn.cmc.du21.inventoryservice.presentation.external.response.ProductResponse;
import vn.cmc.du21.inventoryservice.presentation.external.response.SizeResponse;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    private ProductMapper()
    {
        super();
    }

    public static ProductResponse convertProductToProductResponse(Product product) {
        String createTime = product.getCreateTime() == null ? null : DateTimeUtil.timestampToString(product.getCreateTime());
        List<SizeResponse> sizeResponseList = new ArrayList<>();
        for (ProductSize item : product.getProductSizes())
        {
            SizeResponse sizeResponse = new SizeResponse();
            sizeResponse.setSizeId(item.getSize().getSizeId());
            sizeResponse.setSizeName(item.getSize().getSizeName());
            sizeResponse.setPrice(item.getPrice());
            sizeResponse.setPriceSale(item.getPriceSale());


            sizeResponseList.add(sizeResponse);
        }
        return new ProductResponse(product.getProductId(), product.getProductName(), product.getProductSearch(),
                product.getQuantitative(), product.getDescription(), createTime, product.getProductImage(),
                product.getCategory().getCategoryId(),sizeResponseList);
    }
}
