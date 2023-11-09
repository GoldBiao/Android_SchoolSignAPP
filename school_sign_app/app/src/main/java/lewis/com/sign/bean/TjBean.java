package lewis.com.sign.bean;

import java.io.Serializable;
import java.util.List;

public class TjBean implements Serializable{

    /**
     * code : 0
     * msg : success
     * count : 7
     * data : [{"stucent":{"id":1,"account":"1234","name":"大山111","pic":"http://192.168.49.154:8080/main_menu6.png","phone":"1321321111111","srecord":null,"banji":"阿达111"},"total":0,"zcnum":0,"cdnum":0,"wdnum":0},{"stucent":{"id":2,"account":"12345","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"111","srecord":null,"banji":"111"},"total":0,"zcnum":0,"cdnum":0,"wdnum":0},{"stucent":{"id":3,"account":"12345","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"111","srecord":null,"banji":"111"},"total":0,"zcnum":0,"cdnum":0,"wdnum":0},{"stucent":{"id":4,"account":"12345","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"111","srecord":null,"banji":"111"},"total":0,"zcnum":0,"cdnum":0,"wdnum":0},{"stucent":{"id":5,"account":"12345","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"111","srecord":null,"banji":"111"},"total":0,"zcnum":0,"cdnum":0,"wdnum":0},{"stucent":{"id":6,"account":"1234","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"111","srecord":null,"banji":"11"},"total":0,"zcnum":0,"cdnum":0,"wdnum":0},{"stucent":{"id":7,"account":"123","name":"111","pic":"http://192.168.10.135:8080/src=http___p1.itc.cn_images01_20200617_aee49f0014894465be9ba9edc80d8eee.jpeg&refer=http___p1.itc.jpg","phone":"11","srecord":null,"banji":"11"},"total":0,"zcnum":0,"cdnum":0,"wdnum":0}]
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

    public static class DataBean implements Serializable{
        /**
         * stucent : {"id":1,"account":"1234","name":"大山111","pic":"http://192.168.49.154:8080/main_menu6.png","phone":"1321321111111","srecord":null,"banji":"阿达111"}
         * total : 0
         * zcnum : 0
         * cdnum : 0
         * wdnum : 0
         */

        public StucentBean stucent;
        public int total;
        public int zcnum;
        public int cdnum;
        public int wdnum;

        public StucentBean getStucent() {
            return stucent;
        }

        public void setStucent(StucentBean stucent) {
            this.stucent = stucent;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getZcnum() {
            return zcnum;
        }

        public void setZcnum(int zcnum) {
            this.zcnum = zcnum;
        }

        public int getCdnum() {
            return cdnum;
        }

        public void setCdnum(int cdnum) {
            this.cdnum = cdnum;
        }

        public int getWdnum() {
            return wdnum;
        }

        public void setWdnum(int wdnum) {
            this.wdnum = wdnum;
        }

        public static class StucentBean implements Serializable{
            /**
             * id : 1
             * account : 1234
             * name : 大山111
             * pic : http://192.168.49.154:8080/main_menu6.png
             * phone : 1321321111111
             * srecord : null
             * banji : 阿达111
             */

            public int id;
            public String account;
            public String name;
            public String pic;
            public String phone;
            public Object srecord;
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

            public Object getSrecord() {
                return srecord;
            }

            public void setSrecord(Object srecord) {
                this.srecord = srecord;
            }

            public String getBanji() {
                return banji;
            }

            public void setBanji(String banji) {
                this.banji = banji;
            }
        }
    }
}
