package com.javaee6.cgret.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javaee6.cgret.model.Cgret;
import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.model.OrderCart;
import com.javaee6.cgret.service.IClientLookShopService;
import com.javaee6.cgret.service.IMainClientService;
import com.javaee6.cgret.service.IShopCartService;
import com.javaee6.cgret.util.*;
import org.elasticsearch.action.get.GetResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author Administrator
 * 客户的主界面
 **/

@CrossOrigin
@Controller
@RequestMapping("/client")
public class MainClientController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private IMainClientService iMainClientService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private IClientLookShopService iClientLookShopService;

    @Resource
    private IShopCartService iShopCartService;

    @Resource
    private HttpServletResponse response;

    /**
     * 进入设置个人信息界面
     * @return
     */
    @RequestMapping("/personalSettings")
    public String setPersonInfo( ){

        Client client = (Client) request.getSession().getAttribute("clientInfo");

        Long clientId = client.getClientId();
        if(clientId == null){
            return "Login";
        }

        System.out.println(client.getClientName() + "开始个人设置");
        if(client.getClientName() == null) {
            return "Login";
        }
        return "Client_personalSettings";
    }

    @RequestMapping("/setDefaultAddress")
    @ResponseBody
    public String setDefaultAddress(String detailAddress, String province, String city, String district ){

        iMainClientService.insertDefaultAddress(request, detailAddress, province, city, district );

        return "保存成功";
    }


    /**
     * 上传头像
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadHead")
    @ResponseBody
    public int uploadHead() throws IOException {

        //默认设置图片大小为0，返回给前端当做判断
        int  imgSize = 0;

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("file");

        if(file.getSize() == 0){
            System.out.println("这个文件是空文件，没有上传到本地！");
        }else {
            //如果图片字节数不为0，就设置为1
            imgSize = 1;
            //图片上传成功后，将图片的地址写到数据库
            //保存图片的路径为E盘
            String filePath = "E:\\upload";
//            String filePath = "A:\\IDEA-workspace\\LIIMP\\src\\main\\webapp\\resources\\img\\caseImg";
            //获取原始图片的拓展名
            String originalFilename = file.getOriginalFilename();
            //新的文件名字
            String newFileName = UUID.randomUUID()+originalFilename;
            //封装上传文件位置的全路径
            File targetFile = new File(filePath,newFileName);
            //把本地文件上传到封装上传文件位置的全路径
            file.transferTo(targetFile);

//            caseReportService.uploadIMG(newFileName);
//            product.setPimage(newFileName);
        }

        return imgSize;
    }
    /**
     * 多此一举了，存在了session，就可以在客户端直接通过
     * var a = "${sessionScope.sessionKey}"的方式获取到session内数据了
     * 这个方法暂时先留着
     * 得到个人信息，然后去做相应的设置
     * @param session
     * @return
     */
    @RequestMapping("/getClientInfo")
    @ResponseBody
    public Object getPersonInfo(HttpSession session){
        String clientName = (String) session.getAttribute("clientName");
        Long clientId = (Long) session.getAttribute("clientId");
        String clientEmail = (String) session.getAttribute("clientEmail");
        Long clientTel = (Long) session.getAttribute("clientTel");

        System.out.println(clientEmail + clientName + clientId + clientTel);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client_id", clientId);
        jsonObject.put("client_name", clientName);
        jsonObject.put("client_email", clientEmail);
        jsonObject.put("client_tel",clientTel);

        return jsonObject;
    }


    /**
     * 测试查询的函数
     * @param productid
     * @return
     */
    @RequestMapping("/getCgret")
    @ResponseBody
    public Object get(int productid){
        if(StringUtil.isEmpty(productid+"")){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        GetResponse result = null;
        try {
            result = ElasticsearchUtil.client().prepareGet("cgret","doc",productid+"")
                    .get();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if(!result.isExists()){
            return ServerResponse.createByErrorMessage("获取信息失败");
        }

        System.out.println("1、" + result);
        System.out.println("2、" + result.getSource());

        Map<String, Object> map = result.getSource();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("eighteenCgret", map);

        for(String key : map.keySet()){
            System.out.println("key=" + key + "and value=" + map.get(key));
        }

        return jsonObject;
    }

    @RequestMapping("/select")
    public String select(ModelMap map) {

       return "select";

       /*
        int pagesize = 10;
        Long count = scrollResponse.getHits().getTotalHits();// 总数量
        int page = (int) Math.ceil((float) count / pagesize);// 总页数
        System.out.println("总页数" + page);

        // !首次查询的数据
        for (int i = 1; i <= page; i++) {
            System.out.println("=========================页数："+i+"==================");
            scrollResponse = client
                    .prepareSearchScroll(scrollResponse.getScrollId())//再次发送请求,并使用上次搜索结果的ScrollId
                    .setScroll(new TimeValue(1000)).execute()
                    .actionGet();
            SearchHits hits = scrollResponse.getHits();
            for(SearchHit searchHit : hits){
                System.out.println(searchHit.getSourceAsString());// 获取字符串格式打印
            }
        }
        Date end = new Date();
        System.out.println("耗时" + (end.getTime()-begin.getTime()));*/
    }

    // =====================================================查看店铺=====================================================

    /**
     * 没有搜索条件的search，根据es里面的数据顺序得出数据
     * scroll分页，不能跳页，只能一页页的
     * 这个用于用户首次进入产品列表时用来查看相关data
     * @param map
     * * @return
     */

    @CrossOrigin
    @RequestMapping("/lookShop")
    public String showShop(ModelMap map, HttpServletResponse response){

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Cache-Control", "no-cache");
        Client client = ClientThreadLocal.get();

        Long clientId = client.getClientId();
        if(clientId == null){
            return "Login";
        }
        // 这是从es中获取数据，有点卡
        iClientLookShopService.firstPageCgret(map);

        return "Client_lookShop";
    }

    /**
     * 结算界面
     * @return
     */
    @RequestMapping("/enterEndCalculate")
    public String enterEndCalculate(){

        Client client = ClientThreadLocal.get();

        Long clientId = client.getClientId();

        // 登录态失效则回到登录界面
        if(clientId == null){
            return "Login";
    }


        return "Client_endCalculate";
    }



    /**
     * 进入购物车
     * * @return
     */
    @RequestMapping("/enterOrderCart")
    public String enterOrderCart(ModelMap map) throws Exception {
        // 登录态失效则回到登录界面
        Client client = ClientThreadLocal.get();

        if(client.getClientId() == null){
            return "Login";
        }


        List<OrderCart> carts =  findCart(map);
        List<Cgret> cgretList = iShopCartService.findCgretByOrderCart(carts);

        for(OrderCart orderCart : carts){
            System.out.println("购物车内有" + orderCart.getProductId() + "," + orderCart.getCommodityNum() + "个,金额为" +
                    orderCart.getCommodityNum()*orderCart.getCommodityPrice() );
        }


        map.put("carts", carts);
        map.put("cgretList", cgretList);

        return "Client_orderCart";
    }


    /**
     * 添加商品到购物车
     * 每一次添加后，都要返回新的list到前端
     * 加入需要判断余量是否足够
     * 将商品加入redis缓存
     * @param productId
     * @param count
     * @return
     */
    @RequestMapping("/addItemTocart")
    @ResponseBody
    public void addItemToCart(Integer productId, Integer count,ModelMap map) throws Exception {


        Client client = ClientThreadLocal.get();
        // 登录态失效则回到登录界面
        Long clientId = client.getClientId();

        // 将商品信息保存在redis


       // get list of cart
       List<OrderCart> cartList = findCart(map);

       cartList = iShopCartService.addItemToCart(cartList, productId, count);

       // 未登录
       if(clientId == null){
           String cartListStr = JSON.toJSONString(cartList);

           CookieUtil.writeCookie(response, "cartList" , cartListStr);
       }else {
           // 登录，写回到redis
           iShopCartService.saveCartToRedis(clientId ,cartList);
       }
    }


    /**
     * 查询购物车信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/findCartList")
    public List<OrderCart> findCart(ModelMap map) throws Exception {

        Client client = (Client) request.getSession().getAttribute("clientInfo");
        Long clientId = client.getClientId();

        String cartListCookie = CookieUtil.getCookieValue(request, "cartList");

        if(StringUtil.isEmpty(cartListCookie)){
            cartListCookie = "[]";
        }
        List<OrderCart> cookie_cartList = JSON.parseArray(cartListCookie, OrderCart.class);


        // 未登录，取出cookie中的购物车数据
        if(clientId == null){
            return cookie_cartList;
        }else {

            List<OrderCart> redis_cartList = iShopCartService.findCartFromRedis(clientId);

            // 合并购物车
            if (cookie_cartList != null && redis_cartList != null){

                redis_cartList = iShopCartService.mergeCartList(cookie_cartList, redis_cartList);

                // 将最新处理的购物车数据存入redis
                iShopCartService.saveCartToRedis(clientId, redis_cartList);

                /*CookieUtil.removeCookie("cartList", request, response);*/
            }

            map.put("cartList", redis_cartList);
            return redis_cartList;
        }
    }

    @RequestMapping("/addressSettings")
    public String addressSettings(){
        return "Client_addressSettings";
    }

    @RequestMapping("/goChatRoom")
    public String sendMessage(HttpServletRequest request) throws IOException {

        Client client = ClientThreadLocal.get();
        Long clientId = client.getClientId();

        request.getSession().setAttribute("wsId", clientId);

        return "Client_talkRoom";
    }
}
