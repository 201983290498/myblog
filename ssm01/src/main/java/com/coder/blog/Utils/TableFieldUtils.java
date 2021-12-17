package com.coder.blog.Utils;


import com.coder.commom.annotation.TableField;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * table的渲染工具类
 * @Author coder
 * @Date 2021/12/14 22:45
 * @Description
 */
@Data
public class TableFieldUtils {

  /**
   * 获取实体类的中文名称, 是渲染表格的表头
   *
   * @param destination 方法数据的目标域，可以是ModalMap
   * @param target      带渲染的表格类
   */
  public static void getColumnNames(Map destination, Class target) {
    TableField table = (TableField) target.getAnnotation(TableField.class);
    Field[] fields1 = target.getDeclaredFields();
    List<String> columns = new ArrayList<>();
    //是否开启渲染
    if (table != null && table.enableRendering()) {
      for (Field field : fields1) {
        field.setAccessible(true);
        TableField tableField = field.getAnnotation(TableField.class);
        if (tableField != null) {
          columns.add(tableField.column());
        }
      }
    }else{//普通的获取类名
      for(Field field : fields1){
        columns.add(field.getName());
      }
    }
    destination.put("columns", columns);
  }

  /**
   * 获取渲染之后的表格数据
   * @param list 待转换的数据列表
   * @param aclass 待转换的数据类型
   * @return
   */
  public static void getTable(Map destination,List<?> list, Class aclass){
    TableField method = (TableField) aclass.getAnnotation(TableField.class);

    //是都开启渲染
    if(method.enableRendering()) {
      List<Map<String, Object>> table = new ArrayList<>();
      //遍历每一个数据对象
      for (int i = 0; i < list.size(); i++) {
        //转换后的数据
        Map<String, Object> newData = new HashMap<>();
        Field[] fields = aclass.getDeclaredFields();
        for (Field field : fields) {
          field.setAccessible(true);
          TableField annotation = field.getAnnotation(TableField.class);
          DateTimeFormat annotation1 = field.getAnnotation(DateTimeFormat.class);
          if (annotation != null) {
            try {
              if(annotation1 != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(annotation1.pattern());
                String format = sdf.format(field.get(list.get(i)));
                newData.put(field.getName(), format);
              }else {
                newData.put(field.getName(), field.get(list.get(i)));
              }
            } catch (IllegalAccessException e) {
              System.out.println("数据转换失败");
              e.printStackTrace();
            }
          }
        }
        table.add(newData);
      }
      destination.put("table",table);
    }else{
      destination.put("table",list);
    }

  }

  public static void renderTable(Map store, PageInfo<?> pageInfo, Class aclass) {
    List<?> list = pageInfo.getList();
    pageInfo.setList(null);
    getColumnNames(store,aclass);
    getTable(store,list,aclass);
  }

  public static void renderTable(Map store,List<?> list, Class aclass) {
    getColumnNames(store,aclass);
    getTable(store,list,aclass);
  }
}
