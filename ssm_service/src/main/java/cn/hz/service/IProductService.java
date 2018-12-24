package cn.hz.service;

import cn.hz.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
