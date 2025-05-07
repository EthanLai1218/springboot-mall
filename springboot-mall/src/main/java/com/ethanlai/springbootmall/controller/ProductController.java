package com.ethanlai.springbootmall.controller;

import com.ethanlai.springbootmall.constant.ProductCategory;
import com.ethanlai.springbootmall.dto.ProductQueryParams;
import com.ethanlai.springbootmall.dto.ProductRequest;
import com.ethanlai.springbootmall.model.Product;
import com.ethanlai.springbootmall.service.ProductService;
import com.ethanlai.springbootmall.util.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Tag(name = "商品管理", description = "提供商品的新增、查詢、修改與刪除功能")
@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "查詢商品列表",
               description = "可依分類、關鍵字搜尋、排序與分頁條件取得商品資料"
    )
    @GetMapping("/products")
    //public ResponseEntity<List<Product>> getProducts(
    public ResponseEntity<Page<Product>> getProducts(
            // 查詢條件 Filtering
            @Parameter(description = "商品分類") @RequestParam(required = false) ProductCategory category,
            @Parameter(description = "搜尋關鍵字") @RequestParam(required = false) String search,

            // 排序 Sorting
            @Parameter(description = "排序欄位") @RequestParam(defaultValue = "created_date") String orderBy, // 預設最新的在前面
            @Parameter(description = "排序方式") @RequestParam(defaultValue = "desc") String sort, // 預設降序排列

            // 分頁 Pagination，使用 Max 和 Min 註解，要在 class 上加 Validated 註解
            @Parameter(description = "每頁筆數") @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @Parameter(description = "分頁起始位置") @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        // 取得 product list
        List<Product> productList = productService.getProducts(productQueryParams);

        // 取得 product 總數
        Integer total = productService.countProduct(productQueryParams);

        // 分頁
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        // return ResponseEntity.status(HttpStatus.OK).body(productList);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @Operation(summary = "查詢商品",
               description = "根據商品 ID 取得商品詳細資訊"
    )
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if(product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "新增商品",
               description = "根據請求資料建立新的商品"
    )
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {

        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @Operation(summary = "修改商品數據",
               description = "根據商品 ID 修改該商品的資料"
    )
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {

        // 檢查 product 是否存在
        Product product = productService.getProductById(productId);

        if(product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 修改商品數據
        productService.updateProduct(productId, productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @Operation(summary = "刪除商品",
               description = "根據商品 ID 修改該商品的資料"
    )
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?>  deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
