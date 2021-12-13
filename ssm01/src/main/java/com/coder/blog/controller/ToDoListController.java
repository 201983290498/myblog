package com.coder.blog.controller;

import com.coder.blog.Utils.RespMessageUtils;
import com.coder.blog.entity.ToDo;
import com.coder.blog.entity.User;
import com.coder.blog.service.ToDoService;
import com.coder.commom.annotation.Enum.ResourceType;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO role:user+
 * @Author coder
 * @Date 2021/12/12 9:51
 * @Description
 */
@Controller
@Api("代做事项模块")
@RequestMapping("/todo")
public class ToDoListController {

  private final ToDoService toDoService;

  public ToDoListController(ToDoService toDoService) {
    this.toDoService = toDoService;
  }


  @ResponseBody
  @ApiOperation(value = "获取todoList的分页列表",notes = "可以选择已做和普通事件",httpMethod = "POST")
  @ResourceAcquisitionRecorder(resourceType = ResourceType.RECORD,name = "todoList记录")
  @PostMapping("/list/{page}")
  public String getToDoList(HttpServletRequest request, @ApiParam(value = "页码") @PathVariable("page") int page,
                            @ApiParam(value = "获取代做的事项") @RequestParam(value = "status")Integer status){
    Map<String,Object> conditions = new HashMap<>();
    User user = (User) request.getSession().getAttribute("user");
    if(status != null){
      //获取todo的状态和todo的拥有者
      conditions.put("status",status);
    }
    conditions.put("owner", user.getUsername());
    PageInfo<ToDo> toDoPageInfo = toDoService.selectList(conditions, page, ToDo.pageSize);
    return RespMessageUtils.SUCCESS(toDoPageInfo);
  }

  @ResponseBody
  @ApiOperation(value = "更新待做事项",httpMethod = "POST")
  @ResourceAcquisitionRecorder(resourceType = ResourceType.MODIFY,name = "更新todo事项")
  @PostMapping("/update")
  public String updateToDo(ToDo todo){
    toDoService.update(todo);
    return RespMessageUtils.SUCCESS(todo);
  }

  /**
   * TODO check  role:user+
   */
  @SneakyThrows
  @PostMapping("/insert")
  @ApiOperation(value = "插入待做事项", notes = "插入之后刷新整个事项", httpMethod = "POST")
  @ResourceAcquisitionRecorder(resourceType = ResourceType.MODIFY,name = "插入todo事项")
  @ResponseBody
  public String insert(@RequestParam("info") String info,@RequestParam("status") Integer status,
                       @RequestParam("deadline") String date,HttpServletRequest request){
    User user = (User)request.getSession().getAttribute("user");
    ToDo todo = new ToDo();
    SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    Date parse = sdf.parse(date);
    todo.setStatus(status);
    todo.setInfo(info);
    todo.setDeadline(parse);
    todo.setOwner(user.getUsername());
    todo.setAddTime(new Date());
    toDoService.insert(todo);
    //插入事项，然后返回第一页
    PageInfo<ToDo> toDoPageInfo = toDoService.selectList(null, 1, ToDo.pageSize);
    return RespMessageUtils.SUCCESS(toDoPageInfo);
  }

  @GetMapping("/one")
  @ResponseBody
  @ResourceAcquisitionRecorder(resourceType = ResourceType.RECORD, name="获取一则todo事项")
  @ApiOperation(value="获取一则todo事项",httpMethod = "GET")
  public String getToDo(@ApiParam(name="id",required = true)Long id, HttpServletRequest request){
    ToDo toDo = toDoService.selectOne(id);
    User user = (User) request.getSession().getAttribute("user");
    if(toDo.getOwner().equals(user.getUsername())){
      return RespMessageUtils.SUCCESS(toDo);
    }else{
      return RespMessageUtils.ERROR("资源不属于您,获取失败。");
    }
  }
}
