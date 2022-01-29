package vn.cmc.du21.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Category;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;
import vn.cmc.du21.inventoryservice.persistence.internal.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getProductByCategory(long category, int page, int size, String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return productRepository.findAllByCategory_CategoryId(category,pageable);
    }
//    public int totalPage (long category, int page, int size, String sort){
//        Page<Product> pageTT = (Page<Product>) productRepository.findAllByCategory_CategoryId(category, PageRequest.of(page,size, Sort.by(sort)));
//        return pageTT.getTotalPages();
//    }
//    public Long totalRecord (long category, int page, int size, String sort){
//        Page<Product> pageTT = (Page<Product>) productRepository.findAllByCategory_CategoryId(category, PageRequest.of(page,size, Sort.by(sort)));
//        return pageTT.getTotalElements();
//    }
    public int totalPageByCategory (long category, int page, int size, String sort){
        //Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        List<Product> list = productRepository.findAllByCategoryId(category);
        if (list.size() % size != 0)
            return list.size()/size +1;
        else return list.size()/size;
    }
    public Long totalRecordByCategory (long category, int page, int size, String sort){
        //Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        List<Product> list = productRepository.findAllByCategoryId(category);
        Long totalRecord = Long.parseLong(String.valueOf(list.size()));
        return totalRecord;
    }

}
