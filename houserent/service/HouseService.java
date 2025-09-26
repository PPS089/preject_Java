package com.houserent.service;

import com.houserent.domain.House;
import com.houserent.utils.Utility;

/***
 * 1.响应View的调用
 * 2.完成对房屋信息的各种操作
 */
public class HouseService {

    private House[] houses;//保存大小
    private int houseNums=1;//记录当前有多少个房屋信息
    private int idConter=1;//记录当前id增长到哪个值了

    //构造器
    public HouseService(int size){
        houses=new House[size];//指定数组大小
        houses[0]=new House(1,"jack","112","海淀区",2000,"未出租");//示例
    }


    //findById方法，查找房屋
    public House findByID(int findId){

        //遍历
        for(int i=0;i<houseNums;i++){
            if(houses[i].getId()==findId){
                return houses[i];
            }
        }

        return null;
    }


    //del方法，删除一个房屋信息
    public boolean del(int delID){

        //先找到要删除的房屋信息对应的下标
        int index=-1;
        for(int i=0;i<houseNums;i++){
            //寻找对应的id
            if(delID==houses[i].getId()){
                index=i;//记录
            }
        }

        if(index==-1){//说明delID在数组中不存在
            return false;
        }

        //如果找到
        for(int i=index;houses[i]!=null;i++){
            //第一个条件是确保当满数组时的情况，防止数组越界
            //不能颠倒判断逻辑顺序，否则按上行情况会出现数组越界
            if(i+1==houseNums || houses[i+1]==null){
                houses[i]=null;
                break;
            }
            houses[i]=houses[i+1];
            //houses[i].setId(houses[i].getId()-1);//id自减，因为前一项被删除
        }
        houseNums--;//房屋总数减1
        return true;
    }

    //add方法，添加新对象，返回boolean
    public boolean add(House newHouse){
        //先判断是否还可以继续添加新房屋，暂时不考虑数组扩容的问题
        if(houseNums>= houses.length){//已满
            System.out.println("已满，不能继续添加了");
            return false;
        }

        //数组扩容机制
        /*if(houseNums>= houses.length){
            House[] houses1 = houses;//信息备份
            houses=null;
            houses=new House[houseNums+10];//每次增加10个容量
            System.arraycopy(houses1,0,houses,0,houseNums);//数据迁移回去
        }*/

        houses[houseNums++]=newHouse;
        /**
         * 等价于
         * houses[houseNums]=newHouse;
         * houseNums++;
         */

        //需要一个id自增长机制
        //房屋的id是从1开始计算的，数组是从0开始标记的
        newHouse.setId(++idConter);
        /***
         * 等价于
         * idConter++;
         * newHouse.setId(idConter);
         */
        return true;

    }

    //list方法，返回house
    public House[] list(){
        return houses;
    }
}
