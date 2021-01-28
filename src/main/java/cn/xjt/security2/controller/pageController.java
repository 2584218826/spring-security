package cn.xjt.security2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 内容：
 *
 * @author
 * @date 2020/10/7-21:26
 */

@Controller
public class pageController {

	@RequestMapping({"/","/index"})
	public String index()
	{
		return "welcome";
	}
	@RequestMapping("/tologin")
	public String login()
	{
		return "pages/login";
	}

	@RequestMapping("/level1/{id}")
	public String level1(@PathVariable int id)
	{
		return "pages/level1/"+id;
	}
	@RequestMapping("/level2/{id}")
	public String level2(@PathVariable int id)
	{
		return "pages/level2/"+id;
	}
	@RequestMapping("/level3/{id}")
	public String level3(@PathVariable int id)
	{
		return "pages/level3/"+id;
	}
}
