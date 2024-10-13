package com.example.logisticcavan.products.getproducts.domain;

import com.example.logisticcavan.common.MyResult;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class GetCategoryProductsUseCase {
    private final GetCategoryProductsRepo repo ;

    public GetCategoryProductsUseCase(GetCategoryProductsRepo repo) {
        this.repo = repo;
    }

    public Observable<MyResult<List<Product>>> execute(String categoryName){
        return repo.getProductsByCategoryName(categoryName);
    }
}
