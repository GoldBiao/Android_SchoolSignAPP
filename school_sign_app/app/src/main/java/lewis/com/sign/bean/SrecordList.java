package lewis.com.sign.bean;

import java.io.Serializable;
import java.util.List;

public class SrecordList implements Serializable{

    /**
     * code : 0
     * msg : success
     * count : 7
     * data : [{"id":1,"account":"1234","name":"大山111","pic":"http://192.168.49.154:8080/main_menu6.png","phone":"1321321111111","srecord":{"id":1,"sid":1,"stucent":null,"kecheng":null,"kid":1,"time":"2021-12-29 11:11:11"},"banji":"阿达111"},{"id":2,"account":"12345","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"111","srecord":null,"banji":"111"},{"id":3,"account":"12345","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"111","srecord":null,"banji":"111"},{"id":4,"account":"12345","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"111","srecord":null,"banji":"111"},{"id":5,"account":"12345","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"111","srecord":null,"banji":"111"},{"id":6,"account":"1234","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"111","srecord":null,"banji":"11"},{"id":7,"account":"123","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"11","srecord":{"id":2,"sid":7,"stucent":null,"kecheng":null,"kid":1,"time":"2021-12-29 11:45"},"banji":"11"}]
     */

    public int code;
    public String msg;
    public int count;
    public List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * account : 1234
         * name : 大山111
         * pic : http://192.168.49.154:8080/main_menu6.png
         * phone : 1321321111111
         * srecord : {"id":1,"sid":1,"stucent":null,"kecheng":null,"kid":1,"time":"2021-12-29 11:11:11"}
         * banji : 阿达111
         */

        public int id;
        public String account;
        public String name;
        public String pic;
        public String phone;
        public SrecordBean srecord;
        public String banji;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public SrecordBean getSrecord() {
            return srecord;
        }

        public void setSrecord(SrecordBean srecord) {
            this.srecord = srecord;
        }

        public String getBanji() {
            return banji;
        }

        public void setBanji(String banji) {
            this.banji = banji;
        }

        public static class SrecordBean {
            /**
             * id : 1
             * sid : 1
             * stucent : null
             * kecheng : null
             * kid : 1
             * time : 2021-12-29 11:11:11
             */

            public int id;
            public int sid;
            public Object stucent;
            public Object kecheng;
            public int kid;
            public String time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSid() {
                return sid;
            }

            public void setSid(int sid) {
                this.sid = sid;
            }

            public Object getStucent() {
                return stucent;
            }

            public void setStucent(Object stucent) {
                this.stucent = stucent;
            }

            public Object getKecheng() {
                return kecheng;
            }

            public void setKecheng(Object kecheng) {
                this.kecheng = kecheng;
            }

            public int getKid() {
                return kid;
            }

            public void setKid(int kid) {
                this.kid = kid;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}

