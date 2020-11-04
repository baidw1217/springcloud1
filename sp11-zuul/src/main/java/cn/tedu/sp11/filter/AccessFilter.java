package cn.tedu.sp11.filter;

import cn.tedu.sp01.web.util.JsonResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {

        RequestContext ctx = RequestContext.getCurrentContext().getCurrentContext();
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);


        return "item-service".equalsIgnoreCase(serviceId);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)){
            ctx.setSendZuulResponse(false);
            String json = JsonResult.err().code(JsonResult.NOT_LOGIN).msg("汉字not login").toString();
            ctx.setResponseStatusCode(JsonResult.NOT_LOGIN);
            ctx.addZuulResponseHeader("Content-Type","application/json;charset=UTF-8");
            ctx.setResponseBody(json);
        }
        return null;
    }
}
