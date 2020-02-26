package com.javaee6.cgret.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaee6.cgret.model.*;
import com.javaee6.cgret.service.IGoodsBrandService;
import com.javaee6.cgret.service.IReleaseGoodService;
import com.javaee6.cgret.util.ClientThreadLocal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 **/

@RequestMapping("/shop")
@Controller
public class MainShopController {

    /**
     * 全局变量，productId，用于创建文件夹等操作
     */
    private int thisProductId = 0;

    /**
     * 全局对象
     */
    Cgret cgret = new Cgret();

    /**
     * 对象的imageUrl
     */
    String imageUrl = "";

    /**
     * 保存上传过来的照片
     */
    MultipartFile[] allFiles = new MultipartFile[50];

    class multipleUpFrameThread implements Runnable{

        @Resource
        private IReleaseGoodService iReleaseGoodService;

        private int[] productid;

        public multipleUpFrameThread(IReleaseGoodService iReleaseGoodService, int[] productid){
            this.iReleaseGoodService = iReleaseGoodService;
            this.productid = productid;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            iReleaseGoodService.multipleUpFrame(productid);
        }
    }

    @Resource
    private IGoodsBrandService goodsBrandService;

    @Resource
    private IReleaseGoodService iReleaseGoodService;

    /**
     * 自动添加品牌数据
     * @return
     */
    @RequestMapping("/autoAddBrand")
    public String autoAddBrand(){

        goodsBrandService.autoAddBrand();
        System.out.println("添加数据成功");
        return "autoaddbrand";
    }

    @RequestMapping("/select")
    public String test(){
        return "select";
    }
    // ================================================================== 交易管理===================================================================

    /**
     * 已卖出的宝贝
     * @return
     */
    public String soldGood(){
        return null;
    }

    /**
     * 评价管理
     * @return
     */
    public String commentManagement(){
        return null;
    }

    // ================================================================== 宝贝管理===================================================================

    /**
     * 发布宝贝
     * 先发布主要的信息
     * 未完成功能：单点登录的session检测、重复发布同一样的bug错误提示、照片的update(需要将照片根据productName来创建文件夹),先都弄一张默认图片吧
     * @return
     */
    @RequestMapping("/releaseGood")
    public String releaseGood(HttpServletRequest request){

        Client client = (Client) request.getSession().getAttribute("clientInfo");
        Long clientId = client.getClientId();
        if(clientId == null){
            return "Login";
        }
        return "Shop_releaseGood";
    }

    @RequestMapping(value = "/writeFirstCookie", method = RequestMethod.POST)
    @ResponseBody
    public String writeFirstCookie(String cgretType, String jiaoyou, String brand, String brandDetail, int leftNum,
                                   float price , HttpServletResponse response) throws IOException{
        System.out.println("==============");
        System.out.println(cgretType+jiaoyou+brand+brandDetail+leftNum);

        addCookie(response, "cgretType", URLEncoder.encode(cgretType, "utf-8"));
        addCookie(response, "jiaoyou", URLEncoder.encode(jiaoyou, "utf-8"));
        addCookie(response, "brand", URLEncoder.encode(brand, "utf-8"));
        addCookie(response, "brandDetail", URLEncoder.encode(brandDetail, "utf-8"));
        addCookie(response, "leftNum", URLEncoder.encode(Integer.toString(leftNum), "utf-8"));
        addCookie(response, "price", URLEncoder.encode(String.valueOf(price), "utf-8"));

        return "success";
    }

    public static void addCookie(HttpServletResponse response, String name, String value){
        Cookie cookie = new Cookie(name, value);
        response.addCookie(cookie);
    }

    public static void addCookie(HttpServletResponse response, String name, int value){
        Cookie cookie = new Cookie(name, Integer.toString(value));
        response.addCookie(cookie);
    }

