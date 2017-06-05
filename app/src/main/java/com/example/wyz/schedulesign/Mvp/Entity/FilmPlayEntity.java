package com.example.wyz.schedulesign.Mvp.Entity;

import java.util.List;

/**
 * Created by WYZ on 2017/6/5.
 */

public class FilmPlayEntity {

    private boolean Result;
    private List<FilmPlayEntity.MDetail> Detail;

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean result) {
        Result = result;
    }

    public List<FilmPlayEntity.MDetail> getDetail() {
        return Detail;
    }

    public void setDetail(List<FilmPlayEntity.MDetail> detail) {
        Detail = detail;
    }

    public class MDetail{
        private String play_start;
        private String play_end;
        private String  studio_id;
        private String film_id;
        private String film_name;
        private String play_id;

        public String getPlay_id() {
            return play_id;
        }

        public void setPlay_id(String play_id) {
            this.play_id = play_id;
        }

        public String getPlay_start() {
            return play_start;
        }

        public void setPlay_start(String play_start) {
            this.play_start = play_start;
        }

        public String getPlay_end() {
            return play_end;
        }

        public void setPlay_end(String play_end) {
            this.play_end = play_end;
        }

        public String getStudio_id() {
            return studio_id;
        }

        public String getFilm_id() {
            return film_id;
        }

        public void setStudio_id(String studio_id) {
            this.studio_id = studio_id;
        }

        public void setFilm_id(String film_id) {
            this.film_id = film_id;
        }

        public String getFilm_name() {
            return film_name;
        }

        public void setFilm_name(String film_name) {
            this.film_name = film_name;
        }
    }

}
