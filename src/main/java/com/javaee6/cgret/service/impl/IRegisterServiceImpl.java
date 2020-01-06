package com.javaee6.cgret.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.javaee6.cgret.dao.ClientMapper;
import com.javaee6.cgret.dao.ClientMapperX;
import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.model.ClientExample;
import com.javaee6.cgret.service.IRegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author Hyao
 */
@Service
public class IRegisterServiceImpl implements IRegisterService {

    @Resource
    public ClientMapper mapper;
    @Resource
    public ClientMapperX mapper2;


    /**
     * 手机号格式正则表达式
     */
    private static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";
    private static final Pattern PATTERN_REGEX_MOBILE_SIMPLE = Pattern.compile(REGEX_MOBILE_EXACT);
    
    /**
     * 邮箱格式正则表达式
     */
    private static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final Pattern PATTERN_REGEX_EMAIL = Pattern.compile(REGEX_EMAIL);


    /**
     * 检查邮箱格式和邮箱是否已存在
     * @param regEmail
     * @return
     */
    @Override
    public String checkEmail(String regEmail){

        ClientExample example = new ClientExample();
        ClientExample.Criteria criteriac = example.createCriteria();
        if(isEmail(regEmail)){
            criteriac.andEmailEqualTo(regEmail);
            if(mapper.selectByExample(example).size() == 0){
                return "邮箱正确";
            }
            return "邮箱已绑定其他账号";
        }
        return "邮箱格式不正确";
    }

    /**
     * 检查用户名是否重复且用户名格式是否正确
     * @param regName
     * @return
     */
    @Override
    public String checkUserName(String regName){

        ClientExample example = new ClientExample();
        ClientExample.Criteria criteriac = example.createCriteria();

        if(isName(regName)){

            criteriac.andClientNameEqualTo(regName);
            if(mapper.selectByExample(example).size() == 0) {
                return "用户名正确";
            }
            else{
                return "用户名已存在";
            }
        }
        return "用户名格式不正确";

    }

    /**
     * @param regTel
     * @return
     * 检查手机号是否重复且手机号格式是否正确（我觉得手机号应该可以重复，后期有待商榷）
     */
    @Override
    public String checkTel(String regTel){

        ClientExample example = new ClientExample();
        ClientExample.Criteria criteriac = example.createCriteria();
        if(isTel(regTel)){
            criteriac.andTelephoneEqualTo(Long.valueOf(regTel));
            if(mapper.selectByExample(example).size() == 0){
                return "手机号正确";
            }
            return "手机号已绑定其他账号";
        }
        return "手机号格式不正确";
    }

    /**
     * @param regPwd
     * @return
     * 密码格式验证,之后可以修改
     */
    @Override
    public boolean checkPwd(String regPwd){
        return (regPwd.length() >= 6 && regPwd.length() <= 20);
    }

