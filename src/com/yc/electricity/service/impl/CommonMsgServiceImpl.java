package com.yc.electricity.service.impl;

import com.yc.electricity.dao.ICommonMsgContentDao;
import com.yc.electricity.dao.ICommonMsgDao;
import com.yc.electricity.domain.CommonMsg;
import com.yc.electricity.domain.CommonMsgContent;
import com.yc.electricity.service.ICommonMsgService;
import com.yc.electricity.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuchuan on 3/19/16.
 */
@Service(value = ICommonMsgService.SERVICE_NAME)
@Transactional(readOnly = true)
public class CommonMsgServiceImpl implements ICommonMsgService{

    @Resource(name = ICommonMsgDao.DAO_NAME)
    private ICommonMsgDao dao;

    @Resource(name = ICommonMsgContentDao.DAO_NAME)
    private ICommonMsgContentDao contentDao;

//    @Override
//    public CommonMsg findCommonMsg() {
//        List<CommonMsg> list = dao.findListByConditionNoPage(null, null, null);
//        if (list != null && !list.isEmpty()) {
//            return list.get(0);
//        }
//        return null;
//    }
//
//    @Override
//    @Transactional(readOnly = false)
//    public void saveCommonMsg(CommonMsg commonMsg) {
//        List<CommonMsg> list = dao.findListByConditionNoPage(null, null, null);
//        if (list != null && !list.isEmpty()) {
//            CommonMsg existCommonMsg = list.get(0);
//            existCommonMsg.setStationRun(commonMsg.getStationRun());
//            existCommonMsg.setDevRun(commonMsg.getDevRun());
//            existCommonMsg.setCreateDate(new Date());
//            /**
//             *   session can't have 2 objects with same ID!!
//             *   update will cause error
//             *   Maybe we can user hibernate merge method.
//             *
//             */
////            commonMsg.setComId(list.get(0).getComId());
////            commonMsg.setCreateDate(new Date());
////            dao.update(commonMsg);
//
//        }
//        else {
//            commonMsg.setCreateDate(new Date());
//            dao.save(commonMsg);
//        }
//    }

    public CommonMsg findCommonMsg() {
        List<CommonMsg> list = dao.findListByConditionNoPage(null, null, null);
        CommonMsg commonMsg = null;
        if(list!=null && list.size()>0){
            commonMsg = list.get(0);
            /**********************************************begin**********************************************************/
            //获取数据内容
            //以类型作为条件，按照显示顺序升序排列，查询站点运行情况的数据
            String stationCondition = " and o.type=?";
            Object [] stationParams = {"1"};
            Map<String, String> stationOrderby = new LinkedHashMap<String, String>();
            stationOrderby.put("o.orderby", "asc");
            List<CommonMsgContent> stationList = contentDao.findListByConditionNoPage(stationCondition, stationParams, stationOrderby);
            //获取返回的数据（拼装之后）
            String stationContent = "";
            if(stationList!=null && stationList.size()>0){
                for(CommonMsgContent elecCommonMsgContent:stationList){
                    String content = elecCommonMsgContent.getContent();
                    stationContent += content;
                }
            }
            //将数据赋值给页面的属性（站点运行情况）
            commonMsg.setStationRun(stationContent);
            /**********************************************************************************/
            //以类型作为条件，按照显示顺序升序排列，查询站点运行情况的数据
            String devCondition = " and o.type=?";
            Object [] devParams = {"2"};
            Map<String, String> devOrderby = new LinkedHashMap<String, String>();
            devOrderby.put("o.orderby", "asc");
            List<CommonMsgContent> devList = contentDao.findListByConditionNoPage(devCondition, devParams, devOrderby);
            //获取返回的数据（拼装之后）
            String devContent = "";
            if(devList!=null && devList.size()>0){
                for(CommonMsgContent elecCommonMsgContent:devList){
                    String content = elecCommonMsgContent.getContent();
                    devContent += content;
                }
            }
            //将数据赋值给页面的属性（设备运行情况）
            commonMsg.setDevRun(devContent);
        }
        return commonMsg;
    }

    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,readOnly=false)
    public void saveCommonMsg(CommonMsg elecCommonMsg) {
        //保存到数据表中
        //删除之前的数据
        List<CommonMsgContent> contentList = contentDao.findListByConditionNoPage(null, null, null);
        contentDao.deleteObjectsByList(contentList);
        //从页面获取站点运行情况和设备运行情况，根据站点运行情况，和设备运行情况保存数据
        String stationRun = elecCommonMsg.getStationRun();
        String devRun = elecCommonMsg.getDevRun();
        //调用StirngUtil的方法，分割字符串
        List<String> stationList = StringUtil.getContentByList(stationRun, 50);
        if(stationList!=null && stationList.size()>0){
            for(int i=0;i<stationList.size();i++){
                CommonMsgContent elecCommonMsgContent = new CommonMsgContent();
                elecCommonMsgContent.setType("1");//1表示站点运行情况
                elecCommonMsgContent.setContent(stationList.get(i));
                elecCommonMsgContent.setOrderby(i+1);
                contentDao.save(elecCommonMsgContent);
            }
        }
        List<String> devList = StringUtil.getContentByList(devRun, 50);
        if(devList!=null && devList.size()>0) {
            for (int i = 0; i < devList.size(); i++) {
                CommonMsgContent elecCommonMsgContent = new CommonMsgContent();
                elecCommonMsgContent.setType("2");//2表示设备运行情况
                elecCommonMsgContent.setContent(devList.get(i));
                elecCommonMsgContent.setOrderby(i + 1);
                contentDao.save(elecCommonMsgContent);
            }
        }


        //查询运行监控表，获取运行监控表的数据，返回List<ElecCommonMsg> list，使用list作为判断数据库中是否存在数据
        List<CommonMsg> list = dao.findListByConditionNoPage(null, null, null);
        //如果list!=null:数据表表中存在数据，获取页面传递的2个参数，组织PO对象，执行更新（update）
        if(list!=null && list.size()>0){
            //方案一：先删除再创建
            //方案二：组织PO对象，执行update
            CommonMsg commonMsg = list.get(0);
            commonMsg.setStationRun("1");//1表示站点运行情况
            commonMsg.setDevRun("2");//2表示设备运行情况
            commonMsg.setCreateDate(new Date());
        }
        //如果list==null:数据表表中不存在数据，获取页面传递的2个参数，组织PO对象，执行新增（save）
        else{
            CommonMsg commonMsg = new CommonMsg();
            commonMsg.setCreateDate(new Date());
            commonMsg.setStationRun("1");//1表示站点运行情况
            commonMsg.setDevRun("2");//2表示设备运行情况
            dao.save(commonMsg);
        }


    }

}
