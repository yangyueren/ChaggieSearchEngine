package com.example.cges.mongoentity;

import com.example.cges.entity.VideoObj;
import com.example.cges.entity.WeiboObj;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
public class UncheckedEntryDb{
        @Id
        private String _id;
        @Indexed
        private Long id;

        @Indexed
        private String name;

        private String time;
        private String image;
        private Integer view;
        private Integer like;
        private Integer dislike;

        private List<String> tagList;

        private String content;

        private List<VideoObj> videoList;

        private List<String> imageList;

        private List<WeiboObj> weiboList;
        private String wordCloud;

        public UncheckedEntryDb(Long id, String name, String time, String image, Integer view, Integer like, Integer dislike, List<String> tagList, String content, List<VideoObj> videoList, List<String> imageList, List<WeiboObj> weiboList, String wordCloud) {
                this.id = id;
                this.name = name;
                this.time = time;
                this.image = image;
                this.view = view;
                this.like = like;
                this.dislike = dislike;
                this.tagList = tagList;
                this.content = content;
                this.videoList = videoList;
                this.imageList = imageList;
                this.weiboList = weiboList;
                this.wordCloud = wordCloud;
        }

        public UncheckedEntryDb() {
        }

        public String get_id() {
                return _id;
        }

        public void set_id(String _id) {
                this._id = _id;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }

        public String getImage() {
                return image;
        }

        public void setImage(String image) {
                this.image = image;
        }

        public Integer getView() {
                return view;
        }

        public void setView(Integer view) {
                this.view = view;
        }

        public Integer getLike() {
                return like;
        }

        public void setLike(Integer like) {
                this.like = like;
        }

        public Integer getDislike() {
                return dislike;
        }

        public void setDislike(Integer dislike) {
                this.dislike = dislike;
        }

        public List<String> getTagList() {
                return tagList;
        }

        public void setTagList(List<String> tagList) {
                this.tagList = tagList;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public List<VideoObj> getVideoList() {
                return videoList;
        }

        public void setVideoList(List<VideoObj> videoList) {
                this.videoList = videoList;
        }

        public List<String> getImageList() {
                return imageList;
        }

        public void setImageList(List<String> imageList) {
                this.imageList = imageList;
        }

        public List<WeiboObj> getWeiboList() {
                return weiboList;
        }

        public void setWeiboList(List<WeiboObj> weiboList) {
                this.weiboList = weiboList;
        }

        public String getWordCloud() {
                return wordCloud;
        }

        public void setWordCloud(String wordCloud) {
                this.wordCloud = wordCloud;
        }

        @Override
        public String toString() {
                return "UncheckedEntryDb{" +
                        "_id='" + _id + '\'' +
                        ", id=" + id +
                        ", name='" + name + '\'' +
                        ", time='" + time + '\'' +
                        ", image='" + image + '\'' +
                        ", view=" + view +
                        ", like=" + like +
                        ", dislike=" + dislike +
                        ", tagList=" + tagList +
                        ", content='" + content + '\'' +
                        ", videoList=" + videoList +
                        ", imageList=" + imageList +
                        ", weiboList=" + weiboList +
                        ", wordCloud='" + wordCloud + '\'' +
                        '}';
        }
}
