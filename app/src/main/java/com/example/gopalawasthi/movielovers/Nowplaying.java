package com.example.gopalawasthi.movielovers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gopal Awasthi on 19-03-2018.
 */

public class Nowplaying {

  private int page;
  private List<ResultBean> results;

    public Nowplaying(int page, List<ResultBean> results) {
        this.page = page;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ResultBean> getResults() {
        return results;
    }

    public void setResults(List<ResultBean> results) {
        this.results = results;
    }

    public static class ResultBean {
        private int id;
        private String title;
        @SerializedName("poster_path")
        private String poster_id;
        private String overview;
        private int vote_average;

        public ResultBean(int id, String title, String poster_id, String overview, int vote_average) {
            this.id = id;
            this.title = title;
            this.poster_id = poster_id;
            this.overview = overview;
            this.vote_average = vote_average;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster_id() {
            return poster_id;
        }

        public void setPoster_id(String poster_id) {
            this.poster_id = poster_id;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public int getVote_average() {
            return vote_average;
        }

        public void setVote_average(int voter_average) {
            this.vote_average = voter_average;
        }
    }
}
