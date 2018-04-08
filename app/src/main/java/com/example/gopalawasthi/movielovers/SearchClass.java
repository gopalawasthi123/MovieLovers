package com.example.gopalawasthi.movielovers;

import java.util.List;

/**
 * Created by Gopal Awasthi on 07-04-2018.
 */

public class SearchClass {



    private int page;
    private int total_results;
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

    public static class ResultsBean {
        /**
         * original_name : Batman
         * id : 2287
         * media_type : tv
         * name : Batman
         * vote_count : 88
         * vote_average : 6.87
         * poster_path : /1ZEJuuDh0Zpi5ELM3Zev0GBhQ3R.jpg
         * first_air_date : 1966-01-12
         * popularity : 16.419215
         * genre_ids : [10759,10765,35]
         * original_language : en
         * backdrop_path : /oQKyPlXeYeECOBD5VBWsXY33sgS.jpg
         * overview : Batman is a 1960s American live action television series, based on the DC comic book character of the same name. It stars Adam West as Batman and Burt Ward as Robin — two crime-fighting heroes who defend Gotham City. It aired on ABC for three seasons from January 12, 1966 to March 14, 1968. The show was aired twice weekly for its first two seasons, resulting in the production of a total of 120 episodes.
         * origin_country : ["US"]
         * video : false
         * title : Batman
         * original_title : Batman
         * adult : false
         * release_date : 1989-06-23
         */

        private String original_name;
        private int id;
        private String media_type;
        private String name;
        private int vote_count;
        private double vote_average;
        private String poster_path;
        private String first_air_date;
        private double popularity;
        private String original_language;
        private String backdrop_path;
        private String overview;
        private boolean video;
        private String title;
        private String original_title;
        private boolean adult;
        private String release_date;
        private List<Integer> genre_ids;
        private List<String> origin_country;

        public String getOriginal_name() {
            return original_name;
        }

        public void setOriginal_name(String original_name) {
            this.original_name = original_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMedia_type() {
            return media_type;
        }

        public void setMedia_type(String media_type) {
            this.media_type = media_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getFirst_air_date() {
            return first_air_date;
        }

        public void setFirst_air_date(String first_air_date) {
            this.first_air_date = first_air_date;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
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

        public List<String> getOrigin_country() {
            return origin_country;
        }

        public void setOrigin_country(List<String> origin_country) {
            this.origin_country = origin_country;
        }
    }
}

