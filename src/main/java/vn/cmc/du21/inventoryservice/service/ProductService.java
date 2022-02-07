package vn.cmc.du21.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.cmc.du21.inventoryservice.common.StandardizeStringUtil;
import vn.cmc.du21.inventoryservice.common.VNCharacterUtil;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;
import vn.cmc.du21.inventoryservice.persistence.internal.repository.ProductRepository;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Page<Product> getAllProducts(int page, int size, String sort) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
    }

    public List<Product> getProductByName(String name, String sort ) throws Throwable{

        Set<Product> productList = new LinkedHashSet<>();
        String nameSearch = StandardizeStringUtil.standardizeString(name);
        nameSearch = VNCharacterUtil.removeAccent(nameSearch);

        String[] listWords = nameSearch.split(" ");

        for (String item : listWords
        ) {
            productList.addAll(productRepository.findByProductName(item));
        }

        List<Product> products = new ArrayList<>(productList);

        if(sort.equals("priceSale")){


            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product lhs, Product rhs) {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    return lhs.getPriceDefault() > rhs.getPriceDefault() ? -1 : (lhs.getPriceDefault() < rhs.getPriceDefault()) ? 1 : 0;
                }
            });
            return products;
        } else {
            for (String item : listWords
            ) {
                productList.addAll(productRepository.findByProductName(item));
            }
        }

        return products;
    }
}
