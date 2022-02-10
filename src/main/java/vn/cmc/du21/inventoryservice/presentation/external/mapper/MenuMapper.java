package vn.cmc.du21.inventoryservice.presentation.external.mapper;

import vn.cmc.du21.inventoryservice.persistence.internal.entity.Menu;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.ProductSize;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Size;
import vn.cmc.du21.inventoryservice.presentation.external.request.MenuRequest;
import vn.cmc.du21.inventoryservice.presentation.external.response.MenuResponse;
import vn.cmc.du21.inventoryservice.presentation.external.response.ProductResponse;
import vn.cmc.du21.inventoryservice.presentation.external.response.SizeResponse;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuMapper {
    private MenuMapper()
    {
        super();
    }

    public static MenuResponse convertMenuToMenuResponse(Menu menu) {
        Set<ProductResponse> products = menu.getProducts().stream()
                .map(ProductMapper::convertProductToProductResponse)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        String menuName = menu.getMenuName() == null ? null : menu.getMenuName();

        List listSizeResponse = new ArrayList<SizeResponse>();


        for(Size itemSize : menu.getSizes())
        {
            SizeResponse sizeResponse = new SizeResponse();

            sizeResponse.setSizeId(itemSize.getSizeId());
            sizeResponse.setSizeName(itemSize.getSizeName());

            for(Product itemProduct : menu.getProducts())
            {
                for(ProductSize itemProductSize : itemProduct.getProductSizes())
                {
                    if(itemProductSize.getSize().getSizeId() == itemSize.getSizeId())
                    {
                        sizeResponse.setPrice(sizeResponse.getPrice() + itemProductSize.getPrice());
                        sizeResponse.setPriceSale(sizeResponse.getPriceSale() + itemProductSize.getPriceSale());
                    }
                }
            }

            listSizeResponse.add(sizeResponse);
        }



        return new MenuResponse(menu.getMenuId(), menuName,
                menu.getUserId(), products, listSizeResponse);
    }

    public static Menu convertMenuRequestToMenu(MenuRequest menuRequest)
    {
        Menu menu = new Menu();
        menu.setUserId(menuRequest.getUserId());
        menu.setMenuName(menuRequest.getMenuName());
        return menu;
    }
}
