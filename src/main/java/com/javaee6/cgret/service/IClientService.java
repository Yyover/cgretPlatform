package com.javaee6.cgret.service;

import com.javaee6.cgret.model.Client;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IClientService {

    // region Repository Methods
    /**
     * 保存数据
     * @param client
     */
    void save(Client client);

    /**
     * 通过id查找client
     * @param id
     * @return
     */
    Client findById(Long id);

    /**
     * 通过id得到List<client>
     * @param id
     * @return
     */
    List<Client> getByClientId(Long id);

    /**
     * 通过name得到List<client>
     * @param clientName
     * @return
     */
    List<Client> getByClientName(String clientName);

    /**
     * 分页
     * @param clientName
     * @param pageable
     * @return
     */
    List<Client> getPageByClientName(String clientName, Pageable pageable);

    /**
     * 得到全部client
     * @return
     */
    List<Client> findAll();

    /**
     * 删除
     * @param item
     */
    void delete(Client item);

    // endregion Repository Methods

    // region Template Methods

    /**
     * @param indexName
     * @param type
     * @param obj
     */
    void save(String indexName, String type, Object obj);

    /**
     * @param indexName
     * @param type
     * @param id
     * 这里的id是string的，有待商榷
     */
    void delete(String indexName, String type, Long id);

    /**
     * @param indexName
     * @param type
     * @param obj
     */
    void update(String indexName, String type, Object obj);

    /**
     * 得到后排序
     * @param clazz
     * @param <T>
     * @return
     */
    <T> List<T> findBySort(Class<T> clazz);


}