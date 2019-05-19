package com.jk.controller;

import com.jk.model.LoginUser;
import com.jk.service.AntClientService;
import com.jk.util.Constant;
import com.jk.util.HttpClient;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("ant")
public class AntClientController {
    @Resource
    private StringRedisTemplate redis;

    @Resource
    private AntClientService  antClientService;
    /**
     * 韩慧鑫
     *进入登录页面
     */
    @RequestMapping("toLogin")
    public String toLogin(HttpServletRequest request, Model model, HttpSession session){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constant.cookieNamePwd)) {
                    String[] split = cookie.getValue().split(Constant.splitChart);
                    model.addAttribute("account", split[0]);
                    model.addAttribute("password", split[1]);
                }
            }
        }
        return "login";
    }
    /**
     * 韩慧鑫
     * 邮箱验证码
     */
    @RequestMapping("getEmailCode")
    @ResponseBody
    public String getEmailCode(String emailName){
        //1发送成功  2您已被拉入黑名单  3您是黑名单人员
        String status= antClientService.findStatus2(emailName);
        String flg=null;
        if("1".equals(status)){
        int newcode = (int)(Math.random()*899999)+100000;
            ValueOperations<String, String> strRedis = redis.opsForValue();
            ListOperations<String, String> listRedis = redis.opsForList();
            Long size = listRedis.size(emailName);
            if(size>=3){
                flg="2";
                antClientService.setBlacklist2(emailName);
            }else{
                HtmlEmail email=new HtmlEmail();
                email.setHostName("smtp.163.com");
                try {
                    email.setCharset("UTF-8");
                    email.addTo(emailName);
                    email.setFrom("15942087701@163.com","登录验证码");
                    email.setAuthentication("15942087701@163.com","han7758521");
                    email.setSubject("验证码");
                    email.setMsg(newcode+"");
                    email.send();
                    flg="1";
                    listRedis.leftPush(emailName,newcode+"");
                    redis.expire(emailName,60*60*24*1000 , TimeUnit.MILLISECONDS);//设置过期时间
                    strRedis.set(emailName+size,newcode+"");
                    redis.expire(emailName+size,5*60*1000, TimeUnit.MILLISECONDS);
                }catch (EmailException e) {
                    e.printStackTrace();
            }
            }
        }else{
            flg="3";
        }

        return flg;
    }

    /**
     * 韩慧鑫
     *手机验证码
     */
    @RequestMapping("getPhoneCode")
    @ResponseBody
    public String getPhoneCode(String phoneName){
        //1发送成功  2您已被拉入黑名单  3您是黑名单人员
        String flg="";
       String status= antClientService.findStatus(phoneName);
       if("1".equals(status)){
        int newcode = (int)(Math.random()*899999)+100000;
        ValueOperations<String, String> strRedis = redis.opsForValue();
        ListOperations<String, String> listRedis = redis.opsForList();
        Long size = listRedis.size(phoneName);
        if(size>=3){
            flg="2";
            antClientService.setBlacklist(phoneName);
        }else{
        listRedis.leftPush(phoneName,newcode+"");
        redis.expire(phoneName,60*60*24*1000 , TimeUnit.MILLISECONDS);//设置过期时间
        strRedis.set(phoneName+size,newcode+"");
        redis.expire(phoneName+size,5*60*1000, TimeUnit.MILLISECONDS);
        Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("mobile",phoneName);
            hashMap.put("tpl_id","156275");
            hashMap.put("tpl_value","%23code%23%3d"+newcode);
            hashMap.put("key","a70999110fa25d1a7be51036d57272ff");
        try {
            String s = HttpClient.sendGet("http://v.juhe.cn/sms/send", hashMap);
            flg="1";
        } catch (Exception e) {
            e.printStackTrace();
            flg="2";
        }
        }
       }else{
           flg="3";
       }

        return flg;
    }

    /**
     * 韩慧鑫
     * 清除cookie缓存
     * @param response
     */
    public void clearCookie(HttpServletResponse response){
        Cookie cookie=new Cookie(Constant.cookieNamePwd,"");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    /**
     * 韩慧鑫
     * 登录
     */
    @RequestMapping("login")
    @ResponseBody
    public Map login(LoginUser loginUser, HttpServletResponse response, HttpSession session){
        String type="1";
        ValueOperations<String, String> strRedis = redis.opsForValue();
        ListOperations<String, String> listRedis = redis.opsForList();
        Map<String, Object> map = new HashMap<>();
        if(loginUser.getPhoneCode()!=null&&loginUser.getPhoneCode().length()>0){
            Long size = listRedis.size(loginUser.getPhone());
            for(int i=0;i<size;i++){
                String s = strRedis.get(loginUser.getPhone()+i);
                        if(s!=null&&s.equals(loginUser.getPhoneCode())){
                            type="2";
                            break;
                        }
            }
        }else if(loginUser.getEmailCode()!=null&&loginUser.getEmailCode().length()>0){
            Long size = listRedis.size(loginUser.getEmail());
            for(int i=0;i<size;i++){
                String s = strRedis.get(loginUser.getEmail()+i);
                if(s!=null&&s.equals(loginUser.getEmailCode())){
                    type="2";
                    break;
                }
        }
        }else if(loginUser.getEmailCode()==null&&loginUser.getPhoneCode()==null){
            type="3";
        }

        if(type=="2"){
            LoginUser user=antClientService.getUserByNameAndPwd(loginUser);
            String flg=null;

            if (user != null) {
                if (loginUser.getGetIsChecked()!= null) {
                    Cookie cookie=new Cookie(Constant.cookieNamePwd,user.getAccount()+Constant.splitChart+user.getPassword());
                    cookie.setMaxAge(604801);
                    response.addCookie(cookie);
                    flg="1";
                    map.put("flg",flg);
                    map.put("id",user.getId());
                    map.put("position",user.getPosition());
                    map.put("name",user.getUsername());
                }else {
                    flg="1";
                    map.put("flg",flg);
                    map.put("id",user.getId());
                    map.put("position",user.getPosition());
                    map.put("name",user.getUsername());
                    clearCookie(response);
                }


            }else {
                clearCookie(response);
                flg="3";
                map.put("flg",flg);
            }
        }else if(type=="1"){
            map.put("flg",2);
        }else if(type=="3"){
            map.put("flg",4);
        }

        return map;
    }
    @RequestMapping("tomaeBackstage")
    public String maeBackstage(String name,Integer id,Model model){
        model.addAttribute("name",name);
        model.addAttribute("id",id);
        return "maeBackstage";
    }

    @RequestMapping("toaddUserPage")
    public String toaddUserPage(){
        return "adduser";
    }

    @RequestMapping("toaddant")
    public String toaddant(){
        return "addant";
    }
    @RequestMapping("addUserSendCode")
    @ResponseBody
    public String addUserSendCode(String emailName,HttpServletRequest request) {
        String flg = "1";
        int newcode = (int) (Math.random() * 899999) + 100000;
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.163.com");
        try {
            email.setCharset("UTF-8");
            email.addTo(emailName);
            email.setFrom("15942087701@163.com", "注册验证码");
            email.setAuthentication("15942087701@163.com", "han7758521");
            email.setSubject("验证码");
            email.setMsg(newcode + "");
            email.send();
            request.getSession().setAttribute(emailName,newcode+"");
        } catch (EmailException e) {
            e.printStackTrace();
            flg="2";
        }
        return flg;
    }

    @RequestMapping("addUser")
    @ResponseBody
    public String addUser(LoginUser loginUser,HttpServletRequest request){
        String flg=null;
        Object code = request.getSession().getAttribute(loginUser.getEmail());
        if(code!=null){
        if(loginUser.getEmailCode().equals(code)){
            flg=antClientService.addUser(loginUser);
        }
        }else{
            flg="2";
        }

        return flg;
    }
    @RequestMapping("tozucePage")
    public String tozucePage(){
        return "zucePage";
    }
    @RequestMapping("touserInfo")
    public String touserInfo(Integer id,Model model){
        LoginUser loginUser=antClientService.findUser(id);
        model.addAttribute("loginUser",loginUser);
        return "userInfo";
    }
    @RequestMapping("toupdatePassWord")
    public String toupdatePassWord(Integer id,Model model){
        LoginUser loginUser=antClientService.findUser(id);
        model.addAttribute("loginUser",loginUser);
        return "updatePassWord";
    }
    @RequestMapping("updatePassWord")
    @ResponseBody
    public String updatePassWord(Integer id,String password){
        antClientService.updatePassWord(id,password);
        return null;
    }
    @RequestMapping("tocommonline")
    public String tocommonline(Integer id,Model model){
        model.addAttribute("id",id);
        return "commonline";
    }
    @RequestMapping("findline")
    @ResponseBody
    public HashMap findline(Integer limit,Integer page,String name){
        return antClientService.findline(limit,page,name);
    }
    @RequestMapping("addLineUserC")
    @ResponseBody
    public void addLineUserC(Integer id,Integer lid,Integer userId){
          antClientService.addLineUserC(id,lid,userId);
    }
    @RequestMapping("findcommontable")
    @ResponseBody
    public HashMap findcommontable(Integer limit,Integer page){
        HashMap hashmap = antClientService.findcommontable(limit, page);
        return hashmap;
    }
    @RequestMapping("todizhiguanli")
    public String todizhiguanli(){
        return "dizhiguanli";
    }
    @RequestMapping("tomymsg")
    public String tomymsg(){
        return "mymsg";
    }
    @RequestMapping("toadministrator")
    public String toadministrator(){
        return "administrator";
    }
    @RequestMapping("toheimingdan")
    public String toheimingdan(){
        return "heimingdan";
    }
    @RequestMapping("findhei")
    @ResponseBody
    public HashMap findhei(Integer limit,Integer page){
        return antClientService.findhei(limit,page);
    }
    @RequestMapping("delheimingdan")
    @ResponseBody
    public String delheimingdan(Integer id){
         antClientService.delheimingdan(id);
         return null;
    }

    }


