package com.tt.teach.controller;


import com.tt.teach.pojo.Student;
import com.tt.teach.service.StudentService;
import com.tt.teach.utils.BaseController;
import com.tt.teach.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

@Controller
@RequestMapping("/stu")
public class StudentController extends BaseController{
    @Resource
    private StudentService studentService;
    //接口:http://localhost:8080/stu/login
    @RequestMapping("/login")
    public String login() {
        return "/student/login";
    }
    //接口：http://localhost:8080/stu/index
    @RequestMapping("/index")
    public String index() {
        String studentName= (String) getSession().getAttribute(SESSION_KEY);
        if (studentName!=null){
            return "/student/index";
        }
       return REDIRECT+"/stu/login";
    }
    @RequestMapping("/student")
    public String student() {
        return "/student/student";
    }
    //接口:http://localhost:8080/stu/login
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin() {
        String xuehao=getRequest().getParameter("studentNo");
        Integer studentNo=Integer.parseInt(xuehao);
        String loginPwd=getRequest().getParameter("loginPwd");
        Student student=new Student();
        student.setStudentNo(studentNo);
        student.setLoginPwd(loginPwd);
        Student student1=studentService.doLogin(student);
        if(student1!=null){
            getSession().setAttribute("studentName",student1.getStudentName());
            return FORWARD+"/stu/index";
        }
        return REDIRECT+"/stu/login";
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout() {
        getSession().removeAttribute(SESSION_KEY);
        return REDIRECT+"/stu/login";
    }
    @RequestMapping(value = "/findStuAll",method = RequestMethod.GET)
    @ResponseBody
    public Object findStuAll(){
        List<Student> list=studentService.findStuAll();
        return list;
    }

    @PostMapping("/updateStu")
    public String updateStu() {
        String xuehao=getRequest().getParameter("studentNo");
        Integer studentNo=Integer.parseInt(xuehao);
        String studentName=getRequest().getParameter("studentName");
        String loginPwd=getRequest().getParameter("loginPwd");
        String phone=getRequest().getParameter("phone");
        Student student=new Student();
        student.setStudentNo(studentNo);
        student.setLoginPwd(loginPwd);
        student.setStudentName(studentName);
        student.setPhone(phone);
        int result=studentService.updateStu(student);
        if(result>0){
            return FORWARD+"/stu/student";
        }
        return FORWARD+"/stu/student";
    }

    @RequestMapping(value = "/deleteStu/{studentNo}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteStu(@PathVariable Integer studentNo) {
        int result=studentService.deleteStu(studentNo);
        if (result>0){
            return JsonResult.ok("删除成功！",result);
        }
        return JsonResult.on("删除失败!",result);
    }
}
