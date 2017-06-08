package com.example.wyz.schedulesign.Mvp.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WYZ on 2017/5/19.
 */

public class FilmEntity {
    private boolean Result;
    private List<MDetail> Detail;

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

    public static class MDetail implements  Serializable{
        private int film_id;
        private String film_name;
        private String film_tostar;
        private String film_release;
        private int film_hourlong;
        private String film_type;
        private String film_price;
        private String film_img;

        public String getFilm_img() {
            return film_img;
        }

        public void setFilm_img(String film_img) {
            this.film_img = film_img;
        }

        public String getFilm_name() {
            return film_name;
        }

        public void setFilm_name(String film_name) {
            this.film_name = film_name;
        }

        public String getFilm_tostar() {
            return film_tostar;
        }

        public void setFilm_tostar(String film_tostar) {
            this.film_tostar = film_tostar;
        }

        public String getFilm_release() {
            return film_release;
        }

        public void setFilm_release(String film_release) {
            this.film_release = film_release;
        }

        public int getFilm_hourlong() {
            return film_hourlong;
        }

        public void setFilm_hourlong(int film_hourlong) {
            this.film_hourlong = film_hourlong;
        }

        public String getFilm_type() {
            return film_type;
        }

        public void setFilm_type(String film_type) {
            this.film_type = film_type;
        }

        public String getFilm_price() {
            return film_price;
        }

        public void setFilm_price(String film_price) {
            this.film_price = film_price;
        }

        public int getFilm_id() {

            return film_id;
        }

        public void setFilm_id(int film_id) {
            this.film_id = film_id;
        }
    }
}
