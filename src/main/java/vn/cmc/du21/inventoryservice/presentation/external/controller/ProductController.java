package vn.cmc.du21.inventoryservice.presentation.external.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.cmc.du21.inventoryservice.common.restful.PageResponse;
import vn.cmc.du21.inventoryservice.common.restful.StandardResponse;
import vn.cmc.du21.inventoryservice.common.restful.StatusResponse;
import vn.cmc.du21.inventoryservice.presentation.external.mapper.ProductMapper;
import vn.cmc.du21.inventoryservice.presentation.external.response.CategoryResponse;
import vn.cmc.du21.inventoryservice.presentation.external.response.ProductResponse;
import vn.cmc.du21.inventoryservice.service.CategoryService;
import vn.cmc.du21.inventoryservice.service.ImageService;
import vn.cmc.du21.inventoryservice.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/api/v1.0")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ImageService storageService;

    //get list products
    @GetMapping("/products")
    ResponseEntity<Object> getAllProducts(@RequestParam(value = "page", required = false) String page
                                        , @RequestParam(value = "size", required = false) String size
                                        , @RequestParam(value = "sort",required = false) String sort) {

        log.info("Mapped getAllProducts method {{GET: /products}}");
        if (page==null || !page.chars().allMatch(Character::isDigit) || page.equals("")) page="1";
        if (size==null || !size.chars().allMatch(Character::isDigit) || size.equals("")) size="10";
        if (sort==null || sort.equals("")) sort="productId";

        int pageInt = Integer.parseInt(page)-1;
        int sizeInt = Integer.parseInt(size);

        Page<ProductResponse> listProduct = productService.getAllProducts(pageInt, sizeInt, sort)
                .map(ProductMapper::convertProductToProductResponse);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PageResponse<Object>(
                        StatusResponse.SUCCESSFUL
                        , "Successfully"
                        , listProduct.getContent()
                        , pageInt +1
                        , listProduct.getTotalPages()
                        , listProduct.getTotalElements()
                )
        );
    }

    //get product by name and sort
    @GetMapping("/product")
    ResponseEntity<Object> getProduct(@RequestParam(value = "name",required = false) String name
                                    , @RequestParam(value = "page",required = false) String page
                                    , @RequestParam(value = "size",required = false) String size
                                    , @RequestParam(value = "sort",required = false) String sort
                                    ,  HttpServletRequest request, HttpServletResponse response) throws Throwable{

        log.info("Mapped getProduct method {{GET: /product}}");
        if (page==null || !page.chars().allMatch(Character::isDigit) || page.equals("")) page="1";
        if (size==null || !size.chars().allMatch(Character::isDigit) || size.equals("")) size="10";
        if (sort==null || sort.equals("")) sort="create-time";

        int pageInt = Integer.parseInt(page)-1;
        int sizeInt = Integer.parseInt(size);

        Page<ProductResponse> listProduct = productService.getProductByName(name, pageInt, sizeInt, sort)
                .map(ProductMapper::convertProductToProductResponse);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PageResponse<Object>(
                        StatusResponse.SUCCESSFUL
                        , "Successfully"
                        , listProduct.getContent()
                        , pageInt +1
                        , listProduct.getTotalPages()
                        , listProduct.getTotalElements()
                )
        );
    }

    //get list product by category
    @GetMapping("/product/category/{categoryId}")
    ResponseEntity<Object> getProductByCategory(@PathVariable(name = "categoryId") long category
                                                , @RequestParam(value = "page", required = false) String page
                                                , @RequestParam(value = "size", required = false) String size
                                                , @RequestParam(value = "sort", required = false) String sort,
                                                HttpServletRequest request, HttpServletResponse response) {

        log.info("Mapped getProductByCategory method {{GET: /product/category/{categoryId}}}");
        if(page == null || page.equals("") || !page.chars().allMatch(Character::isDigit)) page = "1";
        if(size == null || size.equals("") || !size.chars().allMatch(Character::isDigit)) size = "10";
        if(sort == null || sort.equals("")) sort = "productId";

        int pageInt = Integer.parseInt(page) - 1;
        int sizeInt = Integer.parseInt(size);

        Page<ProductResponse> listProduct = productService.getProductByCategory(category, pageInt, sizeInt, sort).map(ProductMapper::convertProductToProductResponse);
        return ResponseEntity.status(HttpStatus.OK).body(
                new PageResponse<Object>(
                        StatusResponse.SUCCESSFUL
                        , "successfully"
                        , listProduct.getContent()
                        , pageInt + 1
                        , listProduct.getTotalPages()
                        , listProduct.getTotalElements()
                )
        );
    }

    @GetMapping("/product/get-detail-product/{id}")
    ResponseEntity<Object> getDetailProduct(@PathVariable Long id) throws Throwable {

        log.info("Mapped getDetailProduct method {{GET: /product/get-detail-product/{id}}}");
        ProductResponse productResponse = ProductMapper.convertProductToProductResponse(productService.getProductById(id));

        return ResponseEntity.status(HttpStatus.OK).body(
                new StandardResponse<>(StatusResponse.SUCCESSFUL,
                        "FOUND",
                        productResponse)
        );
    }

    //get image's url
    @GetMapping("product/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        log.info("Mapped readDetailFile method {{GET: product/files/{fileName:.+}}}");
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }
}
