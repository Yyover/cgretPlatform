package com.javaee6.cgret.service.impl;

import com.javaee6.cgret.test.Client;
import com.javaee6.cgret.service.IClientService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;
import com.javaee6.cgret.repository.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Yellowyao
 */
@Service
public class IClientRepositoryImpl implements IClientService {

    @Resource
    private IClientRepository  repository;

    private static final String CLIENT_INDEX_NAME = "cgret_client";
    private static final String CLIENT_INDEX_TYPE = "client";

    /**
     * 保存object(pojo)到repository
     * 可以理解为add
     * @param client
     */
    @Override
    public void save(Client client) {
        repository.save(client);
    }

    /**
     * 通过id从repository查找client
     *
     * @param id
     * @return
     */
    @Override
    public Client findById(Long id) {
        Client rtn = null;
        Optional<Client> client = repository.findById(id);
        if(client.isPresent()){
            rtn = client.get();
        }
        return rtn;

    }

    /**
     * 通过id从repository得到List<client>
     *
     * @param id
     * @return
     */
    @Override
    public List<Client> getByClientId(Integer id) {
        return repository.getByid(id);
    }

    /**
     * 通过name,从repository得到List<client>
     *
     * @param clientName
     * @return
     */
    @Override
    public List<Client> getByClientName(String clientName) {
        return repository.getListByname(clientName);
    }

    /**
     * 分页
     *
     * @param clientName
     * @param pageable
     * @return
     */
    @Override
    public List<Client> getPageByClientName(String clientName, Pageable pageable) {
        Page<Client> clients = repository.getPageByname(clientName, pageable);
        return  clients.getContent();
    }

    /**
     * 得到全部client
     *
     * @return
     */
    @Override
    public List<Client> findAll() {
        List<Client> list = new ArrayList<Client>();
        Iterable<Client> iterable = repository.findAll();
        for(Client item : iterable){
            list.add(item);
        }
        return list;
    }

    /**
     * 删除
     *
     * @param item
     */
    @Override
    public void delete(Client item) {
        repository.delete(item);
    }

    // endregion Repository Methods
    // region Template Methods

    /**
     * template
     */
    @Resource
    private ElasticsearchTemplate template;

    /**
     * @param indexName
     * @param type
     * @param obj
     */
    @Override
    public void save(String indexName, String type, Object obj) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withIndexName(indexName).withType(type).withObject(obj)
                .build();
        template.index(indexQuery);
    }

    /**
     * 这是template的原生方法，它的id是设置成了string,所以这里我们把我们的integer类型的id转换成string类型
     * @param indexName
     * @param type
     * @param id
     */
    @Override
    public void delete(String indexName, String type, Integer id) {

        String idString = String.valueOf(id);
        template.delete(indexName, type, idString);
    }



    /**
     *
     * @param indexName 对象JavaBean上生命的elasticsearch的index和type，构建完IndexQuery就通过Template的index插入
     * @param type
     * @param obj
     */
    @Override
    public void update(String indexName, String type, Object obj) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withIndexName(indexName).withType(type).withObject(obj)
                .build();
        template.index(indexQuery);
    }

    /**
     * 通过id排序
     *
     * @param clazz
     * @return
     */
    @Override
    public <T> List<T> findBySort(Class<T> clazz) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0,10,sort);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withPageable(pageable).build();
        Page<T> items = template.queryForPage(searchQuery, clazz);
        return items.getContent();
    }
}
