package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "addServlet",urlPatterns = {"/addServlet/*"})
public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String where = request.getRequestURI();
        String[] List = where.split("/");
        if(List.length != 4){
                out.println("请重新输入请求");
        }
        else{

                String str= "^a=(-?[1-9]\\d*|0)&b=(-?[1-9]\\d*|0)$";
            //正则表达式匹配整数
            Pattern pattern = Pattern.compile(str);
            Matcher m = pattern.matcher(List[3]);
            boolean flag = false;
            while(m.find()){
                    flag = true;
                Integer a = Integer.parseInt(m.group(1));
                Integer b = Integer.parseInt(m.group(2));
                request.getSession().setAttribute("a",a);
                request.getSession().setAttribute("b",b);
            }
            if(!flag){
                    out.println("参数错误，请重新输入");
            }
            else{
                    request.getRequestDispatcher("/doneServlet").forward(request,response);
            }
        }
    }
}
