package com.example.wyz.schedulesign.Mvp.Entity;

import java.util.List;

/**
 * Created by WYZ on 2017/5/15.
 */

public class PeopleEntity {

    private String username;
    private String password;
    private boolean Result;
    private List<MDetail> Detail;
    public static class MDetail{

        private  int status;
        private int emp_id;
        private String emp_no;
        private String emp_name;
        private String emp_tel_num;
        private String emp_addr;
        private String emp_email;
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getEmp_id() {
            return emp_id;
        }

        public String getEmp_no() {
            return emp_no;
        }

        public String getEmp_addr() {
            return emp_addr;
        }

        public String getEmp_email() {
            return emp_email;
        }

        public String getEmp_name() {
            return emp_name;
        }

        public String getEmp_tel_num() {
            return emp_tel_num;
        }



        public void setEmp_addr(String emp_addr) {
            this.emp_addr = emp_addr;
        }

        public void setEmp_id(int emp_id) {
            this.emp_id = emp_id;
        }

        public void setEmp_email(String emp_email) {
            this.emp_email = emp_email;
        }

        public void setEmp_name(String emp_name) {
            this.emp_name = emp_name;
        }

        public void setEmp_no(String emp_no) {
            this.emp_no = emp_no;
        }

        public void setEmp_tel_num(String emp_tel_num) {
            this.emp_tel_num = emp_tel_num;
        }

    }

    public List<MDetail> getDetail() {
        return Detail;
    }

    public void setDetail(List<MDetail> detail) {
        Detail = detail;
    }

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean result) {
        Result = result;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
