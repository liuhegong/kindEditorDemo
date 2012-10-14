package com.me.control;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.me.domain.VOPerson;


@Controller
@RequestMapping("l")
public class l
{

    @RequestMapping("logon")
    public ModelAndView logon()
    {
        ModelAndView mod = new ModelAndView();
        mod.setViewName("login");
        return mod;
    }


   
    
    

    @SuppressWarnings("unchecked")
    @RequestMapping("testForm")
    public ModelAndView testForm(VOPerson vo,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
    {
        ModelAndView mod = new ModelAndView();

        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements())
        {
            Object object = (Object) e.nextElement();
            System.out.println(object);
        }

        System.out.println("----\r\n 下面是response");
        Enumeration e2 = request.getAttributeNames();
        while (e2.hasMoreElements())
        {
            Object object = (Object) e2.nextElement();
            System.out.println(object);
        }
        
        System.out.println("\r\n");
        System.out.println(request.getCharacterEncoding());
        System.out.println("----\r\n 下面是第三种");
        
        Enumeration e3 = request.getLocales();
        while (e3.hasMoreElements())
        {
            Object object = (Object) e3.nextElement();
            System.out.println(object);
        }
        
        System.out.println("----\r\n 下面是第4种");
        Enumeration e4 = request.getHeaderNames();
        while (e4.hasMoreElements())
        {
            Object object = (Object) e4.nextElement();
            System.out.println(object);
        }
        
        
        mod.setViewName("login");
        return mod;
    }

}
