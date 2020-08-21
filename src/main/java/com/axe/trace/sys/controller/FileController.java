package com.axe.trace.sys.controller;

import com.axe.trace.sys.config.FileUploadConfig;
import com.axe.trace.sys.util.AjaxJson;
import com.axe.trace.sys.util.FileUtils;
import com.axe.trace.sys.util.StringUtils;
import com.axe.trace.sys.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

@RestController
@RequestMapping("/file")
@Api(value = "文件Controller", tags = "文件接口")
public class FileController extends BaseController {

    @PostMapping("/fileUpload")
    @ResponseBody
    @ApiOperation(value = "文件上传")
    public AjaxJson fileUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile[] files) {
        AjaxJson ajaxJson = new AjaxJson();

        StringBuffer allPath = new StringBuffer();
        StringBuffer error = new StringBuffer();
        // 文件保存目录
        String fileDir = null;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                // 原始文件名
                String oldName = file.getOriginalFilename();
                // 文件类型
                String fileType = FileUtils.getFileType(oldName);
                // 视频文件
                if (fileType.equals("video")) {
                    fileDir = FileUploadConfig.UPLOAD_BASE_PATH + "video" + File.separator + year + File.separator + month + File.separator;
                }
                // 图片文件
                else if (fileType.equals("image")) {
                    fileDir = FileUploadConfig.UPLOAD_BASE_PATH + "image" + File.separator + year + File.separator + month + File.separator;
                }
                else {
                    error.append(oldName + "不支持该文件类型; ");
                }
                // 在原始文件名前拼接UUID防止重复
                String newName = fileDir + UUIDUtils.getUUID() + oldName;
                File newFile = new File(newName);
                File fileParent = newFile.getParentFile();
                if (!fileParent.exists()) {
                    fileParent.mkdirs();
                }
                try {
                    file.transferTo(newFile);
                    allPath.append(StringUtils.replace(newName, FileUploadConfig.UPLOAD_BASE_PATH, FileUploadConfig.UPLOAD_VIRTURAL_PATH));
                    // 多个文件名用"|"分隔
                    allPath.append("|");
                } catch (IOException e) {
                    e.printStackTrace();
                    error.append(oldName + "上传失败; ");
                }
            }
        }

        if (StringUtils.isBlank(error)) {
            ajaxJson.setMsg("所有文件上传成功");
        } else {
            ajaxJson.setMsg(error.toString());
        }
        ajaxJson.getBody().put("result", allPath.substring(0, allPath.length()-1));
        return ajaxJson;
    }

    @PostMapping("/fileDelete")
    @ResponseBody
    @ApiOperation(value = "文件删除")
    public AjaxJson fileDelete(String fileName) {
        AjaxJson ajaxJson = new AjaxJson();
        if (FileUtils.delFile(fileName)) {
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("删除文件成功");
        } else {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("删除文件失败");
        }
        return ajaxJson;
    }

}
