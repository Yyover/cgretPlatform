package com.javaee6.cgret.service.impl;

import com.javaee6.cgret.service.IClientLookShopService;
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
import org.springframework.ui.ModelMap;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 **/

@Service
public class IClientLookShopServiceImpl implements IClientLookShopService {

    /**
     * 用户看到的第一页商品(无搜索条件)
     * @param map
     */
    @Override
    public void firstPageCgret(ModelMap map) {
        System.out.println("scroll模式");
        try {
            TransportClient client = ElasticsearchUtil.client();

            List<Map<String, Object>> mapList = new ArrayList<>();


            //条件设置 已上架 未删除
            MatchPhraseQueryBuilder mpq1 = QueryBuilders.matchPhraseQuery("status", "已上架");
            QueryBuilder qb1 = QueryBuilders.boolQuery()
                    .must(mpq1);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(qb1);

            // 首次查询的数据 prepareSearch
            SearchResponse scrollResponse = client.prepareSearch("cgret")
                    .setSearchType(SearchType.DEFAULT)
                    .setTypes("doc")
                    .setQuery(qb1)
                    .setSize(30)
                    .setScroll(new TimeValue(1000))
                    .execute().actionGet();


            SearchHits hitsFirst = scrollResponse.getHits();

            // 遍历得到的10个结
            // 写入List中
            for(SearchHit searchHit : hitsFirst){
                System.out.println(searchHit.getSourceAsString());

                mapList.add(searchHit.getSourceAsMap());
            }

            // 传到modelMap内
            map.put("mapList", mapList);
            client.close();
            System.out.println(mapList.size());

        }catch (Exception e){
            e.printStackTrace();;
        }
    }
}
