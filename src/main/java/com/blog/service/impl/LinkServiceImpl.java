package com.blog.service.impl;

import com.blog.dao.ILinkDao;
import com.blog.domain.Link;
import com.blog.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("linkService")
public class LinkServiceImpl implements ILinkService {
    @Autowired
    private ILinkDao iLinkDao;
    @Override
    public Link findByLinkId(Integer id) {
        return iLinkDao.findByLinkId(id);
    }

    @Override
    public List<Link> findLinkList(Map<String, Object> map) {
        return iLinkDao.findLinkList(map);
    }

    @Override
    public Long getLinkTotal(Map<String, Object> map) {
        return iLinkDao.getLinkTotal(map);
    }

    @Override
    public Integer addLink(Link link) {
        return iLinkDao.addLink(link);
    }

    @Override
    public Integer updateLink(Link link) {
        return iLinkDao.updateLink(link);
    }

    @Override
    public Integer deleteLink(Integer id) {
        return iLinkDao.deleteLink(id);
    }
}
