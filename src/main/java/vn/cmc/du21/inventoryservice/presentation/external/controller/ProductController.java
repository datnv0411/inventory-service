package vn.cmc.du21.inventoryservice.presentation.external.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.cmc.du21.inventoryservice.common.restful.PageResponse;

import vn.cmc.du21.inventoryservice.common.restful.StandardResponse;
import vn.cmc.du21.inventoryservice.common.restful.StatusResponse;

import vn.cmc.du21.inventoryservice.presentation.external.mapper.ProductMapper;
import vn.cmc.du21.inventoryservice.presentation.external.response.ProductResponse;
import vn.cmc.du21.inventoryservice.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1.0")
public class ProductController {
    @Autowired
    ProductService productService;
    //get list product by category
    @GetMapping("/inventory/product")
    ResponseEntity<Object> getProductByCategory(
              @RequestParam(value = "category", required = false) String category
            , @RequestParam(value = "page", required = false) String page
            , @RequestParam(value = "size", required = false) String size
            , @RequestParam(value = "sort", required = false) String sort)
    {
        if (page==null) page="1";
        if (size==null) size="10";
        if (sort==null) sort="productId";

        long categoryLong = Long.parseLong(category);
        int pageInt = Integer.parseInt(page)-1;
        int sizeInt = Integer.parseInt(size);

        List<ProductResponse> listProduct = productService.getProductByCategory(categoryLong,pageInt, sizeInt,sort).stream().map(ProductMapper::convertProductToProductResponse).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(
            new PageResponse<Object>(
                    StatusResponse.SUCCESSFUL
                    ,"successfully"
                    , listProduct
                    , pageInt+1
                    , productService.totalPageByCategory(categoryLong, pageInt,sizeInt,sort)
                    , productService.totalRecordByCategory(categoryLong,pageInt,sizeInt,sort)
            )
        );
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new StandardResponse<Object>(
//                        StatusResponse.SUCCESSFUL
//                        ,"successfully"
//                        , listProduct
//                )
//        );
    }

}
