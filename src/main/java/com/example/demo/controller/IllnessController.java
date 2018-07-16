package com.example.demo.controller;


import com.example.demo.annotation.DemoLog;
import com.example.demo.common.LayUiTableRep;
import com.example.demo.common.WebApiResponse;
import com.example.demo.constant.LogEnum;
import com.example.demo.model.Illness;
import com.example.demo.service.IllnessService;
import com.example.demo.serviceimpl.RedisServiceImpl;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by shichong on 2018/6/25.
 */
@RestController
@RequestMapping("/illness")
public class IllnessController {

    @Autowired
    IllnessService illnessService;





    /**
     * 分页获取illness，返回数据无封装
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    @DemoLog(value = "分页获取病症无封装",module = LogEnum.ILLNESS_INFO,operate = LogEnum.SELECT)
    @GetMapping(value = "/page",produces = "application/json;charset=utf-8")
    public List<Illness> findIllnessInfoPage(@RequestParam(value = "page") int pageNo,
                                             @RequestParam(value = "limit") int pageSize)throws Exception{
        /**
         * 分页获取
         */
       return illnessService.getList(pageNo,pageSize);

    }

    /**
     * 分页获取Illness，返回数据封装
     * @param pageNo
     * @param pageSize
     * @return
     */
    @DemoLog(value = "分页获取病症",module = LogEnum.ILLNESS_INFO,operate = LogEnum.SELECT)
    @GetMapping(value = "/getlist",produces = "application/json;charset=utf-8")
    public LayUiTableRep<Illness> getList(@RequestParam(value = "p")Integer pageNo,
                                          @RequestParam(value = "l")Integer pageSize
    ){
        /**
         * 分页获取
         */

        try {
            Page<Illness> page =illnessService.getListAll(pageNo,pageSize);
            List<Illness> IllnessInfo =page.getResult();
            Integer count =(int) page.getTotal();
            if (IllnessInfo.size()==0){
                return LayUiTableRep.error();
            }
            return LayUiTableRep.success(count,IllnessInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LayUiTableRep.error();
    }

    /**
     * 添加病症
     * @param illness
     * @return
     */
    @DemoLog(value = "添加一个病症",module = LogEnum.ILLNESS_INFO,operate = LogEnum.SAVE)
    @PostMapping
    @ResponseBody
    public WebApiResponse<?> saveIllnessInfo(@RequestBody Illness illness){

        System.out.println(illness.toString());
        try {
            String illnessName=illness.getIllnessName();
            boolean isExist = illnessService.isExistByIllnessName(illnessName);
            if (isExist){
                return WebApiResponse.error("1209","病症已存在，请重新输入！");
            }else {
             illnessService.saveIllnessInfo(illness);

                return WebApiResponse.success(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebApiResponse.error("1201","保存病症有误！");
    }

    /**
     * 更新病症
     * @param id
     * @param illness
     * @return
     */
    @PutMapping(value = "/{id}")
    public WebApiResponse<?> updateIllnessInfo(@PathVariable("id")Integer id, @RequestBody Illness illness){


        try {
            illnessService.updateIllnessInfo(illness);
            return WebApiResponse.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebApiResponse.error("1203","更新有误");
    }


    /**
     * 批量删除
     * 传入List的json 格式为 [1,2,3]
     * @param ids
     * @return
     */
    @DeleteMapping
    public WebApiResponse<?> delIllnessInfos(@RequestBody List<Integer> ids){
       // System.out.println(ids.toString());
        if (ids.size()==0){
            return WebApiResponse.error("1004","至少选中一个病症");
        }
        illnessService.deleteIllnessInfos(ids);
        return WebApiResponse.success(null);
    }


    /**
     * 删除一个病症
     * @param id
     * @return
     */
    @DemoLog(value = "删除一个病症",module = LogEnum.ILLNESS_INFO,operate = LogEnum.DELETE)
    @DeleteMapping(value = "/{id}")
    public WebApiResponse<?> delIllnessInfo(@PathVariable("id")Integer id){

        try {
            illnessService.deleteIllnessInfo(id);
            return WebApiResponse.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WebApiResponse.error("1201","删除有误！");
    }


    @DemoLog(value = "查询一个病症",module = LogEnum.ILLNESS_INFO,operate = LogEnum.SELECT)
    @GetMapping(value = "/selectOne",produces = "application/json;charset=utf-8")
    public Illness selectOneById(@RequestParam("illnessId")Integer illnessId){
        Illness illness =illnessService.findIllnessById(illnessId);
        return illness;
    }


}
