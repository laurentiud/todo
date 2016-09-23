package xwithy.ullink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import xwithy.ullink.repository.Todo;
import xwithy.ullink.repository.TodoDao;

@Controller
public class TodoController {

	@Autowired
	public TodoDao todoDao;

	@RequestMapping("/todo")
	public String showTodo(Model model, @RequestParam String hash) {
	    model.addAttribute("hash", hash);
	    model.addAttribute("messages", getTodos(hash));
	    return "todo";
	}

	@RequestMapping("/todo/new")
	public String showTodo(Model model) {
		String newHash = UUID.randomUUID().toString();
	    model.addAttribute("hash", newHash);
	    model.addAttribute("messages", getTodos(newHash));
	    return "todo";
	}
	
	private List<Todo> getTodos(String hash) {
	    return todoDao.findAllByHash(hash);  
	}
}