package com.example.wyz.schedulesign.Mvp.Entity;

/**
 * Created by WYZ on 2017/5/21.
 */

public class LoginSingleton {
    private int id;
    private  String username;
    private  String name;
    private  String tel;
    private  String addr;
    private  String email;
    private  String image;
    private  LoginSingleton(){

    }
    public static   LoginSingleton getInstance(){
        return  LoginSingletonHolder.mLoginSingleton;
    }
    private  static  class  LoginSingletonHolder{
        private static final LoginSingleton mLoginSingleton=new LoginSingleton();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public   void   updateLoginSingleton(String username, String name, String tel, String addr, String email){
        LoginSingleton.getInstance().setUsername(username);
        LoginSingleton.getInstance().setName(name);
        LoginSingleton.getInstance().setTel(tel);
        LoginSingleton.getInstance().setEmail(email);
        LoginSingleton.getInstance().setAddr(addr);

    }
    public  void setLoginSingleton(int id,String username,String name,String tel,String addr,String email ){
        LoginSingleton.getInstance().setId(id);
        LoginSingleton.getInstance().setUsername(username);
        LoginSingleton.getInstance().setName(name);
        LoginSingleton.getInstance().setTel(tel);
        LoginSingleton.getInstance().setEmail(email);
        LoginSingleton.getInstance().setAddr(addr);
    }
}
