package vn.cmc.du21.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.cmc.du21.inventoryservice.common.StandardizeStringUtil;
import vn.cmc.du21.inventoryservice.common.VNCharacterUtil;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.Product;
import vn.cmc.du21.inventoryservice.persistence.internal.entity.ProductSize;
import vn.cmc.du21.inventoryservice.persistence.internal.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Page<Product> getAllProducts(int page, int size, String sort) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
    }

    @Transactional
    public Page<Product> getProductByName(String name, int page, int size, String sort) throws Throwable{

        Set<Product> productList = new LinkedHashSet<>();
        String nameSearch = StandardizeStringUtil.standardizeString(name);
        nameSearch = VNCharacterUtil.removeAccent(nameSearch);

        String[] listWords = nameSearch.split(" ");

        for (String item : listWords
        ) {
            productList.addAll(productRepository.findByProductName(item));
        }

        List<Product> products = new ArrayList<>(productList);

        if(sort.equals("low-to-high-price")){

            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product lhs, Product rhs) {

                    Comparator comparatorForPriceSale = new Comparator<ProductSize>()
                    {
                        @Override
                        public int compare(ProductSize l, ProductSize r) {
                            return Long.compare(l.getPriceSale(), r.getPriceSale());
                        }
                    };

                    long minLhsPrice = Collections.min(lhs.getProductSizes(), comparatorForPriceSale).getPriceSale();
                    long minRhsPrice = Collections.min(rhs.getProductSizes(), comparatorForPriceSale).getPriceSale();

                    return Long.compare(minLhsPrice, minRhsPrice);
                }
            });
        } else if(sort.equals("most-discounted"))
        {
            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product lhs, Product rhs) {

                    Comparator comparatorForPriceSale = new Comparator<ProductSize>()
                    {
                        @Override
                        public int compare(ProductSize l, ProductSize r) {
                            return Double.compare((l.getPrice() - l.getPriceSale())*1.0/l.getPrice(),
                                    (r.getPrice() - r.getPriceSale())*1.0/r.getPrice());
                        }
                    };

                    ProductSize maxLhs = Collections.max(lhs.getProductSizes(), comparatorForPriceSale);
                    Double maxPercentLhs = (maxLhs.getPrice() - maxLhs.getPriceSale())*1.0/maxLhs.getPrice();
                    ProductSize maxRhs = Collections.max(rhs.getProductSizes(), comparatorForPriceSale);
                    Double maxPercentRhs = (maxRhs.getPrice() - maxRhs.getPriceSale())*1.0/maxRhs.getPrice();
                    return maxPercentLhs >  maxPercentRhs ? -1 : 1;
                }
            });
        } else {
            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product lhs, Product rhs) {
                    return lhs.getCreateTime().after(rhs.getCreateTime()) ? -1 : 1;
                }
            });
        }

        Pageable pageable = PageRequest.of(page, size);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), products.size());
        return new PageImpl<>(products.subList(start, end), pageable, products.size());
    }

    @Transactional
    public Page<Product> getProductByCategory(long category, int page, int size, String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return productRepository.findAllByCategory_CategoryId(category,pageable);
    }
}
