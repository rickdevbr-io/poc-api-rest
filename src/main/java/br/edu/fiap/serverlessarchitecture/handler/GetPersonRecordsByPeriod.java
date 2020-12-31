package br.edu.fiap.serverlessarchitecture.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.edu.fiap.serverlessarchitecture.controller.HandlerRequest;
import br.edu.fiap.serverlessarchitecture.controller.HandlerResponse;
import br.edu.fiap.serverlessarchitecture.model.Person;
import br.edu.fiap.serverlessarchitecture.model.PersonRepository;

public class GetPersonRecordsByPeriod implements RequestHandler<HandlerRequest, HandlerResponse> {

	private final PersonRepository repository = new PersonRepository();
	
	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {

		final String name = request.getPathParameters().get("name");
		final String starts = request.getQueryStringParameters().get("starts");
		final String ends = request.getQueryStringParameters().get("ends");

		context.getLogger().log("Searching for registered person for " + name + " topic between " + starts + " and " + ends);

		final List<Person> person = this.repository.findByPeriod(name, starts, ends);
		
		if(person == null || person.isEmpty()) {
			return HandlerResponse.builder().setStatusCode(404).build();
		}
		
		return HandlerResponse.builder().setStatusCode(200).setObjectBody(person).build();
	}
}