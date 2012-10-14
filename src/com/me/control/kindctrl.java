package com.me.control;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("kindctrl")
public class kindctrl
{

    @RequestMapping("show_simple")
    public ModelAndView show_simple()
    {
        ModelAndView mod = new ModelAndView();
        mod.setViewName("show_simple");
        return mod;
    }
    
    @RequestMapping("show_upload_file")
    public ModelAndView show_upload_file()
    {
        ModelAndView mod = new ModelAndView();
        mod.setViewName("show_upload_file");
        return mod;
    }


 
}
