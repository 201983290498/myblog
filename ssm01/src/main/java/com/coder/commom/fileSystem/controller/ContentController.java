package com.coder.commom.fileSystem.controller;

import com.coder.commom.annotation.Enum.ApplicationType;
import com.coder.commom.annotation.Enum.ResourceType;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import com.coder.commom.fileSystem.entity.FileBase;
import com.coder.commom.fileSystem.service.ContentFileService;
import com.coder.commom.fileSystem.service.imp.FileServiceImp;
import com.coder.commonBase.Utils.RespMessageUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文件管理的控制类
 * @Author coder
 * @Date 2022/6/5 11:01
 * @Description
 */
@Controller
@RequestMapping(value = "/fileSystem/content")
@Slf4j
public class ContentController {

    private final ContentFileService contentService;

    private final FileServiceImp fileService;

    public ContentController(ContentFileService contentService, FileServiceImp fileService) {
        this.contentService = contentService;
        this.fileService = fileService;
    }

    @ResponseBody
    @PostMapping("/list")
    @ApiOperation(value="获取某个文件下的所有目录项", notes = "返回文件的目录项, 空则返回空列表", httpMethod = "POST")
    @ResourceAcquisitionRecorder(applicationType = ApplicationType.FILESYSTEM, resourceType = ResourceType.RECORD, name = "获取某个文件下的所有目录项")
//    public List<FileBase> FindDirList(@RequestParam(value="contentPath" ,required = false) String contentPath, @RequestParam(value = "username", required = false) String username){
    public RespMessageUtils FindDirList(@RequestBody Map<String, Object> data){
        log.info(data.toString());
        String contentPath = (String) data.get("contentPath");
        String username = (String) data.get("username");
        FileBase contentItem = fileService.selectOneByFilepathAndUsername(contentPath, username);
        return RespMessageUtils.SUCCESSOBJ(contentService.listDir(contentItem.getFileId()));
    }
}
