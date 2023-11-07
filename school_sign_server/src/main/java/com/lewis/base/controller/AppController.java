package com.lewis.base.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lewis.base.config.Contance;
import com.lewis.base.entity.*;

import com.lewis.base.mapper.*;
import com.lewis.base.utils.AuthService;
import com.lewis.base.utils.Base64Util;
import com.lewis.base.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/sign")
public class AppController {
    @Autowired
    TercherMapper tercherMapper;
    @Autowired
    KechengMapper kechengMapper;
    @Autowired
    StucentMapper stucentMapper;
     @Autowired
     SrecordMapper srecordMapper;

    //所有的老师
    @RequestMapping(value = "/getAllTercher",method = RequestMethod.POST)
    public ComResult<List<Tercher>>  getAllTercher(@RequestParam(value = "page",defaultValue="1") int page,@RequestParam(value = "limit",defaultValue="10") int limit){
        List<Tercher> Terchers1 = tercherMapper.selectAll();
        PageHelper.startPage(page,limit);
        List<Tercher> Terchers = tercherMapper.selectAll();
        //将查询到的数据封装到PageInfo对象
        PageInfo<Tercher> pageInfo=new PageInfo(Terchers,limit);
        List<Tercher> list = pageInfo.getList();
        ComResult<List<Tercher>> result = new ComResult<>();
        result.setCode(0);
        result.setCount(Terchers1.size());
        result.setData(list);
        result.setMsg("success");
        return result;
    }
    //用户信息
    @RequestMapping(value = "/getTercherInfo",method = RequestMethod.POST)
    public ComResult<Tercher>  getTercherInfo(int id){
        Tercher Tercher = tercherMapper.selectByPrimaryKey(id);
        ComResult<Tercher> result = new ComResult<>();
        result.setCode(0);

        result.setData(Tercher);
        result.setMsg("success");
        return result;
    }

    //注册
  @RequestMapping(value = "/addTercher",method = RequestMethod.POST)
    public ComResult  addTercher(String name,String account,String pwd,String phone) throws Exception {
      Tercher Tercher1 = tercherMapper.check(account);
      ComResult result = new ComResult<>();

      if (Tercher1!=null){

          result.setMsg("账户已存在");

          result.setCode(0);

          return result;
      }else {
          Tercher Tercher = new Tercher();
          Tercher.setName(name);
          Tercher.setPhone(phone);
          Tercher.setAccount(account);
          Tercher.setPwd(pwd);

          int i = tercherMapper.insert(Tercher);


          if (i==1){
              result.setMsg("注册成功");
              result.setData(Tercher);
          }else {
              result.setMsg("faile");
          }
          result.setCode(0);

          return result;
      }


    }
    //注册
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ComResult  login(String account,String pwd) throws Exception {
        Tercher tercher = new Tercher();

        tercher.setAccount(account);
        tercher.setPwd(pwd);
        Tercher Tercher1 = tercherMapper.login(tercher);
        ComResult result = new ComResult<>();


            result.setMsg("登录成功");
            result.setData(Tercher1);
            result.setCode(0);

        return result;

    }
    //修改
    @RequestMapping(value = "/upTercher",method = RequestMethod.POST)
    public ComResult  upTercher(String name,String account,String pwd,String phone,Integer id) throws Exception {
        Tercher tercher = tercherMapper.selectByPrimaryKey(id);
        ComResult result = new ComResult<>();
        tercher.setName(name);
        tercher.setPhone(phone);
        tercher.setAccount(account);
        tercher.setPwd(pwd);
            int i = tercherMapper.updateByPrimaryKey(tercher);


            if (i==1){
                result.setMsg("修改成功");

            }else {
                result.setMsg("faile");
            }
            result.setCode(0);

            return result;



    }
    //删除老师
    @RequestMapping(value = "/delTercher",method = RequestMethod.POST)
    public ComResult  delTercher(Integer id){
        int i = tercherMapper.deleteByPrimaryKey(id);
        ComResult result = new ComResult<>();
          if (i==1){
              result.setMsg("success");
          }else {
              result.setMsg("faile");
          }
          result.setCode(0);
          return result;
      }


    //所有的学生
    @RequestMapping(value = "/getAllStudent",method = RequestMethod.POST)
    public ComResult<List<Stucent>>  getAllStudent(){
        List<Stucent> stucents = stucentMapper.selectAll();

        ComResult<List<Stucent>> result = new ComResult<>();
        result.setCode(0);
        result.setCount(stucents.size());
        result.setData(stucents);
        result.setMsg("success");
        return result;
    }
    //添加学生
    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    public ComResult  addStudent(String name,String account,String banji,String phone,String pic) throws Exception {

        ComResult result = new ComResult<>();


            Stucent stucent = new Stucent();
        stucent.setName(name);
        stucent.setPhone(phone);
        stucent.setAccount(account);
        stucent.setBanji(banji);
        stucent.setPic(pic);

            int i = stucentMapper.insert(stucent);


            if (i==1){
                result.setMsg("添加成功");
                String[] split = pic.split("/");
                String s = split[split.length - 1];
                byte[] bytes = FileUtil.readFileByBytes(Contance.IMAGE_PATH + s);
                String encode = Base64Util.encode(bytes);
                AuthService.add(stucent.getId()+"",encode,name);

                result.setData(stucent);
            }else {
                result.setMsg("faile");
            }
            result.setCode(0);

            return result;
        }


