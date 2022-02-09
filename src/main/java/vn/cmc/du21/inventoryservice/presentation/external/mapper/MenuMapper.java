package vn.cmc.du21.inventoryservice.presentation.external.mapper;

import vn.cmc.du21.inventoryservice.persistence.internal.entity.Menu;
import vn.cmc.du21.inventoryservice.presentation.external.request.MenuRequest;
import vn.cmc.du21.inventoryservice.presentation.external.response.MenuResponse;
import vn.cmc.du21.inventoryservice.presentation.external.response.ProductResponse;
import vn.cmc.du21.inventoryservice.presentation.external.response.SizeResponse;

import java.util.ArrayList;
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
                .collect(Collectors.toSet());
        String menuName = menu.getMenuName() == null ? null : menu.getMenuName();

        List listSizeResponse = new ArrayList<SizeResponse>();
//        for()
//
//        SizeResponse sizeResponse = new SizeResponse();
//        for(Product item : menu.getProducts())
//        {
//
//            listSizeResponse.add(sizeResponse);
//        }



        return new MenuResponse(menu.getMenuId(), menuName, menu.getTotalMoney(),
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
