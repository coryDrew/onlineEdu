package com.atguigu.msmservice.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.aliyun.tea.*;
import com.aliyun.dysmsapi20170525.*;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
import com.aliyun.teautil.*;
import com.aliyun.teautil.models.*;

@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        try {
            Config config = new Config()
                    // 您的AccessKey ID
                    .setAccessKeyId("LTAI5t8t29tbJ3ogYSBntpyR")
                    // 您的AccessKey Secret
                    .setAccessKeySecret("HAs0uO55V4b26brAjmJye4nwjf7ulH");
            // 访问的域名
            config.endpoint = "dysmsapi.aliyuncs.com";
            Client client = new Client(config);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setSignName("阿里云短信测试")
                    .setTemplateCode("SMS_154950909")
                    .setPhoneNumbers("18205022799")
                    .setTemplateParam(JSONObject.toJSONString(param));
            RuntimeOptions runtime = new RuntimeOptions();
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, runtime);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
