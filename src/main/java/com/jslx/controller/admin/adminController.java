package com.jslx.controller.admin;/**
 * Created by chenjia on 2018/5/30.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jslx.model.User;
import com.jslx.model.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenjia
 * @create 2018-05-30 17:21
 * @desc
 **/
@Controller
@RequestMapping(value = "/admin")
public class adminController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 调到登录页面
     * @return
     */
    @RequestMapping(value = "/tologin")
    public String toLogin(){
        return "login/login";
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JSON login(String username, String password){
        //前台已经进行了判断.所以这里不用判断用户名,密码是不是空

        int status = 0;
        String result = "";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User selectUser = userMapper.selectByUsernameAndPassword(user);
        if (selectUser==null){
            status=1;
            result="用户名密码错误";
        }
        JSONObject json = new  JSONObject();
        json.put("status",status);
        json.put("result",result);
        return json;
    }



}
