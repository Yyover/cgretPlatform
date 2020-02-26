/*
package com.javaee6.cgret.controller;


import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.repository.IClientRepository;
import com.javaee6.cgret.service.IClientService;
import com.javaee6.cgret.service.IMainClientService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


*/
/**
 * @author Yellowyao
 *//*

@Controller
@RequestMapping("/es")
public class ElasticController {

    @Resource
    private IClientService esService;

    @Resource
    private IMainClientService mClientService;

    @Resource
    private IClientRepository iClientRepository;

    */
/**
     * 插入数据
     * @return
     *//*

    @RequestMapping("/save1")
    public String save1(){
        Long clientId = 49L;

        Client c = new Client();
        c.setClientName("jjojo");
        c.setClientId(clientId);
        c.setTelephone(Long.valueOf("15478845565"));
        c.setEmail("215412568@qq.com");
        c.setArticleCode("0000");
        c.setPassword("123456");
        Date date = new Date();
        c.setCreateTime(date);
        iClientRepository.save(c);

        return "success";
    }

    */
/**
     * 查询数据
     * @return
     *//*


    @RequestMapping("/index")
    public String index(){

        Iterable<Client> it = iClientRepository.findAll();
        Iterator<Client> iterator = it.iterator();
        Client cc = iterator.next();
        System.out.println("======" + cc.getClientName());
        System.out.println("=======" + cc.getClientId() );
        return "zz";
    }

    */
/**
     * 需要用logstash进行数据迁移
     * 通过client的搜索btn来将id传入后台，再去数据库获得对应的client,再将这个client传到repository中保存
     * DEMO中是靠这个方法生成对象，再存进repository，我可以自己从数据拿
     * 疑问就是存进了repository，是用来干嘛的？？？
     *//*

    @RequestMapping("/save")
    public void save(){

        // 初始化Client client = null; 之后会报空指针错误。
       Client client = new Client();
       long clientId = 100;

       client.setClientId(clientId);
       client.setPassword("123456");
       client.setClientName("hl");
       client.setArticleCode("0000");
       client.setEmail("521624522@qq.com");
       client.setTelephone(Long.valueOf("15814636918"));
        System.out.println("添加用户成功");
       esService.save(client);
    }

    @RequestMapping("/find")
    public void find(){
        long clientId = 100;
        List<Client> clList = esService.getByClientId(clientId);

        System.out.println("通过ID查询");
        System.out.println(clList.get(0).getClientName());
        System.out.println("---------------");
    }

    @PostMapping("/searchByEs")
    public Object search(@RequestParam Param param){

        HashMap<String, Object> map = new HashMap<>();
        StopWatch watch = new StopWatch();
        watch.start();
        String type = param.getType();
        if(type.equalsIgnoreCase("mysql")){
            //mysql
        }else if(type.equalsIgnoreCase("es")){
            //elasticsearch
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            builder.should(QueryBuilders.matchPhraseQuery("title",  param.getKeyword()));
            builder.should(QueryBuilders.matchPhraseQuery("content",  param.getKeyword()));
            String s = builder.toString();
            System.out.println(s);
            Page<Client> search = (Page<Client>) iClientRepository.search(builder);
            List<Client> content = search.getContent();
            map.put("list", content);

        }else{
            return "i dont understand";
        }
        watch.stop();;


        long totalTime = watch.getTotalTimeMillis();
        map.put("duration", totalTime);

        return map;
    }
*/
/*
    post /client/_search
    {
        "query":{
        "bool": {
            "should": [
            {
                "match_phrase": {
                "telephone": "15814636918"
            }
            },
            {
                "match_phrase": {
                "name": "jjojo"
            }
            }
      ]
        }
    }
    }
*//*





    public static class Param{
        private String type;

        private String keyword;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }

}
*/
