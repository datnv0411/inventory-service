package vn.cmc.du21.inventoryservice.presentation.external.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.cmc.du21.inventoryservice.common.restful.PageResponse;
import vn.cmc.du21.inventoryservice.common.restful.StatusResponse;
import vn.cmc.du21.inventoryservice.presentation.external.mapper.ProductMapper;
import vn.cmc.du21.inventoryservice.presentation.external.response.ProductResponse;
import vn.cmc.du21.inventoryservice.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1.0/products")
public class ProductController {
    @Autowired
    ProductService productService;

    //get list products
    @GetMapping("")
    ResponseEntity<Object> getAllProducts(@RequestParam(value = "page", required = false) String page
            , @RequestParam(value = "size", required = false) String size
            , @RequestParam(value = "sort",required = false) String sort)
    {
        if (page==null || !page.chars().allMatch(Character::isDigit) || page == "") page="1";
        if (size==null || !size.chars().allMatch(Character::isDigit) || size == "") size="10";
        if (sort==null || sort == "") sort="productId";

        int pageInt = Integer.parseInt(page)-1;
        int sizeInt = Integer.parseInt(size);

        List<ProductResponse> listProduct = productService.getAllProducts(pageInt, sizeInt, sort)
                .stream()
                .map(ProductMapper::convertProductToProductResponse)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(
                new PageResponse<Object>(
                        StatusResponse.SUCCESSFUL
                        , "Successfully"
                        , listProduct
                        , pageInt +1
                        , productService.totalPage(pageInt, sizeInt, sort)
                        , productService.totalRecord(pageInt, sizeInt, sort)
                )
        );
    }
}