    //添加学生
    @RequestMapping(value = "/upStudent",method = RequestMethod.POST)
    public ComResult  upStudent(String name,String account,String banji,String phone,String pic,Integer id) throws Exception {
        Stucent stucent = stucentMapper.selectByPrimaryKey(id);
        ComResult result = new ComResult<>();
        stucent.setName(name);
        stucent.setPhone(phone);
        stucent.setAccount(account);
        stucent.setBanji(banji);
        stucent.setPic(pic);

        int i = stucentMapper.updateByPrimaryKey(stucent);
        if (i==1){
            result.setMsg("修改成功");
            result.setData(stucent);
            String[] split = pic.split("/");
            String s = split[split.length - 1];
            byte[] bytes = FileUtil.readFileByBytes(Contance.IMAGE_PATH + s);
            String encode = Base64Util.encode(bytes);
            AuthService.faceUpdate(stucent.getId()+"",encode,name);
        }else {
            result.setMsg("faile");
        }
        result.setCode(0);

        return result;
    }

    //删除学生
    @RequestMapping(value = "/delStudent",method = RequestMethod.POST)
    public ComResult  delStudent(Integer id){
        int i = stucentMapper.deleteByPrimaryKey(id);
        ComResult result = new ComResult<>();
        if (i==1){
            result.setMsg("success");
        }else {
            result.setMsg("faile");
        }
        result.setCode(0);
        return result;
    }


    //添加课程
    @RequestMapping(value = "/addKecheng",method = RequestMethod.POST)
    public ComResult  addKecheng(String name,String address,String time,Integer tid) throws Exception {

        ComResult result = new ComResult<>();


        Kecheng kecheng = new Kecheng();
        kecheng.setName(name);
        kecheng.setAddress(address);
        kecheng.setTime(time);
        kecheng.setTid(tid);

        int i = kechengMapper.insert(kecheng);


        if (i==1){
            result.setMsg("添加成功");
            result.setData(kecheng);
        }else {
            result.setMsg("faile");
        }
        result.setCode(0);

        return result;
    }

    //所有的课程
    @RequestMapping(value = "/getAllKecheng",method = RequestMethod.POST)
    public ComResult<List<Kecheng>>  getAllKecheng(){
        List<Kecheng> kechengs = kechengMapper.selectAll();
        ComResult<List<Kecheng>> result = new ComResult<>();
        result.setCode(0);
        result.setCount(kechengs.size());
        result.setData(kechengs);
        result.setMsg("success");
        return result;
    }
    //老师的发布课程
    @RequestMapping(value = "/getAllKechengByTid",method = RequestMethod.POST)
    public ComResult<List<Kecheng>>  getAllKechengByTid(Integer tid)throws Exception {
        List<Kecheng> kechengs = kechengMapper.selectAll();
        List<Kecheng> kechengList=new ArrayList<>();
        for (int i = 0; i < kechengs.size(); i++) {
            Kecheng kecheng = kechengs.get(i);
            if (kecheng.getTid()==tid){
                List<Stucent> stucents = stucentMapper.selectAll();
                ComResult<List<Srecord>> srecordS = getRecordByKid(kecheng.getId());
                SignNum signNum = new SignNum();
                signNum.total=stucents.size();
                signNum.oknum=srecordS.getData().size();
                signNum.nonum=signNum.total-signNum.oknum;
                kecheng.setSignNum(signNum);
                kechengList.add(kecheng);
            }
        }
        ComResult<List<Kecheng>> result = new ComResult<>();
        result.setCode(0);
        result.setCount(kechengList.size());
        result.setData(kechengList);
        result.setMsg("success");
        return result;
    }
    //所有的学生
    @RequestMapping(value = "/getAllStudentRecordBYKid",method = RequestMethod.POST)
    public ComResult<List<Stucent>>  getAllStudentRecordBYKid(Integer kid){
        List<Stucent> stucents = stucentMapper.selectAll();
        for (int i = 0; i < stucents.size(); i++) {
            Stucent stucent = stucents.get(i);
            Srecord srecord = new Srecord();
            srecord.setKid(kid);
            srecord.setSid(stucent.getId());

            Srecord  srecord1= srecordMapper.getRecord(srecord);
            stucent.setSrecord(srecord1);
        }
        ComResult<List<Stucent>> result = new ComResult<>();
        result.setCode(0);
        result.setCount(stucents.size());
        result.setData(stucents);
        result.setMsg("success");
        return result;
    }

