package com.houserent.domain;

/***
 * House类的一个对象表示一个房屋信息
 */
public class House {

    //属性 编号、姓名、电话、地址、月租、状态
    private int id;         //房屋id
    private String name;    //房东姓名
    private String phone;   //房东电话
    private String address; //房屋地址
    private int rent;       //月租
    private String state;   //状态

    //构造器
    public House(int id,String name,String phone,String address,int rent,String state){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.address=address;
        this.rent=rent;
        this.state=state;
    }

    //get、set访问器

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //为了方便输出

    @Override
    public String toString() {
        return  id +
                "\t\t"+name +
                "\t"+ phone +
                "\t\t"+ address +
                "\t" + rent +
                "\t" + state ;
    }
}
