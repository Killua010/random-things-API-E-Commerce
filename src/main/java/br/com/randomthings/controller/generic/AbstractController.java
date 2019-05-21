package br.com.randomthings.controller.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.command.ICommand;
import br.com.randomthings.controller.utils.HttpResponseEntity;
import br.com.randomthings.dto.IDTO;
import br.com.randomthings.utils.Result;

@CrossOrigin
@RestController
public abstract class AbstractController<DtoEntity extends IDTO> {

	@Autowired
	protected DtoEntity dtoEntity;

	@Autowired
	protected List<ICommand> commands;

	@Autowired
	protected HttpResponseEntity standardResponse;

	@PostMapping
	public @ResponseBody ResponseEntity<Result> save(@Valid @RequestBody DtoEntity dto) {
		Result result = searchCommand("Save").execute(dto.fill());
		result.setHttpStatus(201);
		return restResponse(result);
	}

	@GetMapping({"", "/{id}"})
    public @ResponseBody ResponseEntity<Result> find(@PathVariable(value="id",required=false) Long id){
		IDTO dto = InstaceDto();
		Result result = searchCommand("Find").execute(dto.fill(id));
		result.setHttpStatus(200);
		return restResponse(result);
    }

	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody DtoEntity dto, @PathVariable Long id) {
		Result result = searchCommand("Update").execute(dto.fill(id));
		result.setHttpStatus(200);
		return restResponse(result);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable Long id) {
		IDTO dto = InstaceDto();
		Result result = searchCommand("Delete").execute(dto.fill(id));
		result.setHttpStatus(204);
		return restResponse(result);
	}

	private IDTO InstaceDto() {
		IDTO dto = null;
		try {
			Class classDefinition = Class.forName(dtoEntity.getClass().getName());
			dto  = (IDTO) classDefinition.newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}
		return dto;
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
