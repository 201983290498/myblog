package com.coder.blog.controller;

import com.coder.blog.Utils.RespMessageUtils;
import com.coder.blog.service.BlogService;
import com.coder.commom.annotation.Enum.ResourceType;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author coder
 * @Date 2021/12/10 12:46
 * @Description
 */
@Controller
@RequestMapping("/blog")
@Api("博客相关的操作")
public class BlogController {

  private final BlogService blogService;

  public BlogController(BlogService blogService) {
    this.blogService = blogService;
  }

  /**
   * TODO role:admin fun: 按季度获取博文
   */
  @ResponseBody
  @RequestMapping(value = "/getCountByMonth", method = RequestMethod.GET)
  @ResourceAcquisitionRecorder(resourceType = ResourceType.RECORD, role = "admin", name = "按季度获取博文的数量")
  @ApiOperation(value = "按季度获取博文的数量", httpMethod = "GET")
  public String getCountByMonth(){
    Map<String, Object> stringObjectMap = blogService.selectCountByMonth();
    return RespMessageUtils.SUCCESS(stringObjectMap);
  }

  /**
   * TODO role:admin
   */
  @RequestMapping(value="/list", method = RequestMethod.GET)
  @ResourceAcquisitionRecorder(resourceType = ResourceType.HTML, role = "admin", name = "获取blogTable页面")
  @ApiOperation(value = "获取blogTable页面", httpMethod = "GET")
  public String blogTable(ModelMap map, HttpServletRequest request){
    map.addAttribute("tableName","BlogTable");
    return "/admin/blogTable";
  }
}
