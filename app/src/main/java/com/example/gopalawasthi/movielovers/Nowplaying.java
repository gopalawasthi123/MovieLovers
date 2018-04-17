package com.example.gopalawasthi.movielovers;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gopal Awasthi on 19-03-2018.
 */

  public class Nowplaying {

    private int page;
    private int total_results;
    private DatesBean dates;
    private int total_pages;
    private List<ResultsBean> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public DatesBean getDates() {
        return dates;
    }

    public void setDates(DatesBean dates) {
        this.dates = dates;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class DatesBean {
        /**
         * maximum : 2018-03-19
         * minimum : 2018-01-29
         */

        private String maximum;
        private String minimum;

        public String getMaximum() {
            return maximum;
        }

        public void setMaximum(String maximum) {
            this.maximum = maximum;
        }

        public String getMinimum() {
            return minimum;
        }

        public void setMinimum(String minimum) {
            this.minimum = minimum;
        }
    }
    @Entity(tableName = "beansList")
    public static class ResultsBean  {
        /**
         * vote_count : 1009
         * id : 337167
         * video : false
         * vote_average : 6.2
         * title : Fifty Shades Freed
         * popularity : 592.721736
         * poster_path : /jjPJ4s3DWZZvI4vw8Xfi4Vqa1Q8.jpg
         * original_language : en
         * original_title : Fifty Shades Freed
         * genre_ids : [18,10749]
         * backdrop_path : /9ywA15OAiwjSTvg3cBs9B7kOCBF.jpg
         * adult : false
         * overview : Believing they have left behind shadowy figures from their past, newlyweds Christian and Ana fully embrace an inextricable connection and shared life of luxury. But just as she steps into her role as Mrs. Grey and he relaxes into an unfamiliar stability, new threats could jeopardize their happy ending before it even begins.
         * release_date : 2018-02-07
         */
        @Ignore
        private int vote_count;
        @PrimaryKey
        private int id;
        @Ignore
        private boolean video;

        private boolean type;

        public boolean isType() {
            return type;
        }

        public void setType(boolean type) {
            this.type = type;
        }

        private double vote_average;

        private String title;
        @Ignore
        private double popularity;

        private String poster_path;
        @Ignore
        private String original_language;
        @Ignore
        private String original_title;

        private String backdrop_path;
        @Ignore
        private boolean adult;

        private String overview;
        @Ignore
        private String release_date;
        @Ignore
        private List<Integer> genre_ids;

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }
    }
}
