package com.example.demo.constant;

/**
 * Created by shichong on 2018/6/25.
 */
public class Constant {
    /**
     * WEB API code码
     */
    public static class WebApiResDict{
        public static final String SUCCESS_CODE="0";//success
        public static final String ERROR_CODE="1";//error
    }

    /**
     * Http状态数据字典
     */
    public static class HttpStatusDict {
        public static final String SUCCESS_CODE ="200";
        public static final String ERROR_CODE = "500";
        public static final String BAD_REQUEST = "400";
        public static final String NOT_FOUND = "404";
        public static final String EXCEPTION = "450";//调用统计异常，记录请求信息时发生错误。
        public static final String APPKEY_NULL = "460";//请求appkey为空
        public static final String SIGN_NULL= "461";//请求签名为空
        public static final String CONSUMER_IS_NOT_EXIST = "462";//appkey对应的合作方不存在
        public static final String UN_AUTH = "463";//未授权合作方
        public static final String SIGN_ERROR = "464";//签名错误
        public static final String AUTH_EXCEPTION = "465";
        public static final String PARM_CONVERT_EXCEPTION = "466";//参数转换异常，具体错误见异常信息
    }

    /**
     * Http消息MSG数据字典
     */
    public static class HttpMsgDict {
        public static final String SUCCESS_MSG ="Success";
        public static final String ERROR_MSG ="Error";
        public static final String BAD_REQUEST_MSG = "Bad request";
        public static final String NOT_FOUND_MSG ="Not found";
        public static final String EXCEPTION_MSG ="Call exception";
        public static final String APPKEY_NULL_MSG ="the appkey is null";
        public static final String SIGN_NULL_MSG= "The sign is null";//请求签名为空
        public static final String CONSUMER_IS_NOT_EXIST_MSG = "The consumer is not exist";//appkey对应的合作方不存在
        public static final String UN_AUTH_MSG = "Partner unauthorized";//未授权合作方
        public static final String SIGN_ERROR_MSG = "Signature error";//签名错误
        public static final String AUTH_EXCEPTION_MSG = "Authority exception";
        public static final String PARM_CONVERT_EXCEPTION_MSG = "Parameter conversion exception";//参数转换异常，具体错误见异常信息
    }

    /**
     * User 信息反馈数据字典
     */
    public static class UserDict{
        public static final String SUCCESS_CODE ="200";
        public static final String ERROR_CODE ="500";
        public static final String  SUCCESS_MSG ="操作成功！";
        public static final String SUCCESS_ADDMSG ="添加成功！";
        public static final String SUCCESS_MODMSG ="修改成功！";
        public static final String  SUCCESS_DELMSG ="删除成功！";
        public static final String  SUCCESS_SELMSG ="查询成功！";
        public static final String ERROR_MSG ="参数异常！";
        public static final String ERROR_ADDMSG ="添加失败！";
        public static final String ERROR_DELMSG ="删除失败！";
        public static final String ERROR_MODMSG ="修改失败！";
        public static final String ERROR_SELMSG ="查询失败！";
    }
}
