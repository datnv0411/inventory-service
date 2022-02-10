package vn.cmc.du21.inventoryservice.presentation.external.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.cmc.du21.inventoryservice.common.JwtTokenProvider;
import vn.cmc.du21.inventoryservice.common.restful.PageResponse;
import vn.cmc.du21.inventoryservice.common.restful.StandardResponse;
import vn.cmc.du21.inventoryservice.common.restful.StatusResponse;
import vn.cmc.du21.inventoryservice.presentation.external.mapper.MenuMapper;
import vn.cmc.du21.inventoryservice.presentation.external.request.MenuRequest;
import vn.cmc.du21.inventoryservice.presentation.external.response.MenuResponse;
import vn.cmc.du21.inventoryservice.presentation.internal.response.UserResponse;
import vn.cmc.du21.inventoryservice.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/api/v1.0/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/add/{productId}")
    StandardResponse<Object> addProductToMenu(HttpServletRequest request, HttpServletResponse response,
                                              @PathVariable(name = "productId") long productId)
    {
        MenuResponse menuResponse = MenuMapper.convertMenuToMenuResponse(menuService.addProduct(productId));

        return new StandardResponse<>(
                StatusResponse.SUCCESSFUL,
                "Add successfully",
                menuResponse
            );
    }

    @GetMapping("/remove/{productId}")
    StandardResponse<Object> removeProductFromMenu(HttpServletRequest request, HttpServletResponse response,
                                                   @PathVariable(name = "productId") long productId)
    {
        MenuResponse menuResponse = MenuMapper.convertMenuToMenuResponse(menuService.removeProduct(productId));
        return new StandardResponse<>(
                StatusResponse.SUCCESSFUL,
                "Removed",
                menuResponse
        );
    }

    @PostMapping("/create")
    StandardResponse<Object> createMenu(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody MenuRequest menuRequest)
    {
        UserResponse userLogin = JwtTokenProvider.getInfoUserFromToken(request);

        long userId = userLogin.getUserId();
        menuRequest.setUserId(userId);
        MenuResponse menuResponse = MenuMapper.convertMenuToMenuResponse(
                menuService.createMenu(MenuMapper.convertMenuRequestToMenu(menuRequest))
        );
        return new StandardResponse<>(
                StatusResponse.SUCCESSFUL,
                "Created",
                menuResponse
        );
    }

    @GetMapping("/get-my-menu")
    PageResponse<Object> getMyMenu(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(name = "page", required = false) String page,
                                   @RequestParam(name = "size", required = false) String size,
                                   @RequestParam(name = "sort", required = false) String sort)
    {
        if(page == null || page.equals("") || !page.chars().allMatch(Character::isDigit)) page = "1";
        if(size == null || size.equals("") || !size.chars().allMatch(Character::isDigit)) size = "10";
        if(sort == null || sort.equals("")) sort = "menuId";

        UserResponse userLogin = JwtTokenProvider.getInfoUserFromToken(request);

        long userId = userLogin.getUserId();
        Page<MenuResponse> listMenu = menuService.getPageMenu(userId, page, size, sort)
                .map(MenuMapper::convertMenuToMenuResponse);
        return new PageResponse<>(
                StatusResponse.SUCCESSFUL,
                "Get your menu list successfully",
                listMenu.getContent(),
                Integer.parseInt(page),
                listMenu.getTotalPages(),
                listMenu.getTotalElements()
        );
    }

    @DeleteMapping("/delete/{menuId}")
    StandardResponse<Object> deleteMenu(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable(name = "menuId") long menuId)
    {
        UserResponse userLogin = JwtTokenProvider.getInfoUserFromToken(request);

        long userId = userLogin.getUserId();
        menuService.deleteMenu(userId, menuId);
        return new StandardResponse<>(
                StatusResponse.SUCCESSFUL,
                "Deleted"
        );
    }
    @GetMapping("/get-detail-menu/{menuId}")
    StandardResponse<Object> getDetailMenu(HttpServletRequest request,HttpServletResponse response,
                                           @PathVariable(name ="menuId") long menuId) throws Throwable {
        UserResponse userLogin = JwtTokenProvider.getInfoUserFromToken(request);
        long userId = userLogin.getUserId();
        MenuResponse menuResponse = MenuMapper.convertMenuToMenuResponse(menuService.getMenuById(userId,menuId));
        return new StandardResponse<>(
                StatusResponse.SUCCESSFUL,
                "found!!",menuResponse
        );
    }
}
