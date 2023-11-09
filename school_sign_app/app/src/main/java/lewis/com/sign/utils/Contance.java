package lewis.com.sign.utils;


//接口
public class Contance {

    public static  String BaseUrl="http://192.168.158.192:8080/sign";
    public static String Login=BaseUrl+"/login";//登录
    public static String addKecheng=BaseUrl+"/addKecheng";//添加课程
    public static String getAllKechengByTid=BaseUrl+"/getAllKechengByTid";//添加课程
    public static String getAllStudentRecordBYKname=BaseUrl+"/getAllStudentRecordBYKname";//添加课程
    public static String getTercherInfo=BaseUrl+"/getTercherInfo";//用户信息
    public static String upTercher=BaseUrl+"/upTercher";//修改用户
    public static String addRecord=BaseUrl+"/addRecord";//
    public static String getAllStudentRecordBYKid=BaseUrl+"/getAllStudentRecordBYKid";//
    public static String faceCheckBase64=BaseUrl+"/faceCheckBase64";//人脸搜索

    public static String UpLoadPic=BaseUrl+"/upload";//上传头像

}
