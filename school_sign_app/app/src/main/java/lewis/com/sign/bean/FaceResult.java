package lewis.com.sign.bean;

import java.util.List;

public class FaceResult {

    /**
     * code : 0
     * msg : null
     * count : 0
     * data : {"error_code":0,"error_msg":"SUCCESS","log_id":5520179159445,"timestamp":1640748484,"cached":0,"result":{"face_token":"49e33d050e42b30b183b9ab841473b9b","user_list":[{"group_id":"sign","user_id":"7","user_info":"","score":99.086097717285}]}}
     */

    public int code;
    public String msg;
    public int count;
    public DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * error_code : 0
         * error_msg : SUCCESS
         * log_id : 5520179159445
         * timestamp : 1640748484
         * cached : 0
         * result : {"face_token":"49e33d050e42b30b183b9ab841473b9b","user_list":[{"group_id":"sign","user_id":"7","user_info":"","score":99.086097717285}]}
         */

        public int error_code;
        public String error_msg;
        public long log_id;
        public int timestamp;
        public int cached;
        public ResultBean result;

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public String getError_msg() {
            return error_msg;
        }

        public void setError_msg(String error_msg) {
            this.error_msg = error_msg;
        }

        public long getLog_id() {
            return log_id;
        }

        public void setLog_id(long log_id) {
            this.log_id = log_id;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public int getCached() {
            return cached;
        }

        public void setCached(int cached) {
            this.cached = cached;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * face_token : 49e33d050e42b30b183b9ab841473b9b
             * user_list : [{"group_id":"sign","user_id":"7","user_info":"","score":99.086097717285}]
             */

            public String face_token;
            public List<UserListBean> user_list;

            public String getFace_token() {
                return face_token;
            }

            public void setFace_token(String face_token) {
                this.face_token = face_token;
            }

            public List<UserListBean> getUser_list() {
                return user_list;
            }

            public void setUser_list(List<UserListBean> user_list) {
                this.user_list = user_list;
            }

            public static class UserListBean {
                /**
                 * group_id : sign
                 * user_id : 7
                 * user_info :
                 * score : 99.086097717285
                 */

                public String group_id;
                public String user_id;
                public String user_info;
                public double score;

                public String getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(String group_id) {
                    this.group_id = group_id;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getUser_info() {
                    return user_info;
                }

                public void setUser_info(String user_info) {
                    this.user_info = user_info;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }
            }
        }
    }
}
