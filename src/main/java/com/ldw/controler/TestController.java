package com.ldw.controler;

import com.ldw.service.ITestService;
import com.ldw.util.CustomAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private ITestService ts;
    private Logger logger= LoggerFactory.getLogger(TestController.class);
    @RequestMapping("test1")
    @CustomAnnotation
    public String test() {
        System.out.println(ts.getClass().getName());
//          List<Map<String, Object>> list = ts.select();
//        System.out.println(ts.getClass().getName() + " 22222");
//        ts.insertBySql("insert into PRACTISE values(110,'小李飞刀'，22，sysdate，'故宫')");
        return "helloworld";

    }

    @RequestMapping("demo1")
    public void demo1(HttpServletResponse response) throws IOException {
        System.out.println("进入到ajax的底层代码中");
        response.setContentType("text/html,charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("abcdefg");
        out.flush();
        out.close();
    }

    /**
     *ajax验证登录用户
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("userLoad")
    public String userLoad(HttpServletRequest request) {
        logger.warn("hahahahahahahah");
        logger.warn(logger.getClass().getClassLoader().getClass().getName().toString());

        String username = request.getParameter("email");
        String password = request.getParameter("password");
        if ("ludangwei".equals(username) && "123456".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user","true");
            return "true";
        }
        return "false";
    }

    /**
     *验证是否为越权操作
     * @param request
     * @return
     */
    @RequestMapping("gohomepage")
    public String goHomePage(HttpServletRequest request){
        String user = (String)request.getSession().getAttribute("user");
        if(user!=null){
            return "chatroom";
        }
        return "error";
    }
}
