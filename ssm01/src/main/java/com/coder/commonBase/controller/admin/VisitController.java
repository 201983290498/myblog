package com.coder.commonBase.controller.admin;

import com.coder.commonBase.Utils.TableFieldUtils;
import com.coder.commonBase.entity.visit.Visit;
import com.coder.commonBase.service.VisitService;
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
 * @Date 2021/12/10 22:30
 * @Description
 */
@Controller
@Api("访客记录模块")
@RequestMapping("/visit")
public class VisitController {

  private final VisitService service;

  public VisitController(VisitService service) {
    this.service = service;
  }


  /**
   * TODO role:admin
   */
  @RequestMapping(value="/list", method = RequestMethod.GET)
  @ResourceAcquisitionRecorder(resourceType = ResourceType.HTML, role = "admin", name = "获取visitTable页面")
  @ApiOperation(value = "获取visitTable页面", httpMethod = "GET")
  public String visitTable(ModelMap map, HttpServletRequest request){
    map.addAttribute("tableName","VisitTable");
    map.addAttribute("tableName_zh","访客记录表");
    TableFieldUtils.renderTable(map,service.selectAll(), Visit.class);
    return "/admin/visitTable";
  }

}
