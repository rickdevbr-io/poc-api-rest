package br.edu.fiap.serverlessarchitecture.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.edu.fiap.serverlessarchitecture.controller.HandlerRequest;
import br.edu.fiap.serverlessarchitecture.controller.HandlerResponse;
import br.edu.fiap.serverlessarchitecture.model.Person;
import br.edu.fiap.serverlessarchitecture.model.PersonRepository;

public class GetPersonRecordsByNameGender implements RequestHandler<HandlerRequest, HandlerResponse> {

	private final PersonRepository repository = new PersonRepository();

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {

		final String name = request.getPathParameters().get("name");
		final String gender = request.getQueryStringParameters().get("gender");

		context.getLogger()
				.log("Searching for registered person for " + name + " that gender equals " + gender);
		final List<Person> people = this.repository.findByNameGender(name, gender);

		if (people == null || people.isEmpty()) {
			return HandlerResponse.builder().setStatusCode(404).build();
		}

		return HandlerResponse.builder().setStatusCode(200).setObjectBody(people).build();
	}
}