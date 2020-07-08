package com.init.index.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

//需要继承AuthorizingRealm
public class Shiro_Realm extends AuthorizingRealm {
    //配置加密，一旦使用MD5校验，就一定得配置该类
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        //HashedCredentialsMatcher是shiro提供的解析盐的实现类
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //设置算法:采用的算法是md5
        matcher.setHashAlgorithmName("md5");
        //设置加密次数，和注册的要一致
        matcher.setHashIterations(3);
        super.setCredentialsMatcher(matcher);
    }

    /*
     * 功能:给用户授权
     * 注意:授权是在验证之后进行的
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Set<String> set = new HashSet();
        set.add("user:add");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(set);
        return info;
    }

    /*
    * 功能:验证用户账号密码
    *
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
