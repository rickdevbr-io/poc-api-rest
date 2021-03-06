package br.edu.fiap.serverlessarchitecture.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.edu.fiap.serverlessarchitecture.controller.HandlerRequest;
import br.edu.fiap.serverlessarchitecture.controller.HandlerResponse;
import br.edu.fiap.serverlessarchitecture.model.Person;
import br.edu.fiap.serverlessarchitecture.model.PersonRepository;

public class GetPersonRecordsByNameCpf implements RequestHandler<HandlerRequest, HandlerResponse> {

	private final PersonRepository repository = new PersonRepository();

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {

		final String name = request.getPathParameters().get("name");
		final String cpf = request.getQueryStringParameters().get("cpf");

		context.getLogger().log("Searching for registered person for " + name + " and cpf equals " + cpf);

		final List<Person> people = this.repository.findByNameCpf(name, cpf);

		if (people == null || people.isEmpty()) {
			return HandlerResponse.builder().setStatusCode(404).build();
		}

		return HandlerResponse.builder().setStatusCode(200).setObjectBody(people).build();
	}
}