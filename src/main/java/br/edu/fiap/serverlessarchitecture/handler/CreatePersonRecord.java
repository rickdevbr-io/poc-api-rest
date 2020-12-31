package br.edu.fiap.serverlessarchitecture.handler;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.fiap.serverlessarchitecture.controller.HandlerRequest;
import br.edu.fiap.serverlessarchitecture.controller.HandlerResponse;
import br.edu.fiap.serverlessarchitecture.model.Person;
import br.edu.fiap.serverlessarchitecture.model.PersonRepository;

public class CreatePersonRecord implements RequestHandler<HandlerRequest, HandlerResponse> {
	
	private final PersonRepository repository = new PersonRepository();

	@Override
	public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {

		Person person = null;
		try {
			person = new ObjectMapper().readValue(request.getBody(), Person.class);
		} catch (IOException e) {
			return HandlerResponse.builder().setStatusCode(400).setRawBody("There is a error in your Person!").build();
		}
		context.getLogger().log("Creating a new person record with " + person.getName());
		final Person personRecorded = repository.save(person);
		return HandlerResponse.builder().setStatusCode(201).setObjectBody(personRecorded).build();
	}
}