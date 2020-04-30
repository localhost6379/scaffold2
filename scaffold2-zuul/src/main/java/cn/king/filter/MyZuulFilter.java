package cn.king.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/5 18:54
 * @version: 1.0.0
 * @description: 网关过滤
 */
//@Component
public class MyZuulFilter extends ZuulFilter {

    // 路由之前过滤
    private static final String PRE = "pre";
    // 路由之时
    private static final String ROUTING = "routing";
    // 路由之后
    private static final String POST = "post";
    // 发送错误的时候
    private static final String ERROR = "error";

    @Override
    public String filterType() {
        return PRE;
    }

    // 指定过滤器的调用顺序, Zuul过滤器我们可以写多个, 然后按照顺序调用
    @Override
    public int filterOrder() {
        return 0;
    }

    // 是否需要过滤. 肯定需要啊true  这个叫过滤开关
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // 过滤条件
    @Override
    public Object run() throws ZuulException {
        //获取当前线程请求上下文类. 可以获取Request对象和Response对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取request域对象 获取Request对象
        HttpServletRequest request = currentContext.getRequest();
        //获取参数  获取请求参数
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user == null) {
            //设置响应域对象
            // 下面代表不让请求通过网关, 相当于过滤器拦截请求了
            currentContext.setSendZuulResponse(false);
            // 设置http响应码 401 没有权限
            currentContext.setResponseStatusCode(401);
            //获取response   获取Response对象
            HttpServletResponse response = currentContext.getResponse();
            try {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("用户未登录,无法访问服务,请先登录 -- zuul filter");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

}