    /**
     * 上传文件以商品信息来创建文件夹，数据库保存的是文件的路径
     * 上传图片时候，因为要获得productId,所以在这一步就要读取cookie，并且生成一个cgret对象出来
     * 这样才可以获得cgret对象的productId属性
     *
     * @param request
     * @param files
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(HttpServletRequest request,
                            @RequestParam("file")MultipartFile[] files) throws IOException{
        System.out.println("uploadIMG ing");

        allFiles = null;
        allFiles = files;

        return "success";

    }

    /**
     * 这个多图片处理
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/submitAllInfo")
    @ResponseBody
    public String submitAllInfo(HttpServletRequest request) throws IOException{

        // ========================================================生成数据库需要的对象，以及一些图片上传需要的数据=======================================
        Cookie[] cookies = request.getCookies();

        int i = 0;
        String productName = "";
        String brandName = "";
        int brandId = 0;

        for(Cookie cookie : cookies){
            System.out.println(cookie.getName() + "," + URLDecoder.decode(cookie.getValue(), "utf-8") );

            // 插入brand，如果此brand不存在，则更新brand表， 再赋值给cgret
            if(cookie.getName().equalsIgnoreCase("brand") && iReleaseGoodService.numOfBrand(URLDecoder.decode(cookie.getValue(), "utf-8")) == 0){
                // 插入新的brand进入到brand表中
                iReleaseGoodService.insertBrandToTable(URLDecoder.decode(cookie.getValue(), "utf-8"));
                Brand brand = iReleaseGoodService.selectByBrandName(URLDecoder.decode(cookie.getValue(), "utf-8"));
                cgret.setBrandid(brand.getBrandid());

                // productName准备用brand+brandDetail拼接而成
                productName = productName + URLDecoder.decode(cookie.getValue(), "utf-8");
                brandId = brand.getBrandid();
                brandName = brand.getBrandname();

                // brand存在
            }else if (iReleaseGoodService.numOfBrand(URLDecoder.decode(cookie.getValue(), "utf-8")) !=0 ){
                // 如果brand存在，则直接赋值给cgret
                Brand brand = iReleaseGoodService.selectByBrandName(URLDecoder.decode(cookie.getValue(), "utf-8"));
                cgret.setBrandid(brand.getBrandid());

                // productName准备用brand+brandDetail拼接而成
                productName = productName + URLDecoder.decode(cookie.getValue(), "utf-8");
                brandId = brand.getBrandid();
                brandName = brand.getBrandname();
            }

            // 插入type
            if(cookie.getName().equalsIgnoreCase("cgretType")){
                Cgrettype cgrettype = iReleaseGoodService.selectByTypeName(URLDecoder.decode(cookie.getValue(), "utf-8"));

                // 数据库应该用int的，设置错了，不过问题不大
                cgret.setTypeid(Integer.toString(cgrettype.getTypeid()));
            }

            // 插入price
            if(cookie.getName().equalsIgnoreCase("price")){
                cgret.setPrice(Float.parseFloat(URLDecoder.decode(cookie.getValue(), "utf-8") ));
            }

            // 插入jiaoyou
            if(cookie.getName().equalsIgnoreCase("jiaoyou")){
                Jiayou jiayou = iReleaseGoodService.selectByJiaoName(URLDecoder.decode(cookie.getValue(), "utf-8"));
                cgret.setJiaoyouid(jiayou.getJiayouid());
            }

            // 插入库存
            if(cookie.getName().equalsIgnoreCase("leftnum")){
                cgret.setLeftnum(Integer.parseInt(URLDecoder.decode(cookie.getValue(), "utf-8")));
            }

            // 不存在则先,插入brandDetail,并且返回一个ID
            if(cookie.getName().equalsIgnoreCase("branddetail") &&
                    iReleaseGoodService.numOfBrandDetailAndBrand(URLDecoder.decode(cookie.getValue(), "utf-8"), brandId) == 0){
                int id =  iReleaseGoodService.insertWithBrandDetailAndBrand(URLDecoder.decode(cookie.getValue(), "utf-8"), brandId);
                productName = productName + URLDecoder.decode(cookie.getValue(), "utf-8");
                cgret.setBranddetailid(id);
                cgret.setProductname(productName);
            }

        }
        System.out.println("cookie有" + i +"个");
        String barCode =  cgret.getBrandid()+"A"+ cgret.getBranddetailid() +"A"+ cgret.getJiaoyouid() +"A"+ cgret.getTypeid();
        cgret.setBarcode(barCode);

        int cgretNum = iReleaseGoodService.maxIdOfCgert();

        cgret.setProductid(iReleaseGoodService.maxIdOfCgert()+1);

        // 正在处理的商品的Id就是thisProductId
        thisProductId = cgret.getProductid();

        // 默认属性
        cgret.setStatus("未上架");
        cgret.setVersion(1);

        // Set update_time for logstash
        Date now = new Date();
        cgret.setUpdateTime(now);

        // 非删除状态,this is setted for logstash.logstash is not convenient for delete manipulation,so i build a colume
        // record the delete status.
        cgret.setDeletestatus(0);

        // ========================================================处理图片以及上传，生成需要的数据库数据=======================================
        // 根据商品ID，创建商品专属文件夹，并把图片上传到该文件夹，并将图片路径上传到数据库
        // ***突然觉得只需要upload + productId + 图片名字就足够了
        // imageUrl用于文件夹的创建，而simpleImageUrl则是用于传入数据库的数据
        String filePath = request.getServletContext().getRealPath("/") + "upload\\" +thisProductId +'\\';
        imageUrl  = filePath;

        String simpleFilePath = "upload\\" + thisProductId + "\\";

        System.out.println(filePath);
        System.out.println(simpleFilePath);

        File tmpFile = new File(filePath);
        if(!tmpFile.exists()){
            tmpFile.mkdir();
        }

        // imageUrl的组成是 文件夹的路径 + 图片名称 + '|'
        // fileNum记录图片的数量，规定图片最多三张，不够三张则用一张默认路径的默认图片补齐，这个操作是方便编程
        int fileNum = 0;

        String imageUrl = "";
        String simpleImageUrl = "";
        for(MultipartFile file:allFiles){
            fileNum++;
            System.out.println("有收到文件");
            file.transferTo(new File(filePath+file.getOriginalFilename()));
            imageUrl = imageUrl + filePath + file.getOriginalFilename() + "|";
            simpleImageUrl = simpleImageUrl + simpleFilePath + file.getOriginalFilename() + "|";
        }

        // ========================================================将新建对象以及图片URL存进数据库=======================================
        iReleaseGoodService.insertWithCgret(cgret);
//        iReleaseGoodService.insertImageUrlWithProductId(thisProductId, simpleImageUrl, fileNum);
        iReleaseGoodService.insertSimpleUrlWithProductId(thisProductId, simpleImageUrl, fileNum);
        System.out.println("发布成功");
        return "发布成功";
    }


    /**
     * 查看宝贝状态
     * （不是重要需求）
     * @return
     */
    @RequestMapping("/reviewGoodStatus")
    public String reviewGoodStatus(ModelMap map, HttpServletRequest request){

        Client client = (Client) request.getSession().getAttribute("clientInfo");

        if(client.getClientId() == null){
            return "Login";
        }

        iReleaseGoodService.showGoodStatus(map);
        return "Shop_reviewGoodStatus";
    }

