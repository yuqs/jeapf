package org.jeapf.framework.config.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jeapf.framework.config.entity.Config;
import org.jeapf.framework.config.service.ConfigManager;
import org.jeapf.framework.orm.Page;
import org.jeapf.framework.orm.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 配置管理Controller
 * @author yuqs
 */
@Controller
@RequestMapping(value = "/config/configuration")
public class ConfigController {
	//注入配置管理对象
	@Autowired
	private ConfigManager configManager;
	
	/**
	 * 分页查询配置，返回配置列表视图
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Page<Config> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = configManager.findPage(page, filters);
		model.addAttribute("page", page);
		return "config/configList";
	}
	
	/**
	 * 新建配置时，返回配置编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("configuration", new Config());
		return "config/configEdit";
	}

	/**
	 * 编辑配置时，返回配置编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Config entity = configManager.get(id);
		model.addAttribute("configuration", entity);
		return "config/configEdit";
	}
	
	/**
	 * 编辑配置时，返回配置查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("configuration", configManager.get(id));
		return "config/configView";
	}
	
	/**
	 * 新增、编辑配置页面的提交处理。保存权限实体，并返回配置列表视图
	 * @param Authority
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Config config, Long[] orderIndexs) {
		configManager.save(config);
		return "redirect:/config/configuration";
	}
	
	/**
	 * 根据主键ID删除配置实体，并返回配置列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		configManager.delete(id);
		return "redirect:/config/configuration";
	}
}
