package com.example.wyz.schedulesign.Mvp.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WYZ on 2017/6/6.
 */

public class StudioEntity {
    private boolean Result;
    private List<StudioEntity.MDetail> Detail;

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean result) {
        Result = result;
    }

    public List<StudioEntity.MDetail> getDetail() {
        return Detail;
    }

    public void setDetail(List<StudioEntity.MDetail> detail) {
        Detail = detail;
    }

    public static class MDetail implements Serializable{
        private String studio_id;
        private String studio_name;
        private int  studio_row_count;
        private int studio_col_count;
        /*
        * 0:不能使用
        * 1:可以使用
        * */
        private String studio_flag;


        public String getStudio_id() {
            return studio_id;
        }
        public void setStudio_id(String studio_id) {
            this.studio_id = studio_id;
        }
        public String getStudio_name() {
            return studio_name;
        }
        public void setStudio_name(String studio_name) {
            this.studio_name = studio_name;
        }

        public int getStudio_row_count() {
            return studio_row_count;
        }

        public void setStudio_row_count(int studio_row_count) {
            this.studio_row_count = studio_row_count;
        }

        public int getStudio_col_count() {
            return studio_col_count;
        }

        public void setStudio_col_count(int studio_col_count) {
            this.studio_col_count = studio_col_count;
        }

        public String getStudio_flag() {
            return studio_flag;
        }
        public void setStudio_flag(String studio_flag) {
            this.studio_flag = studio_flag;
        }
    }
}
