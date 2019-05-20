package com.jk.controller;


import com.jk.model.City;
import com.jk.model.DeliveryList;

import com.jk.model.LoginUser;
import com.jk.model.Top;
import com.jk.model.CityModel;
import com.jk.model.GongSiModel;
import com.jk.model.ZhaoBiaoModel;
import com.jk.service.AntClientService;
import com.jk.util.Constant;
import com.jk.util.HttpClient;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import java.util.HashMap;
import java.util.List;

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

    @RequestMapping("toindex")
    public String toindex2(){
        return "sendPage";
    }

    @RequestMapping("getprovinces")
    @ResponseBody
    public HashMap<String,Object> getprovinces(){
        return antClientService.getprovinces();
    }


    /**
     * 跳转index页面
     */
    @RequestMapping("goindex")
    public String toindex(){
        return "index";
    }


    /**
     * 跳转index页面
     */
    @RequestMapping("sendPage")
    public String sendPage(){
        return "sendPage";
    }



    /**
     * 查询导航条内容
     */
    @RequestMapping("getTop")
    @ResponseBody
    public List<Top> getTop(){
        List<Top> list = antClientService.getTop();
        return list;
    }


    /**
     * 分站城市查询
     */
    @RequestMapping("findCity")
    @ResponseBody
    public List<City> findCity(){
        List<City> list = antClientService.findCity();
        return list;
    }






    /**
     * 承运商查询
     */
    @RequestMapping("findCompany")
    public ModelAndView findCompany(String name){

        Integer cid=antClientService.findCompany(name);

        ModelAndView mv=new ModelAndView();
        mv.addObject("cid",cid);
        mv.setViewName("company");
        return mv;
    }


    /**
     * 线路查询，返回id
     */
    @RequestMapping("findGsId")
    @ResponseBody
    public Long findGsId(String provenance,String origin){
        Long did=antClientService.findGsId(provenance,origin);
        System.out.print("sss");
        return did;
    }


    @RequestMapping("getcity")
    @ResponseBody
    public HashMap<String,Object> getcity(String cid,String cname){
        return antClientService.getcity(cid,cname);
    }
    @RequestMapping("getcounty")
    @ResponseBody
    public HashMap<String,Object> getcounty(String cid){
        return antClientService.getcounty(cid);
    }
    @RequestMapping("getoftencity")
    @ResponseBody
    public HashMap<String,Object> getoftencity(){
        return antClientService.getoftencity();
    }
    @RequestMapping("addsubmit")
    @ResponseBody
    public String addsubmit(DeliveryList deliveryList,Integer gid){
        deliveryList.setGongsiid(gid);
        antClientService.addsubmit(deliveryList);
        return "1";
    }
    @RequestMapping("toshow")
    public String toshow(){
        return "show";
    }
    @RequestMapping("refer")
    @ResponseBody
    public HashMap<String,Object> refer(Integer page,Integer limit){
        return antClientService.refer(page,limit);
    }

    @RequestMapping("page")
    public ModelAndView page(DeliveryList formdata){
        ModelAndView mv = new ModelAndView();
        mv.addObject("formdata",formdata);
        mv.setViewName("show");
        return mv;
    }
    @RequestMapping("toindexvalue")
    public ModelAndView toindexvalue(Integer gid){
        ModelAndView mv = new ModelAndView();
        mv.addObject("gid",gid);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("toline")
    public String totext(){
        return "line";
    }

    @RequestMapping("referline")
    @ResponseBody
    public HashMap<String,Object> referline(Integer page,Integer limit){
        return antClientService.referline(page,limit);
    }
    @RequestMapping("tocom")
    public ModelAndView tocom(Integer gid){
        ModelAndView mv = new ModelAndView();
        mv.addObject("gid",gid);
        mv.setViewName("companyone");
        return mv;
    }

    @RequestMapping("refertcom")
    @ResponseBody
    public HashMap<String,Object> refertcom(Integer gid){
        return antClientService.refertcom(gid);
    }

    @RequestMapping("toadd")
    public ModelAndView toadd(Integer xid){
        ModelAndView mv = new ModelAndView();
        mv.addObject("xid",xid);
        mv.setViewName("addpeople");
        return mv;
    }
    @RequestMapping("addinformation")
    @ResponseBody
    public String addinformation(Integer xid,DeliveryList deliveryList){
        antClientService.addinformation(xid,deliveryList);
        return "1";
    }

    //简单测试框架
    @RequestMapping("get")
    @ResponseBody
    public String get(){
        return antClientService.get();
    }
    //公司页面
    @RequestMapping("company")
    public String getCompany(){
        return "company";
    }

    //公司页面
    @RequestMapping("toindex222")
    public String index(){
        return "index";
    }

    //查询省的
    @RequestMapping("getCity")
    @ResponseBody
    public List<CityModel> getCity(){
        return antClientService.getCity();
    }

    //查询市的  报错
    @RequestMapping("getShi")
    @ResponseBody
    public List<CityModel> getShi(Integer id){
        List<CityModel> CityModel = antClientService.getShi(id);
        return CityModel;
    }

    //物流分页查询
    @RequestMapping("getAll")
    @ResponseBody
    public HashMap<String,Object> getAll(Integer page,Integer limit,Integer id){
        //@RequestBody GongSiModel gongSiModel,
        HashMap<String,Object> all = antClientService.getAll(page,limit,id);
        return all;
    }
    // 根据物流公司的id详情查询 getXiang
    @RequestMapping("getXiang")
    @ResponseBody
    public HashMap<String,Object> getXiang(Integer id){
        //@RequestBody GongSiModel gongSiModel,
        HashMap<String,Object> all = antClientService.getXiang(id);
        return all;
    }

    //转发页面
    @RequestMapping("tofindPage")
    public String findPage(Integer id, Model model){
        model.addAttribute("id",id);
        return "findpage";
    }

    //招标管理页面
    @RequestMapping("tozhaobiao")
    public String zhaobiao(){
        return "zhaobiao";
    }


    //个人中心  招标管理页面 getZhaoBiao
    @RequestMapping("getZhaoBiao")
    @ResponseBody
    public HashMap<String,Object> getZhaoBiao(Integer page,Integer limit,String biaoti){
        HashMap<String,Object> all = antClientService.getZhaoBiao(page,limit,biaoti);
        return all;
    }

    //转发到新增页面 addPage
    @RequestMapping("addPage")
    public ModelAndView addPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addPage");
        return mv;
    }

    //新增招标信息
    @RequestMapping("addzhaobiao")
    @ResponseBody
    public String addzhaobiao(ZhaoBiaoModel zhaoBiaoModel){
        antClientService.addzhaobiao(zhaoBiaoModel);
        return null;
    }


    /**
     * 刘瑞广
     */
    @RequestMapping("xinwen")
    public String querynews(){
        return "xinwen";
    }

    @RequestMapping("wuZhuan")
    public String wuZhuan(String msg){
        if (msg.equals("1")){
            return"zhuanxian";
        }
        if (msg.equals("2")){
            return"gongsi";
        }
        if (msg.equals("3")){
            return"hangye";
        }
        if (msg.equals("4")){
            return"huoyun";
        }
        if (msg.equals("5")){
            return"mingren";
        }
        if (msg.equals("6")){
            return"xinwenzhaobiao";
        }
        if (msg.equals("7")){
            return"lvmayi";
        }
        if (msg.equals("8")){
            return"redian";
        }
        if (msg.equals("9")){
            return"wuliuzhuanxian";
        }
        return "xinwen";
    }


}
