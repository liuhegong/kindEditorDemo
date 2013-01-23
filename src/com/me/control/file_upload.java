package com.me.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.me.ut.WebPath;

@Controller
@RequestMapping("file_upload")
public class file_upload {

    //处理kindEditor上传请求的方法
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/doimgupload")
    public @ResponseBody
    Map handlerKindEditorImg(@RequestParam("imgFile") MultipartFile file,
                             @RequestParam("localUrl") String localUrl,
                             HttpServletResponse response)
    {
        ModelAndView mod = new ModelAndView();
        String rootFoleder = WebPath.getClassRootPath() + "kindupload";
        if (!(new File(rootFoleder).exists()))
        {
            new File(rootFoleder).mkdir();
        }

        File outfile = null;
        OutputStream output = null;
        try
        {
            // MultipartFile是对当前上传的文件的封装a
            if (!file.isEmpty())
            {
                response.setHeader("Content-Disposition",
                                   "inline; filename=file.jpg");

                localUrl = new String(file.getOriginalFilename()
                        .getBytes("ISO-8859-1"), "UTF-8");
                System.out.println("上传的文件的文件名是：" + (localUrl));

                String filepath = rootFoleder + "/" + localUrl;
                outfile = new File(filepath);
                output = new FileOutputStream(outfile);
                IOUtils.copy(file.getInputStream(),
                             output);
                // store the bytes somewhere
                // 在这里就可以对file进行处理了，可以根据自己的需求把它存到数据库或者服务器的某个文件夹
            }
            else
            {
            	System.out.println("上传img失败");
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	// 此处一定要关闭
            if (output != null)
            {
                try
                {
                    output.close();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
            // 如果上传过程中出现异常：告知前台错误
            mod.setViewName("fieldtest");
            mod.addObject("fielderr",
                          "上传文件" + localUrl + "的时候出现错误");
        }
        finally
        {
            // 此处一定要关闭
            if (output != null)
            {
                try
                {
                    output.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        Map out = new HashMap();
        out.put("error",
                0);
        out.put("url",
                WebPath.SYS_PATH + "/file_upload/downimg.do?fileName=" + localUrl
                        + "");
        return out;
    }
	
	  /**
     * 
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("downimg")
    public void downimg(@RequestParam("fileName") String fileName,
                        HttpServletRequest request,
                        HttpServletResponse response)
                                                     throws UnsupportedEncodingException
    {

        fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("content-type",
                             "text/html;charset=UTF-8");

        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;

        String folePath = WebPath.getClassRootPath() + "kindupload/";

//         现在全用base64进行编码传递，因为前端js对url编码支持不好
//         fileName = Base64UT.decode(fileName);
//         fileName = URLDecoder.decode(fileName, "UTF-8");

        String downLoadPath = folePath + fileName;
        System.out.println(downLoadPath);

        // 不存在返回异常
        if (!(new File(downLoadPath).exists()))
        {
            System.out.println("文件不存在");
        }

        try
        {
            long fileLength = new File(downLoadPath).length();
            // response.setContentType("application/x-msdownload;");
            // response.setHeader(
            // "Content-disposition",
            // "attachment; filename=" + new String(fileName.getBytes(),
            // "ISO8859-1"));// 下载文件的时候

            response.setContentType("image/jpeg");
            // image/gif image/x-png
            response.setHeader("Content-Disposition",
                               "inline; filename="
                                       + new String(
                                                    fileName.getBytes(),
                                                        "ISO8859-1"));

            // 空格被替换成了_
            // ,是因为ie的问题
            response.setHeader("Content-Length",
                               String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff,
                                               0,
                                               buff.length)))
            {
                bos.write(buff,
                          0,
                          bytesRead);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bis != null)
                try
                {
                    bis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            if (bos != null)
                try
                {
                    bos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
        }

    }


}
