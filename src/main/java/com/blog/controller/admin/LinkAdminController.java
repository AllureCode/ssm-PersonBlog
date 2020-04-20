package com.blog.controller.admin;

import com.blog.domain.Link;
import com.blog.domain.PageBean;
import com.blog.service.ILinkService;
import com.blog.utils.ResponseUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {
    @Autowired
    private ILinkService iLinkService;

    /**
     * 链接列表查询
     * @param page
     * @param rows
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page",required = false) String page,
                       @RequestParam(value = "rows",required = false) String rows,
                       HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("start", pageBean.getStart());
        map.put("rows", pageBean.getPageSize());
        List<Link> linkList = iLinkService.findLinkList(map);
        Long linkTotal = iLinkService.getLinkTotal(map);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray=JSONArray.fromObject(linkList);
        jsonObject.put("rows", jsonArray);
        jsonObject.put("total", linkTotal);
        ResponseUtil.write(response, jsonObject);
        return null;
    }
    @RequestMapping("/save")
    public String save(Link link,HttpServletResponse response) throws Exception {
        Integer integer=0;
        if(link.getId()==null){
            integer = iLinkService.addLink(link);
        }else {
            integer=  iLinkService.updateLink(link);
        }
        JSONObject jsonObject = new JSONObject();
        if(integer>0){
            jsonObject.put("success", Boolean.TRUE);
        }else {
            jsonObject.put("success", Boolean.FALSE);
        }
        ResponseUtil.write(response, jsonObject);
        return null;
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids")String ids,HttpServletResponse response) throws Exception {
        String[] id = ids.split(",");
        Integer integer=0;
        for(int i=0;i<id.length;i++){
             integer = iLinkService.deleteLink(Integer.parseInt(id[i]));
        }
        JSONObject jsonObject = new JSONObject();
        if(integer>0){
            jsonObject.put("success", Boolean.TRUE);
        }
        ResponseUtil.write(response, jsonObject);
        return null;
    }

}