    /**
     * @param regEmail
     * @param regName
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @throws AddressException
     * 利用STMP协议发送验证邮件
     */
    @Override
    public void sendEmail(String regEmail, String regName) throws AddressException,MessagingException, UnsupportedEncodingException {
        final Properties properties = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        properties.put("mail.smtp.auth", "true");
        // 此处填写SMTP服务器
        properties.put("mail.smtp.host", "smtp.qq.com");
        // QQ邮箱给出两个端口号，
        properties.put("mail.smtp.port", "587");
        // 发送邮件的账号
        properties.put("mail.user", "503810938@qq.com");
        // 16位STMP口令，在QQ邮箱设置中生成
        properties.put("mail.password", "xvwkonjwwzavbjfe");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = properties.getProperty("mail.user");
                String password = properties.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(properties, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                properties.getProperty("mail.user"), "烟草订货平台", "UTF-8");
        message.setFrom(form);

        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress(regEmail);
        message.setRecipient(Message.RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject("这是一封验证邮件");

        /*设置邮件的内容体*/
        String actiCode = createActiCode(regName);
        StringBuffer mail = new StringBuffer();
        mail.append("<h1>如果这不是您本人的操作，请忽略该邮件并切勿点击以下的激活链接!否则该邮箱将绑定他人账号!<h1><br>");
        mail.append("<a href="+"\""+"http://localhost:8080/cgret/checkCode?username="+regName+"&actiCode="+actiCode+"\""+">点击激活您的邮箱</a><br>");
        mail.append("在您激活您的邮箱之前，您的账号"+regName+"将处于未激活状态，无法使用！");
        message.setContent(mail.toString(), "text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);
    }

    /**
     * @param regName
     * @param regPwd
     * @param regEmail
     * @param regTel
     * 注册数据通过验证后，将注册数据写入数据库,并生成userID
     */
    @Override
    public void updateData(String regName, String regPwd, String regEmail, String regTel){
        System.out.println("写入数据库");
        Client Client = new Client();

        // 生成用户ID，以后要改
        Long ID;
        while (true){
            ID = new Random().nextLong();
            if(mapper.selectByPrimaryKey(ID) == null){
             break;
            }
        }
        Long numOfClient = mapper2.numOfClient();
        String temp = "";

        Client.setClientId(numOfClient+1);
        Client.setClientName(regName);
        Client.setPassword(regPwd);
        Client.setEmail(regEmail);
        if(!(regTel.equals(temp))){
            Client.setTelephone(Long.valueOf(regTel));
        }

        mapper.insertSelective(Client);
    }

    /**
     * @param regName
     * 将注册后的注册日期更新至数据库
     */
    @Override
    public void createDate(String regName){
        System.out.println("添加日期");
        Client client = new Client();
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        String currentTime = dateFormat.format(new Date());*/
        client.setCreateTime(new Date());
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();
        criteria.andClientNameEqualTo(regName);
        mapper.updateByExampleSelective(client,example);
    }

    /**
     * @param regName
     * @return
     * 生成四位激活码并存入数据库，返回激活码
     * 调用此方法前需调用createDateAndState方法，确保数据库已有相应数据
     */
    @Override
    public String createActiCode(String regName){
        Random random=new Random();
        StringBuilder actiCode=new StringBuilder();
        // 生成包含大小写字母和数字1-9的四位激活码,全0为已激活状态
        for(int i=0;i<4;i++){
            int number=random.nextInt(3);
            long result=0;
            switch(number){
                case 0:
                    result=Math.round(Math.random()*25+65);
                    actiCode.append(String.valueOf((char)result));
                    break;
                case 1:
                    result=Math.round(Math.random()*25+97);
                    actiCode.append(String.valueOf((char)result));
                    break;
                case 2:
                    actiCode.append(String.valueOf(new Random().nextInt(9)+1));
                    break;
                    default: break;
            }
        }
        Client client = new Client();
        client.setArticleCode(actiCode.toString());
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();
        criteria.andClientNameEqualTo(regName);
        mapper.updateByExampleSelective(client,example);
        return actiCode.toString();
    }

    /**
     * <p>检查actiCode激活</p>
     * @param userName
     * @param actiCode
     * @return
     */
    @Override
    public boolean checkCode(String userName, String actiCode){
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();
        criteria.andClientNameEqualTo(userName);
        List<Client> list = mapper.selectByExample(example);
        if(list.size() == 1){
            if(list.get(0).getArticleCode().equals(actiCode)){
                list.get(0).setArticleCode("0000");
                mapper.updateByExampleSelective(list.get(0), example);
                return true;
            }
        }

        return false;
    }

    /**
     * @param regName
     * @return
     * 检查激活码是否为全0，是的话将不再发送激活邮件
     */
    @Override
    public boolean checkActiCode(String regName){

        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();
        criteria.andClientNameEqualTo(regName);
        List<Client> list = mapper.selectByExample(example);
        String  activated = "0000";

        if(list.size() == 1){
            if(list.get(0).getArticleCode() == null){
                return true;
            } else if(list.get(0).getArticleCode().equals(activated)){
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * @param pattern
     * @param str
     * @return
     * 通用验证函数
     */
    private boolean isMatch(Pattern pattern, String str) {
        return StringUtil.isNotEmpty(str) && pattern.matcher(str).matches();
    }

    /**
     * @param regName
     * @return
     * 用户名格式验证（不能为邮箱格式）
     */
    @Override
    public boolean isName(String regName){
        return (!isMatch(PATTERN_REGEX_EMAIL, regName));
    }

    /**
     * @param regTel
     * @return
     * 手机号格式验证
     */
    @Override
    public boolean isTel(String regTel){
        return isMatch(PATTERN_REGEX_MOBILE_SIMPLE, regTel);
    }

    /**
     * @param regEmail
     * @return
     * 邮箱格式验证
     */
    @Override
    public boolean isEmail(String regEmail){
        return isMatch(PATTERN_REGEX_EMAIL, regEmail);
    }

    @Override
    public String getEmail(){
        return "12345678@qq.com";
    }
}
