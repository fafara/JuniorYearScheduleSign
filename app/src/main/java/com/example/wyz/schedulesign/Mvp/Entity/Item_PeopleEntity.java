package com.example.wyz.schedulesign.Mvp.Entity;

/**
 * Created by WYZ on 2017/5/18.
 */

public class Item_PeopleEntity {
    private String tel;
    private int id;
    private String emp_no;
    private String emp_name;

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_tel_num() {
        return emp_tel_num;
    }

    public void setEmp_tel_num(String emp_tel_num) {
        this.emp_tel_num = emp_tel_num;
    }

    public String getEmp_addr() {
        return emp_addr;
    }

    public void setEmp_addr(String emp_addr) {
        this.emp_addr = emp_addr;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    private String emp_tel_num;
    private String emp_addr;
    private String emp_email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
