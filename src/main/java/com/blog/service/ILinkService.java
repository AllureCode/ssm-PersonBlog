package com.blog.service;

import com.blog.domain.Link;

import java.util.List;
import java.util.Map;

public interface ILinkService {
    /**
     * 根据链接id查询
     */
    Link findByLinkId(Integer id);

    /**
     * 自定义条件查询
     * @param map
     * @return
     */
    List<Link> findLinkList(Map<String ,Object> map);

    /**
     * 查询链接总条数
     * @param map
     * @return
     */
   Long getLinkTotal(Map<String,Object> map);
    /**
     * 添加链接
     * @param link
     * @return
     */
    Integer addLink(Link link);
    /**
     * 更新链接
     * @param link
     * @return
     */
    Integer updateLink(Link link);

    /**
     * 删除链接
     * @param id
     * @return
     */
    Integer deleteLink(Integer id);
}
