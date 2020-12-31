package br.edu.fiap.serverlessarchitecture.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.edu.fiap.serverlessarchitecture.controller.DynamoDBManager;

public class PersonRepository {

	private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

	public Person save(final Person person) {
		mapper.save(person);
		return person;
	}

	public List<Person> findByPeriod(final String name, final String starts, final String ends) {

		final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(name));
		eav.put(":val2", new AttributeValue().withS(starts));
		eav.put(":val3", new AttributeValue().withS(ends));

		final DynamoDBQueryExpression<Person> queryExpression = new DynamoDBQueryExpression<Person>()
				.withKeyConditionExpression("name = :val1 and dateTimeCreation between :val2 and :val3")
				.withExpressionAttributeValues(eav);

		final List<Person> people = mapper.query(Person.class, queryExpression);

		return people;
	}

	public List<Person> findByNameCpf(final String name, final String cpf) {

		final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(name));
		eav.put(":val2", new AttributeValue().withS(cpf));

		final DynamoDBQueryExpression<Person> queryExpression = new DynamoDBQueryExpression<Person>()
				.withIndexName("cpfIndex").withConsistentRead(false)
				.withKeyConditionExpression("name = :val1 and cpf=:val2").withExpressionAttributeValues(eav);

		final List<Person> studies = mapper.query(Person.class, queryExpression);

		return studies;
	}

	public List<Person> findByNameGener(final String name, final String gener) {

		final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(name));
		eav.put(":val2", new AttributeValue().withS(gener));

		final Map<String, String> expression = new HashMap<>();

		expression.put("#gener", "gener");

		final DynamoDBQueryExpression<Person> queryExpression = new DynamoDBQueryExpression<Person>()
				.withIndexName("generIndex").withConsistentRead(false)
				.withKeyConditionExpression("name = :val1 and #gener=:val2").withExpressionAttributeValues(eav)
				.withExpressionAttributeNames(expression);

		final List<Person> people = mapper.query(Person.class, queryExpression);

		return people;
	}
}