package com.javaee6.cgret.repository;

import com.javaee6.cgret.test.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 这里是一个容易出bug的地方
 * @author Yellowyao
 * 商品信息用elasticsearch，而其他普通信息使用mysql的普通查询就可以
 */

public interface IClientRepository extends ElasticsearchRepository<Client, Long> {

    /**
     * 这里注意命名的id要和Client类中的属性名一样，这是jpa的严格规定，否则repository就找不到。
     * @param id
     * @return
     */
    List<Client> getByid(Integer id);

    List<Client> getListByname(String name);

    Page<Client> getPageByname(String name, Pageable pageable);


}
