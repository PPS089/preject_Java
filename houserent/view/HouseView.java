package com.houserent.view;

import com.houserent.domain.House;
import com.houserent.service.HouseService;
import com.houserent.utils.Utility;

/***
 * 1.显示界面
 * 2.接收用户输入
 * 3.调用HouseService完成对房屋信息的各种操作
 */
public class HouseView {

    //控制显示菜单
    private boolean loop = true; //控制显示菜单
    private char key=' ';        //接收用户输入
    private HouseService houseService = new HouseService(10);//初始化为10

    //编写updateHouse()，修改方法
    public void updateHouse(){
        System.out.println("---------------------------修改房屋信息---------------------------");
        System.out.print("请输入要修改的id(-1代表退出修改)：");
        int updateId=Utility.readInt();
        if(updateId == -1){
            System.out.println("---------------------------放弃修改---------------------------");
            return;
        }

        //根据输入的id查找对象
        House house = houseService.findByID(updateId);//返回的是引用类型
        if(house==null){
            System.out.println("-------------------------该房屋不存在---------------------------");
            return;
        }

        //修改姓名
        System.out.print("姓名("+house.getName()+")：");
        String name = Utility.readString(8,"");//如果用户直接回车，表示不修改该信息，默认”“
        if(!"".equals(name)){//表示要修改
            house.setName(name);
        }

        //修改电话
        System.out.print("电话("+house.getPhone()+")：");
        String phone = Utility.readString(12,"");
        if(!"".equals(phone)){//表示要修改
            house.setPhone(phone);
        }

        //修改地址
        System.out.print("地址("+house.getAddress()+")：");
        String address = Utility.readString(18,"");
        if(!"".equals(address)){//表示要修改
            house.setAddress(address);
        }

        //修改月租
        System.out.print("月租("+house.getRent()+")：");
        int rent = Utility.readInt(-1);
        if(rent!=-1){//表示要修改
            house.setRent(rent);
        }

        //修改状态
        System.out.print("状态("+house.getState()+")：");
        String  state = Utility.readString(3,"");
        if(!"".equals(state)){//表示要修改
            house.setState(state);
        }
    }

    //编写findHouse()方法，查询方法
    public void findHouse(){
        System.out.println("---------------------------查找房屋信息---------------------------");
        System.out.print("请输入要查找的id：");
        int findId=Utility.readInt();
        House house = houseService.findByID((findId));
        if(house!=null){
            System.out.println(house);
        }else{
            System.out.println("---------------------------该房屋不存在---------------------------");
        }
    }

    //完成退出确认方法
    public void exut(){
        char c = Utility.readConfirmSelection();
        if(c=='Y'){
            loop=false;
        }
    }

    //编写delHouse()方法，接收输入的id，调用Service方法
    public void delHouse(){
        System.out.println("---------------------------删除房屋信息---------------------------");
        System.out.print("请输入待删除房屋的编号(-1退出)：");
        int delID=Utility.readInt();
        if(delID==-1){
            System.out.println("---------------------------放弃删除---------------------------");
            return;
        }
        System.out.print("请确认是否删除y/n：");
        char choice = Utility.readConfirmSelection();//该方法内部有无限循环，必须输入y/n
        if (choice == 'Y') {//确认删除
            if(houseService.del(delID)){
                System.out.println("---------------------------删除成功---------------------------");
            }else{
                System.out.println("-----------------------编号不存在，删除失败-----------------------");
            }
        }else{
            System.out.println("---------------------------放弃删除---------------------------");
        }

    }

    //编写addhouse()方法,创建House对象，调用add方法
    public void addHouse(){
        System.out.println("---------------------------添加房屋---------------------------");
        System.out.print("姓名：");
        String name = Utility.readString(8);//名字长度
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态：");
        String state = Utility.readString(3);

        //创建一个新的House对象，注意id时系统分配，用户不能输入
        House house = new House(0,name,phone,address,rent,state);
        //返回true
        if(houseService.add(house)){
            System.out.println("添加房屋成功");
        }else{
            System.out.println("添加房屋失败");
        }
    }


    //编写listHouse()显示房屋列表
    public void listHouse(){
        System.out.println("---------------------------房屋列表---------------------------");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态");
        House[] houses = houseService.list();//接收列表数组
        for(int i=0;houses[i]!=null;i++){
            System.out.println(houses[i]);//默认调用house[i].toString();  已被重写
        }
    }


    //显示主菜单
    public void mainMenu(){
        do {
            System.out.println("\n============房屋出租系统菜单============");
            System.out.println("\t\t\t1.新 增 房 源");
            System.out.println("\t\t\t2.查 找 房 源");
            System.out.println("\t\t\t3.删除房屋信息");
            System.out.println("\t\t\t4.修改房屋信息");
            System.out.println("\t\t\t5.显示房屋信息");
            System.out.println("\t\t\t6.退      房");
            System.out.print("请输入你的选择：");
            key= Utility.readChar();
            switch (key){
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    updateHouse();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    exut();
                    break;
                default:
                    System.out.print("请输入正确的编号");
                    break;
            }

        }while(loop);
    }

}
