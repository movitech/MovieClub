package com.thinkgem.jeesite.modules.sys.rest;

import com.thinkgem.jeesite.modules.sys.entity.Login;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.exception.AuthenException;
import com.thinkgem.jeesite.modules.sys.exception.PasswordResetException;
import com.thinkgem.jeesite.modules.sys.exception.UnknowAccountException;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.HResult;
import com.thinkgem.jeesite.modules.sys.utils.MyHttpClientUtils;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Controller
@RequestMapping(value = "${restPath}/sys/rest")
public class LoginRest {
    @Autowired
    private SystemService systemService;

    public static final String loginUrl = "http://cinelibre.ph/api/login_user/";

    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    public synchronized
    @ResponseBody
    String loginVerify(@RequestBody Login login){
        String email = null;
        String content = null;
        JSONObject obj = null;
        User user = null;
        try {
            email = login.getEmail_ID();
            String password = login.getUser_password();
            content = MyHttpClientUtils.doPost(loginUrl+"email_ID/"+email+"/user_password/"+password,null,"UTF-8");
            user = systemService.login(email, password);

        } catch (PasswordResetException e) {
            e.printStackTrace();
        } catch (AuthenException e) {
            e.printStackTrace();
        } catch (UnknowAccountException e) {
            e.printStackTrace();
        }
        return content;
    }

    /*@RequestMapping(value = "/loginVerify2", method = RequestMethod.POST)
    public synchronized
    @ResponseBody
    String login(){
        String email = "707408204@qq.com";
        String password = "012345";
        String content = MyHttpClientUtils.doPost(loginUrl+"email_ID/"+URLEncoder.encode(email)+"/user_password/"+password,null,"UTF-8");
        JSONObject obj = JSONObject.fromObject(content);

        return content;
    }

    public static void main(String[] args)
    {
        String content = MyHttpClientUtils.doPost("http://localhost:8080/movieclub/r/sys/rest/loginVerify2",null,"UTF-8");
        System.out.println(content);
    }*/

}