    /**
     * 上架商品
     * @param productId
     */
    @RequestMapping("/upFrame")
    @ResponseBody
    public String upFrame(int productId){
        iReleaseGoodService.upFrame(productId);
        return "success";
    }

    /**
     * 下架商品
     * @param productId
     */
    @RequestMapping("/downFrame")
    @ResponseBody
    public String downFrame(int productId){
        iReleaseGoodService.downFrame(productId);
        System.out.println("successssss");
        return "success";
    }

    /**
     * 批量上架
     * 采取创建两个线程的方式，每个线程处理一半的商品
     * @return
     */
    @RequestMapping("/multipleUpFrame")
    @ResponseBody
    public String multipleUpFrame(@RequestParam("chk")int[] productId){
        for(int i = 0; i < productId.length; i++){
            System.out.println(productId[i]);
        }

        iReleaseGoodService.multipleUpFrame( productId);

        return "success";
    }

    /**
     * 批量下架
     * @return
     */
    @RequestMapping("/multipleDownFrame")
    @ResponseBody
    public String multipleDownFrame(@RequestParam("chk")int[] productId){
        for(int i = 0; i < productId.length; i++){
            System.out.println(productId[i]);
        }

        iReleaseGoodService.multipleDownFrame( productId);

        return "success";
    }

    @RequestMapping("/updateGoodStatus")
    @ResponseBody
    public Object updateGoodStatus(){

        List<Cgret> cgretList = iReleaseGoodService.updateGoodStatus();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",cgretList);
        System.out.println(jsonObject);
        return jsonObject;
    }

    /**
     * 出售中的宝贝
     * (也就是发布了的宝贝，弄个表格罗列起来就可以了，做个筛选，这里就要用到elasticSearch引擎了吧)
     * @return
     */
    public String SellingGood(HttpServletRequest request){
        Client client = (Client) request.getSession().getAttribute("clientInfo");


        if(client.getClientId() == null){
            return "Login";
        }
        return "sellingGood";
    }

    /**
     * 仓库中的宝贝
     * （这里？？必须要有，也是一个增删查改）,先不做这个，做图片
     * @return
     */
    @RequestMapping
    public String GoodInRepository(HttpServletRequest request){

        Client client = (Client) request.getSession().getAttribute("clientInfo");

        if(client.getClientId() == null){
            return "Login";
        }
        return "GoodInRepository";
    }

    // ================================================================== 物流管理===================================================================

    /**
     * 发货
     * @return
     */
    public String sendGood(){
        return null;
    }

    /**
     * 寄快递
     * @return
     */
    public String sendDelivery(){
        return null;
    }

    /**
     * 物流工具
     * @return
     */
    public String deliveryTool(){
        return null;
    }

    /**
     * 物流服务
     * @return
     */
    public String deliveryService(){
        return null;
    }

    // ================================================================== 店铺管理===================================================================

    /**
     * 查看店铺
     * @return
     */
    @RequestMapping("/lookShop")
    public String checkShop(){
        return "Shop_lookShop";
    }

    /**
     * 图片空间
     * （暂时不理会）
     * @return
     */
    public String imageZone(){
        return null;
    }

    /**
     * 店铺基本设置
     * （基本设置）
     * @return
     */
    public String baseSetting(){
        return null;
    }

    // ================================================================== 客户服务 ===================================================================

    /**
     * 退款管理
     * @return
     */
    public String refundManagement(){
        return null;
    }

    /**
     * 投诉管理
     * @return
     */
    public String reportManagement(){
        return null;
    }

    // ================================================================== 支付通商家中心 ===================================================================

    public String payCentral(){
        return null;
    }

    // ===============================================================进入商家的聊天室============================================================
    @RequestMapping("/goChatRoom")
    public String sendMessage() throws IOException {

        Client client = ClientThreadLocal.get();

        return "Shop_talkRoom";
    }
}
