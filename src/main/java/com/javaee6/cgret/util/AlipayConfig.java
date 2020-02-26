package com.javaee6.cgret.util;

/**
 * @author Administrator
 **/

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101900720689";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key =
            "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIzpdw+f48mMLPadCltk2LjyMJT/VkwlRC0+tNk/NYGgedRnwUSgGur5J3cB1pt+9Dh0mzjalIaBaxXgiIB7/+aXcP+1kmSHypBO2Nq0SBwl3y5S4Ss8fEHshIW44wI/9n/FQw8OdpbYsDjN0eftPqXqovOCR7JyWhdVMCihgF0vAgMBAAECgYBlN/7SNmQFjq9tpnieFd6nWzESlieDIvHKHlUCCKmCp6dbr/vgM7GiP77deXCS2arMMtadDEXMKIKngFYNFQVHptg3o9ly3oO/6dFqiWW5Fdk8yTZvfgNC4aA3ep6rw17RYVcrYuX3nsjlCqKrwP/cBUCFP6AbclIU8VbUubs84QJBANpBnrsvcGXrf+6aSVkBcv9N4HhUlUtQheLtIlWgfmFKYtPWRaDNC9JoevpoQ1uJEeLzCL1q/Purlxib6YgMQY0CQQClR7/WpFjo6QNhpqvV02IRN1hTKyeGs4ccIQd92dTMUkBPoVBpELMLUuxKcQaK0OSuPWD9mxwTnUyOkFd1a+SrAkB16I9lFnrFRNWHRGitod/LVvoWZ7ODzYuXDX7H/X1o9/Ot94sEFOH3GKSfVOZWtBUqoxssoXjsSUXGcKxX6C8dAkBnBCF+F1GTWijRZKEV1Xr6YBSdQfiQ7LT5w1x4BJ2t/FY5S8JiuhXeqQC+JVR+kVT3hSo+577IOvLMqQraFTWhAkAsOJ9O5I/DtoGBOGjwa2tmfZHEjCeKsr7ddMJ8oeyV5FKRN30D7+IWdigytnaSZlTfmUxIGh1sgbm9k0KsQqi8";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCM6XcPn+PJjCz2nQpbZNi48jCU/1ZMJUQtPrTZPzWBoHnUZ8FEoBrq+Sd3AdabfvQ4dJs42pSGgWsV4IiAe//ml3D/tZJkh8qQTtjatEgcJd8uUuErPHxB7ISFuOMCP/Z/xUMPDnaW2LA4zdHn7T6l6qLzgkeycloXVTAooYBdLwIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http:\\/\\/j4e2ic.natappfree.cc\\/cgret\\/Order\\/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = " http:\\/\\/j4e2ic.natappfree.cc\\/cgret\\/Order\\/return_url";

    // 签名方式
    public static String sign_type = "RSA";

    // 字符编码格式
    public static String charset = "utf-8";

    //沙箱环境网关为： https://openapi.alipaydev.com/gateway.do
    //正式环境网关为：https://openapi.alipay.com/gateway.do
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}