    //所有的学生
    @RequestMapping(value = "/getAllStudentRecordBYKname",method = RequestMethod.POST)
    public ComResult<List<TjBean>>  getAllStudentRecordBYKname(String kname,Integer tid){
        List<TjBean> tjBeans=new ArrayList<>();
        List<Stucent> stucents = stucentMapper.selectAll();
        for (int i = 0; i < stucents.size(); i++) {
            Stucent stucent = stucents.get(i);
            TjBean tjBean = new TjBean();
            tjBean.setStucent(stucent);
            Kecheng kecheng=new Kecheng();
            kecheng.setTid(tid);
            kecheng.setName(kname);
            List<Kecheng> kcList = kechengMapper.getKcList(kecheng);
            if (kcList.size()>0){
                tjBean.setTotal(kcList.size());
                int zcnum=0;
                int cdnum=0;
                int wdnum=0;
                for (int j = 0; j < kcList.size(); j++) {
                    Kecheng kecheng1 = kcList.get(j);
                    Srecord srecord = new Srecord();
                    srecord.setKid(kecheng1.getId());
                    srecord.setSid(stucent.getId());
                    Srecord  srecord1= srecordMapper.getRecord(srecord);
                    if (srecord1==null){
                        wdnum+=1;
                    }else {
                        zcnum+=1;
                        Long rtime = Long.parseLong(getNumeric(srecord1.getTime()));
                        Long ktime = Long.parseLong(getNumeric(kecheng1.getTime()));
                        if (rtime>ktime){
                           cdnum+=1;
                        }
                    }
                }
                tjBean.setWdnum(wdnum);
                tjBean.setZcnum(zcnum);
                tjBean.setCdnum(cdnum);
            }else {
                tjBean.setTotal(0);
                tjBean.setWdnum(0);
                tjBean.setZcnum(0);
                tjBean.setCdnum(0);
            }
            tjBeans.add(tjBean);
        }
        ComResult<List<TjBean>> result = new ComResult<>();
        result.setCode(0);
        result.setCount(tjBeans.size());
        result.setData(tjBeans);
        result.setMsg("success");
        return result;
    }
    public  String getNumeric(String str) {
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    //添加签到
    @RequestMapping(value = "/addRecord",method = RequestMethod.POST)
    public ComResult  addRecord(Integer sid,String time,Integer kid) throws Exception {

        ComResult result = new ComResult<>();
        Srecord srecord = new Srecord();
       srecord.setTime(time);
       srecord.setSid(sid);
       srecord.setKid(kid);
        int i = srecordMapper.insert(srecord);
        if (i==1){
            result.setMsg("签到成功");
        }else {
            result.setMsg("faile");
        }
        result.setCode(0);

        return result;
    }


    //获取学生的签到记录
    @RequestMapping(value = "/getRecordBySid",method = RequestMethod.POST)
    public ComResult<List<Srecord>>  getRecordBySid(Integer sid) throws Exception {
        List<Srecord> srecords = srecordMapper.selectAll();
        List<Srecord> srecordList =new ArrayList<>();
        for (int i = 0; i < srecords.size(); i++) {
            Srecord srecord = srecords.get(i);
            if (srecord.getSid()==sid){
                srecord.setKecheng(kechengMapper.selectByPrimaryKey(srecord.getKid()));
                srecordList.add(srecord);
            }
        }
        ComResult result = new ComResult<>();

        result.setData(srecordList);
        result.setCode(0);

        return result;
    }

    //获取课程的签到记录
    @RequestMapping(value = "/getRecordByKid",method = RequestMethod.POST)
    public ComResult<List<Srecord>>  getRecordByKid(Integer kid) throws Exception {
        List<Srecord> srecords = srecordMapper.selectAll();
        List<Srecord> srecordList =new ArrayList<>();
        for (int i = 0; i < srecords.size(); i++) {
            Srecord srecord = srecords.get(i);
            if (srecord.getKid()==kid){
                srecord.setStucent(stucentMapper.selectByPrimaryKey(srecord.getSid()));
                srecordList.add(srecord);
            }
        }
        ComResult result = new ComResult<>();

        result.setData(srecordList);
        result.setCode(0);

        return result;
    }


    //人脸检测
    @RequestMapping(value = "/faceCheck",method = RequestMethod.POST)
    public ComResult<List<Srecord>>  faceCheck(String img) throws Exception {
        String[] split = img.split("/");
        String s = split[split.length - 1];
        byte[] bytes = FileUtil.readFileByBytes(Contance.IMAGE_PATH + s);
        String encode = Base64Util.encode(bytes);
        String ss = AuthService.faceSearch(encode);
        ComResult result = new ComResult<>();

        result.setData(ss);
        result.setCode(0);

        return result;
    }
    //人脸检测
    @RequestMapping(value = "/faceCheckBase64",method = RequestMethod.POST)
    public ComResult<List<Srecord>>  faceCheckBase64(String img) throws Exception {

        String ss = AuthService.faceSearch(img);
        FaceResult faceResult = JSON.parseObject(ss, FaceResult.class);
        ComResult result = new ComResult<>();

        result.setData(faceResult);
        result.setCode(0);

        return result;
    }
}
