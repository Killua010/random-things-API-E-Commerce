package br.com.randomthings.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.command.ICommand;
import br.com.randomthings.controller.utils.HttpResponseEntity;
import br.com.randomthings.utils.Result;
import br.com.randomthings.viewhelper.EntityViewHelper;

@CrossOrigin
@RestController
public abstract class AbstractController<viewHelper extends EntityViewHelper> {

	@Autowired
	protected viewHelper vh;

	@Autowired
	protected List<ICommand> commands;

	@Autowired
	protected HttpResponseEntity standardResponse;

	@PostMapping
	public @ResponseBody ResponseEntity<Result> save(@Valid @RequestBody viewHelper vh) {
		Result result = searchCommand("Save").execute(vh.getEntity());
		if (null != result.getResponse() && !result.getResponse().toString().isEmpty()) {
			result.setHttpStatus(400);
		} else {
			result.setHttpStatus(201);
		}
		return restResponse(result);
	}

	@GetMapping({"", "/{id}"})
    public @ResponseBody ResponseEntity<Result> find(@PathVariable(value="id",required=false) Long id){
		vh = createEntity();
		Result result = searchCommand("Find").execute(vh.getEntity(id));
		if(null != result.getResponse() && !result.getResponse().toString().isEmpty()) {
			result.setHttpStatus(404);
		} else {
			result.setHttpStatus(200);
		}
		return restResponse(result);
    }

	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody viewHelper vh, @PathVariable Long id) {
		Result result = searchCommand("Update").execute(vh.getEntity(id));
		if (null != result.getResponse() && !result.getResponse().toString().isEmpty()) {
			result.setHttpStatus(400);
		} else {
			result.setHttpStatus(200);
		}
		return restResponse(result);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable Long id) {
		vh = createEntity();
		Result result = searchCommand("Delete").execute(vh.getEntity(id));
		result.setHttpStatus(204);
		return restResponse(result);
	}

	private viewHelper createEntity() {
		Object object = null;
		try {
			Class classDefinition = Class.forName(vh.getClass().getName());
			object = classDefinition.newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}
		return (viewHelper) object;
	}

	protected ICommand searchCommand(String operation) {
		return commands.stream().filter(cmd -> 
				(cmd.getClass().getName().toUpperCase().contains(operation.toUpperCase()))).findAny().orElse(null);
	}

	protected ResponseEntity<Result> restResponse(Result result) {
		for (Method method : this.standardResponse.getClass().getMethods()) {
			if (method.getName().contains(result.getHttpStatus().toString())) {
				try {
					return (ResponseEntity<Result>) method.invoke(this.standardResponse, result);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
