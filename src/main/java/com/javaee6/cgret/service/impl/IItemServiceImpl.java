package com.javaee6.cgret.service.impl;

import com.javaee6.cgret.service.IItemService;
import com.javaee6.cgret.util.ElasticsearchUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 **/

@Service
public class IItemServiceImpl implements IItemService {


    /**
     * 通过商品id获得详细信息
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getData(Long id) {

        Map<String, Object> map = new HashMap<>();

        try {
            TransportClient client = ElasticsearchUtil.client();

            //条件设置: productid = #{id}
            MatchPhraseQueryBuilder mpq1 = QueryBuilders.matchPhraseQuery("productid", id);
            QueryBuilder qb1 = QueryBuilders.boolQuery()
                    .must(mpq1);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(qb1);

            // 首次查询的数据 prepareSearch
            SearchResponse scrollResponse = client.prepareSearch("cgret")
                    .setSearchType(SearchType.DEFAULT)
                    .setTypes("doc")
                    .setQuery(qb1)
                    .setSize(1)
                    .setScroll(new TimeValue(1000))
                    .execute().actionGet();

            SearchHits hitsFirst = scrollResponse.getHits();


            // 遍历得到的10个结
            // 写入List中
            for (SearchHit searchHit : hitsFirst) {
                System.out.println(searchHit.getSourceAsString());

                map = searchHit.getSourceAsMap();
            }

            System.out.println(map.size());

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}
