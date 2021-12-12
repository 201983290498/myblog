package com.coder.blog.controller.admin;

import com.coder.blog.service.VisitRecordService;
import com.coder.commom.annotation.Enum.ResourceType;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author coder
 * @Date 2021/12/12 16:04
 * @Description
 */
@Controller
@Api("访问visitRecorder模块")
@RequestMapping(value = "/visitRecord")
public class VisitRecordController {

  private final VisitRecordService service;

  public VisitRecordController(VisitRecordService service) {
    this.service = service;
  }

  /**
   * TODO role:admin
   */
  @RequestMapping(value="/list", method = RequestMethod.GET)
  @ResourceAcquisitionRecorder(resourceType = ResourceType.HTML, role = "admin", name = "获取visitRecordTable页面")
  @ApiOperation(value = "获取visitRecordTable页面", httpMethod = "GET")
  public String visitRecordTable(ModelMap map, HttpServletRequest request){
    System.out.println(request.getSession().getAttribute("user"));
    return "/admin/visitRecordTable";
  }
}
