package com.blog.realm;

import com.blog.domain.Blogger;
import com.blog.service.IBloggerService;
import com.blog.utils.CommonParamUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IBloggerService iBloggerService;
    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登陆验证
     * @param authenticationToken：基于用户和密码的标志
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从authenticationToken中获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        //让shiro去验证账号密码
        Blogger blogger = iBloggerService.findUsername(userName);
        if(blogger != null){
            SecurityUtils.getSubject().getSession().setAttribute(CommonParamUtils.CURRENT_USER, blogger);
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(blogger.getUserName(), blogger.getPassword(),getName());
            return authenticationInfo;
        }
        return null;
    }
}
