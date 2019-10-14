package com.hbh.blog.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:Hebinhua@foresee.com.cn
 * @Date:2019/10/10
 * @Des:数据返回封装类
 */
public class JsonResultUtil implements Serializable {
    /**
     * 返回代码-成功  0
     */
    public static final String RESPONSE_CODE_SUCCESS = "0";

    /**
     * 返回代码-失败  1
     */
    public static final String RESPONSE_CODE_ERROR = "1";

    /**
     * @Description 组装报文
     * @param code, msg, body
     * @return java.lang.String
     * @Author HeBinhua
     * @Date 2019/10/10
     */
    public static String getResponse( String code, String msg, JSONArray body) {
        ValueFilter filter = new ValueFilter() {
            @Override
            public Object process(Object obj, String s, Object v) {
                if (v == null) {
                    return "";
                }
                return v;
            }
        };
        JSONObject response = new JSONObject();
        response.put("timeStamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        response.put("code", code);
        response.put("msg", msg);
        response.put("body", body);
        return JSONObject.toJSONString(response, filter);
    }

    /**
     * @Description  组装成功报文
     * @param msg, body
     * @return java.lang.String
     * @Author HeBinhua
     * @Date 2019/10/10
     */
    public static String getSuccessResponse(String msg, JSONArray body){
        return JsonResultUtil.getResponse(JsonResultUtil.RESPONSE_CODE_SUCCESS, msg, body);
    }

    /**
     * @Description 组装失败报文
     * @param msg
     * @return java.lang.String
     * @Author HeBinhua
     * @Date 2019/10/10
     */
    public static String getErrorResponse(String msg){
        return JsonResultUtil.getResponse( JsonResultUtil.RESPONSE_CODE_ERROR, msg, null);
    }

}
