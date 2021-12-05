package com.coder.blog.Utils;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ui.ModelMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The type Resp message utils.
 *
 * @Author coder
 * @Date 2021 /11/29 21:44
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespMessageUtils {

    private Boolean result;

    private String msg;
    /** 数据 */
    private Object data;

    private int size;

  /**
   * 只返回校验结果
   *
   * @param result the result
   */
  public RespMessageUtils(Boolean result) {
        this.result = result;
    }

  /**
   * 返回校验结果和数据
   *
   * @param result the result
   * @param data   the data
   */
  public RespMessageUtils(Boolean result, Object data) {
        this.result = result;
        this.data = data;
    }

  /**
   * 返回校验结果和相关的提示信息
   *
   * @param result the result
   * @param msg    the msg
   */
  public RespMessageUtils(Boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

  /**
   * 返回成功的信息
   *
   * @return string
   */
  public static String SUCCESS(){
        return JSON.toJSONString(new RespMessageUtils(true));
    }

  /**
   * 返回成功的信息和数据
   *
   * @param data :数据
   * @return string
   */
  public static String SUCCESS(Object data){
        return JSON.toJSONString(new RespMessageUtils(true,data));
    }

  /**
   * 返回错误
   *
   * @return string
   */
  public static String ERROR(){
        return JSON.toJSONString(new RespMessageUtils(false,null));
    }

  /**
   * 返回错误的信息
   *
   * @param msg the msg
   * @return string
   */
  public static String ERROR(String msg){
        return JSON.toJSONString(new RespMessageUtils(false,msg));
    }

  /**
   * 产生错误信息队列
   *
   * @param map  the map
   * @param args the args
   */
  public static void generateErrorInfo(ModelMap map, String[] args){
        Object error = map.getAttribute("errors");
        List<String> errors  = new LinkedList<>();
        if(error == null) {
            errors.addAll(List.of(args));
        }else{
            errors = (List<String>) error;
            errors.add("用户名和密码错误");
        }
        map.addAttribute("errors",errors);
    }

}
