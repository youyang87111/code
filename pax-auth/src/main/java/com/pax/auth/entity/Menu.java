package com.pax.auth.entity;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AuthBaseEntity {
	
	private int				id;
	private Site			site;
	private String			name;
	private String			url;
	private int				orderno;
	private String			img;
	private Menu			pmenu;
	private List<Menu>		list	= new ArrayList<Menu>();
	
	//在权限选择功能时，先展示菜单，然后展示菜单对应中可选择的功能（这个功能为操作者所拥有的功能，而不是这个菜单所对应的所有功能）
	private List<Function>	funcs	= new ArrayList<Function>();
	
	//在角色选择权限时，先展示菜单，然后展示菜单对应中可选择的权限（这个权限为操作者所拥有的权限，而不是这个菜单所对应的所有权限）
	private List<Authority>	auths	= new ArrayList<Authority>();
	
	public Site getSite() {
		return site;
	}
	
	public void setSite(Site site) {
		this.site = site;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getOrderno() {
		return orderno;
	}
	
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public Menu getPmenu() {
		return pmenu;
	}
	
	public void setPmenu(Menu pmenu) {
		this.pmenu = pmenu;
	}
	
	public List<Menu> getList() {
		return list;
	}
	
	public void setList(List<Menu> list) {
		this.list = list;
	}
	
	public List<Function> getFuncs() {
		return funcs;
	}
	
	public void setFuncs(List<Function> funcs) {
		this.funcs = funcs;
	}
	
	public List<Authority> getAuths() {
		return auths;
	}
	
	public void setAuths(List<Authority> auths) {
		this.auths = auths;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Menu other = (Menu) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
