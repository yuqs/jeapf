package org.jeapf.framework.web.taglibs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jeapf.framework.security.entity.Menu;
import org.jeapf.framework.security.service.MenuManager;
import org.jeapf.framework.security.shiro.ShiroPrincipal;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * 系统首界面左栏导航菜单自定义标签
 * 该类继承RequestContextAwareTag，主要用于获取WebApplicationContext
 * @author yuqs
 */
public class MenuTag extends RequestContextAwareTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3041636263647268721L;
	//Spring的上下文
	private WebApplicationContext context;
	//Servlet的上下文
	private ServletContext sc = null;

	@Override
	protected int doStartTagInternal() throws Exception {
		//获取ServletContext
		sc = pageContext.getServletContext();
		//获取spring上下文
		context = getRequestContext().getWebApplicationContext();
		JspWriter writer = pageContext.getOut();
		if (context == null) {
			writer.write("获取菜单项失败");
		} else {
			StringBuffer buffer = new StringBuffer();
			//获取所有可允许访问的菜单列表
			List<Menu> menus = getAllowedAccessMenu();
			//循环迭代菜单列表，构成ID、List结构的Map
			Map<Long, List<Menu>> menuMaps = buildMenuTreeMap(menus);
			//根据Map构造符合左栏菜单显示的html
			buildMenuTreeFolder(buffer, menuMaps, Menu.ROOT_MENU);
			writer.write(buffer.toString());
		}
		return 0;
	}

	private Map<Long, List<Menu>> buildMenuTreeMap(List<Menu> menus) {
		Map<Long, List<Menu>> menuMap = new TreeMap<Long, List<Menu>>();
		for (Menu menu : menus) {
			/**
			 * 判断是否有上一级菜单，如果有，则添加到上一级菜单的Map中去 如果没有上一级菜单，把该菜单作为根节点
			 */
			Long parentMenuId = menu.getParentMenu() == null ? Menu.ROOT_MENU
					: menu.getParentMenu().getId();
			if (!menuMap.containsKey(parentMenuId)) {
				List<Menu> subMenus = new ArrayList<Menu>();
				subMenus.add(menu);
				menuMap.put(parentMenuId, subMenus);
			} else {
				List<Menu> subMenus = menuMap.get(parentMenuId);
				subMenus.add(menu);
				menuMap.put(parentMenuId, subMenus);
			}
		}
		return menuMap;
	}
	
	/**
	 * 获取当前登录账号所有允许访问的菜单列表
	 * @return
	 */
	private List<Menu> getAllowedAccessMenu() {
		Subject subject = SecurityUtils.getSubject();
		ShiroPrincipal principal = (ShiroPrincipal) subject.getPrincipal();
		MenuManager menuManager = context.getBean(MenuManager.class);
		return menuManager.getAllowedAccessMenu(principal.getId());
	}
	
	/**
	 * 构建菜单目录
	 * @param buffer html信息
	 * @param menuMap
	 * @param menuId
	 */
	private void buildMenuTreeFolder(StringBuffer buffer, Map<Long, List<Menu>> menuMap, Long menuId)
	{
		List<Menu> treeFolders = menuMap.get(menuId);
		if(treeFolders == null)
		{
			return;
		}
		for(Menu menu : treeFolders)
		{
			buffer.append("<div class='subnav'>");
			buffer.append("<div class='subnav-title'>");
			buffer.append("<a href='#' class='toggle-subnav'><i class='icon-angle-down'></i><span>" + menu.getName() + "</span></a>");
			buffer.append("</div>");
			buffer.append("<ul class='subnav-menu'>");
			List<Menu> treeNodes = menuMap.get(menu.getId());
			/**
			 * 有子菜单时，将子菜单添加到当前节点上
			 */
			buildMenuTreeNode(buffer, treeNodes);
			buffer.append("</ul></div>");
		}
	}
	
	/**
	 * 循环子菜单资源，并构造tree型html语句
	 * @param buffer
	 * @param treeNodes
	 */
	private void buildMenuTreeNode(StringBuffer buffer, List<Menu> treeNodes)
	{
		if(treeNodes == null)
		{
			return;
		}
		for(Menu menu : treeNodes)
		{
			buffer.append("<li id='menu_" + menu.getId() + "'><a href='");
			buffer.append(sc.getContextPath());
			buffer.append(menu.getDescription());
			buffer.append("' target='mainFrame' ");
			buffer.append(" onclick='refreshMenuFocus(this);'>");
			buffer.append(menu.getName());
			buffer.append("</a></li>");
		}
	}
}
