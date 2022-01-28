package vn.cmc.du21.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.cmc.du21.inventoryservice.common.VNCharacterUtil;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;
import vn.cmc.du21.inventoryservice.persistence.internal.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(int page, int size, String sort) {
        List<Product> foundProducts = productRepository.findAll(PageRequest.of(page, size, Sort.by(sort))).stream().collect(Collectors.toList());

        for (Product item: foundProducts) {
            item.setProductSearch(VNCharacterUtil.removeAccent(item.getProductName()));
            productRepository.save(item);
        }

        return foundProducts;
    }

    public int totalPage (int page, int size, String sort){
        Page<Product> pageTT = productRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
        return pageTT.getTotalPages();
    }

    public Long totalRecord (int page, int size, String sort){
        Page<Product> pageTT = productRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
        return pageTT.getTotalElements();
    }
}
