package xwithy.ullink.controller;

import java.util.UUID;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xwithy.ullink.repository.Todo;
import xwithy.ullink.repository.TodoDao;

@Controller
public class TodoController {

	@Autowired
	public TodoDao todoDao;

	@RequestMapping("/todo")
	public String showTodoList(HttpServletRequest request, Model model, @RequestParam(required = false) String hash) {
		if (StringUtils.isEmpty(hash))
			hash =  UUID.randomUUID().toString();

		model.addAttribute("hash", hash);
	    model.addAttribute("todos", todoDao.findAllByHash(hash));
		model.addAttribute("linkToTodo", request.getRequestURL().toString() + "?hash=" + hash);
	    return "todo";
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/todo/{hash}/{message}")
	@ResponseBody
	public long createTodo(@PathVariable String hash, @PathVariable String message) {
		return todoDao.save(new Todo(hash, message)).getId();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/todo/delete/{id}")
	@ResponseBody
	public boolean deleteTodo(@PathVariable Long id) {
		System.out.println("delete" + todoDao.findOne(id).getMessage());
		todoDao.delete(id);
		return true;
	}
}