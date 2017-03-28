package com.pmcc.utils;

import java.util.HashSet;
import java.util.Set;

public class OnlineUser
{

    public OnlineUser()
    {
        roleIds = new HashSet();
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getUserId()
    {
        if(userId == null)
            userId = "00000000000000000000";
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getDepId()
    {
        return depId;
    }

    public void setDepId(String depId)
    {
        this.depId = depId;
    }

    public Set getRoleIds()
    {
        return roleIds;
    }

    public void setRoleIds(Set roleIds)
    {
        this.roleIds = roleIds;
    }

    public String getUserEName()
    {
        return userEName;
    }

    public void setUserEName(String userEName)
    {
        this.userEName = userEName;
    }

    public String getUserCName()
    {
        if(userCName == null)
            userCName = "\u7CFB\u7EDF\u7BA1\u7406\u5458";
        return userCName;
    }

    public void setUserCName(String userCName)
    {
        this.userCName = userCName;
    }

    public String getDepCName()
    {
        return depCName;
    }

    public void setDepCName(String depCName)
    {
        this.depCName = depCName;
    }

    public String getDepEName()
    {
        return depEName;
    }

    public void setDepEName(String depEName)
    {
        this.depEName = depEName;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    
    public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}


	private String sessionId;
    private String userId;
    private String userEName;
    private String userCName;
    private String depCName;
    private String depEName;
    private String depId;
    private Set roleIds;
    private String ip;
    private String sysCode;
}

