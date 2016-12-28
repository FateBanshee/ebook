package com.GB.ebook.entity;

public class Role {
	
	private int roleId;
	private String roleName;
	private int  sysAccountManage=0;
	private int  sysPrivilegeSetting=0;
	private int  personalAccontManage=0;
	private int  personalPassword=0;
	private int  personalTeacherApprove=0;
	private int  registerCourse=0;
	private int  personalCourse=0;
	private int  listCourse=0;
	private int  queryCourse=0;
	
	private String description;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getSysAccountManage() {
		return sysAccountManage;
	}

	public void setSysAccountManage(int sysAccountManage) {
		this.sysAccountManage = sysAccountManage;
	}

	public int getSysPrivilegeSetting() {
		return sysPrivilegeSetting;
	}

	public void setSysPrivilegeSetting(int sysPrivilegeSetting) {
		this.sysPrivilegeSetting = sysPrivilegeSetting;
	}

	public int getPersonalAccontManage() {
		return personalAccontManage;
	}

	public void setPersonalAccontManage(int personalAccontManage) {
		this.personalAccontManage = personalAccontManage;
	}

	public int getPersonalPassword() {
		return personalPassword;
	}

	public void setPersonalPassword(int personalPassword) {
		this.personalPassword = personalPassword;
	}

	public int getPersonalTeacherApprove() {
		return personalTeacherApprove;
	}

	public void setPersonalTeacherApprove(int personalTeacherApprove) {
		this.personalTeacherApprove = personalTeacherApprove;
	}

	public int getRegisterCourse() {
		return registerCourse;
	}

	public void setRegisterCourse(int registerCourse) {
		this.registerCourse = registerCourse;
	}

	public int getPersonalCourse() {
		return personalCourse;
	}

	public void setPersonalCourse(int personalCourse) {
		this.personalCourse = personalCourse;
	}

	public int getListCourse() {
		return listCourse;
	}

	public void setListCourse(int listCourse) {
		this.listCourse = listCourse;
	}

	public int getQueryCourse() {
		return queryCourse;
	}

	public void setQueryCourse(int queryCourse) {
		this.queryCourse = queryCourse;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + listCourse;
		result = prime * result + personalAccontManage;
		result = prime * result + personalCourse;
		result = prime * result + personalPassword;
		result = prime * result + personalTeacherApprove;
		result = prime * result + queryCourse;
		result = prime * result + registerCourse;
		result = prime * result + roleId;
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + sysAccountManage;
		result = prime * result + sysPrivilegeSetting;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (listCourse != other.listCourse)
			return false;
		if (personalAccontManage != other.personalAccontManage)
			return false;
		if (personalCourse != other.personalCourse)
			return false;
		if (personalPassword != other.personalPassword)
			return false;
		if (personalTeacherApprove != other.personalTeacherApprove)
			return false;
		if (queryCourse != other.queryCourse)
			return false;
		if (registerCourse != other.registerCourse)
			return false;
		if (roleId != other.roleId)
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		if (sysAccountManage != other.sysAccountManage)
			return false;
		if (sysPrivilegeSetting != other.sysPrivilegeSetting)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", sysAccountManage=" + sysAccountManage
				+ ", sysPrivilegeSetting=" + sysPrivilegeSetting
				+ ", personalAccontManage=" + personalAccontManage
				+ ", personalPassword=" + personalPassword
				+ ", personalTeacherApprove=" + personalTeacherApprove
				+ ", registerCourse=" + registerCourse + ", personalCourse="
				+ personalCourse + ", listCourse=" + listCourse
				+ ", queryCourse=" + queryCourse + ", description="
				+ description + "]";
	}
	
	
	
	
	
}
