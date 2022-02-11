package vn.cmc.du21.inventoryservice.presentation.external.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Object> addProductToMenu(@PathVariable(name = "productId") long productId,
                                              HttpServletRequest request, HttpServletResponse response) throws Throwable {

        MenuResponse menuResponse = MenuMapper.convertMenuToMenuResponse(menuService.addProduct(productId));

        return ResponseEntity.status(HttpStatus.OK).body(
                new StandardResponse<>(
                    StatusResponse.SUCCESSFUL,
                    "Add successfully",
                    menuResponse
                )
        );
    }

    @GetMapping("/remove/{productId}")
    ResponseEntity<Object> removeProductFromMenu(@PathVariable(name = "productId") long productId,
                                                   HttpServletRequest request, HttpServletResponse response ) throws Throwable {

        MenuResponse menuResponse = MenuMapper.convertMenuToMenuResponse(menuService.removeProduct(productId));

        return ResponseEntity.status(HttpStatus.OK).body(
                new StandardResponse<>(
                        StatusResponse.SUCCESSFUL,
                        "Removed",
                        menuResponse
                )
        );
    }

    @PostMapping("/create")
    ResponseEntity<Object> createMenu(@RequestBody MenuRequest menuRequest,
                                        HttpServletRequest request, HttpServletResponse response) {

        UserResponse userLogin;
        try {
            userLogin = JwtTokenProvider.getInfoUserFromToken(request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new StandardResponse<>(
                            StatusResponse.UNAUTHORIZED,
                            "Bad token!!!"
                    )
            );
        }

        long userId = userLogin.getUserId();
        menuRequest.setUserId(userId);
        MenuResponse menuResponse = MenuMapper.convertMenuToMenuResponse(
                menuService.createMenu(MenuMapper.convertMenuRequestToMenu(menuRequest))
        );

        return ResponseEntity.status(HttpStatus.OK).body(
                new StandardResponse<>(
                        StatusResponse.SUCCESSFUL,
                        "Created",
                        menuResponse
                )
        );
    }

    @GetMapping("/get-my-menu")
    ResponseEntity<Object> getMyMenu(@RequestParam(name = "page", required = false) String page,
                                   @RequestParam(name = "size", required = false) String size,
                                   @RequestParam(name = "sort", required = false) String sort,
                                   HttpServletRequest request, HttpServletResponse response){

        if(page == null || page.equals("") || !page.chars().allMatch(Character::isDigit)) page = "1";
        if(size == null || size.equals("") || !size.chars().allMatch(Character::isDigit)) size = "10";
        if(sort == null || sort.equals("")) sort = "menuId";

        UserResponse userLogin;
        try {
            userLogin = JwtTokenProvider.getInfoUserFromToken(request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new StandardResponse<>(
                            StatusResponse.UNAUTHORIZED,
                            "Bad token!!!"
                    )
            );
        }

        long userId = userLogin.getUserId();
        Page<MenuResponse> listMenu = menuService.getPageMenu(userId, page, size, sort)
                .map(MenuMapper::convertMenuToMenuResponse);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PageResponse<>(
                        StatusResponse.SUCCESSFUL,
                        "Get your menu list successfully",
                        listMenu.getContent(),
                        Integer.parseInt(page),
                        listMenu.getTotalPages(),
                        listMenu.getTotalElements()
                )
        );

    }

    @DeleteMapping("/delete/{menuId}")
    ResponseEntity<Object> deleteMenu(@PathVariable(name = "menuId") long menuId,
                                        HttpServletRequest request, HttpServletResponse response) throws Throwable {

        UserResponse userLogin;
        try {
            userLogin = JwtTokenProvider.getInfoUserFromToken(request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new StandardResponse<>(
                            StatusResponse.UNAUTHORIZED,
                            "Bad token!!!"
                    )
            );
        }

        long userId = userLogin.getUserId();
        menuService.deleteMenu(userId, menuId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new StandardResponse<>(
                    StatusResponse.SUCCESSFUL,
                    "Deleted"
                )
        );
    }

    @GetMapping("/get-detail-menu/{menuId}")
    ResponseEntity<Object> getDetailMenu(@PathVariable(name ="menuId") long menuId,
                                           HttpServletRequest request,HttpServletResponse response) throws Throwable {

        UserResponse userLogin;
        try {
            userLogin = JwtTokenProvider.getInfoUserFromToken(request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new StandardResponse<>(
                            StatusResponse.UNAUTHORIZED,
                            "Bad token!!!"
                    )
            );
        }

        long userId = userLogin.getUserId();
        MenuResponse menuResponse = MenuMapper.convertMenuToMenuResponse(menuService.getMenuById(userId,menuId));

        return ResponseEntity.status(HttpStatus.OK).body(
                new StandardResponse<>(
                        StatusResponse.SUCCESSFUL,
                        "found!!",
                        menuResponse
                )
        );
    }
}
