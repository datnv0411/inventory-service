package vn.cmc.du21.inventoryservice.presentation.external.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.cmc.du21.inventoryservice.common.restful.PageResponse;
import vn.cmc.du21.inventoryservice.common.restful.StandardResponse;
import vn.cmc.du21.inventoryservice.common.restful.StatusResponse;
import vn.cmc.du21.inventoryservice.presentation.external.mapper.CategoryMapper;
import vn.cmc.du21.inventoryservice.presentation.external.response.CategoryResponse;
import vn.cmc.du21.inventoryservice.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(path = "/api/v1.0")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    // get all category
    @GetMapping("/categories")
    ResponseEntity<Object> getAllCategories(HttpServletRequest request, HttpServletResponse response) {

        log.info("Mapped getAllCategories method {{GET: /categories}}");

        List<CategoryResponse> listCategory = categoryService.getAllCategories()
                .stream().map(CategoryMapper::convertCategoryToCategoryResponse)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(
                new StandardResponse<>(StatusResponse.SUCCESSFUL,
                        "Successfully",
                        listCategory)
        );
    }
}
