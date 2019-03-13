package com.tong.gao.walletuser.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseOcrBean {
    String request_id;    //	用于区分每一次请求的唯一的字符串。
    //检测出证件的数组
    List<Card> cards;
    //    整个请求所花费的时间，单位为毫秒。
    int time_used;
    //
    String error_message;

    public boolean isVail() {
        if (error_message != null && !error_message.isEmpty()) return false;
        if (cards == null || cards.isEmpty()) return false;
        Legality legality = cards.get(0).getLegality();
        return legality != null && legality.isLegality();
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public String toString() {
        return "ResponseOcrBean{" +
                "request_id='" + request_id + '\'' +
                ", cards=" + cards +
                ", time_used=" + time_used +
                ", error_message='" + error_message + '\'' +
                '}';
    }

    public static class Card {
        //证件类型。返回1，代表是身份证。
        int type;
        String address; //住址
        // 生日，格式为YYYY-MM-DD
        String birthday;
        //  性别（男/女）
        String gender;
        String id_card_number;
        //                姓名
        String name;
        //民族（汉字）
        String race;
        /**
         * 表示身份证的国徽面或人像面。返回值为：
         * front:人像面
         * back:
         * 国徽面
         */
        String side;

        // 签发机关
        String issued_by;
        /**
         * 有效日期，返回值有两种格式：
         * <p>
         * 一个16位长度的字符串：YYYY.MM.DD-YYYY.MM.DD
         * <p>
         * 或是：YYYY.MM.DD-
         * 长期
         */
        String valid_date;
        Legality legality;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getId_card_number() {
            return id_card_number;
        }

        public void setId_card_number(String id_card_number) {
            this.id_card_number = id_card_number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public String getIssued_by() {
            return issued_by;
        }

        public void setIssued_by(String issued_by) {
            this.issued_by = issued_by;
        }

        public String getValid_date() {
            return valid_date;
        }

        public void setValid_date(String valid_date) {
            this.valid_date = valid_date;
        }

        public Legality getLegality() {
            return legality;
        }

        public void setLegality(Legality legality) {
            this.legality = legality;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "type=" + type +
                    ", address='" + address + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", gender='" + gender + '\'' +
                    ", id_card_number='" + id_card_number + '\'' +
                    ", name='" + name + '\'' +
                    ", race='" + race + '\'' +
                    ", side='" + side + '\'' +
                    ", issued_by='" + issued_by + '\'' +
                    ", valid_date='" + valid_date + '\'' +
                    ", legality=" + legality +
                    '}';
        }
    }

    public static class Legality {
        @SerializedName("Edited")
        float edited;
        @SerializedName("Photocopy")
        float photocopy;
        @SerializedName("ID Photo")
        float iDPhoto;
        @SerializedName("Screen")
        float screen;
        @SerializedName("Temporary ID Photo")
        float temporaryIDPhoto;

        /**
         * "Edited": 0.001,
         * "Photocopy": 0.0,
         * "ID Photo": 0.502,
         * "Screen": 0.496,
         * "Temporary ID Photo": 0.0
         */

        public float getEdited() {
            return edited;
        }

        public void setEdited(float edited) {
            this.edited = edited;
        }

        public float getPhotocopy() {
            return photocopy;
        }

        public void setPhotocopy(float photocopy) {
            this.photocopy = photocopy;
        }

        public float getiDPhoto() {
            return iDPhoto;
        }

        public void setiDPhoto(float iDPhoto) {
            this.iDPhoto = iDPhoto;
        }

        public float getScreen() {
            return screen;
        }

        public void setScreen(float screen) {
            this.screen = screen;
        }

        public float getTemporaryIDPhoto() {
            return temporaryIDPhoto;
        }

        public void setTemporaryIDPhoto(float temporaryIDPhoto) {
            this.temporaryIDPhoto = temporaryIDPhoto;
        }

        public boolean isLegality() {
            if (iDPhoto <= 20) return false;
            if (edited >= iDPhoto) return false;
            if (temporaryIDPhoto >= iDPhoto) return false;
            if (screen >= iDPhoto) return false;
            return true;
        }

        @Override
        public String toString() {
            return "Legality{" +
                    "edited=" + edited +
                    ", photocopy=" + photocopy +
                    ", iDPhoto=" + iDPhoto +
                    ", screen=" + screen +
                    ", temporaryIDPhoto=" + temporaryIDPhoto +
                    '}';
        }
    }
}
