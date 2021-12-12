package com.coder.blog.controller.admin;

import com.coder.blog.Utils.RespMessageUtils;
import com.coder.blog.service.BlogService;
import com.coder.commom.annotation.Enum.BlogType;
import com.coder.commom.annotation.Enum.ResourceType;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author coder
 * @Date 2021/12/11 0:14
 * @Description
 */
@Controller
@RequestMapping("/admin/blogType")
@Api(value = "管理员的博客类型模块")
@Data
public class BlogTypeController {


  private final BlogService blogService;

  public BlogTypeController(BlogService blogService) {
    this.blogService = blogService;
  }

  @ApiOperation(value="获取各博客的数量占比",httpMethod = "GET")
  @ResourceAcquisitionRecorder(resourceType = ResourceType.RECORD,name="获取各博客的数量占比", role = "admin")
  @RequestMapping(value = "/differentKind",method = RequestMethod.GET)
  @ResponseBody
  public String CountOfEachType(){
    Map<String, Object> map=  blogService.selectTypeCount(BlogType.class);
    return RespMessageUtils.SUCCESS(map);
  }


}
