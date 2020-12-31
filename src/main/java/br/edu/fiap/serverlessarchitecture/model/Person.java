package br.edu.fiap.serverlessarchitecture.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "person")
public class Person {

	@DynamoDBHashKey(attributeName = "name")
	private String name;
	
	@DynamoDBRangeKey(attributeName = "dateTimeCreation")
	private String dateTimeCreation;

	@DynamoDBIndexRangeKey(attributeName = "cpf", localSecondaryIndexName = "cpfIndex")
	private String cpf;

	@DynamoDBAttribute(attributeName = "nickname")
	private String nickname;
	
	@DynamoDBAttribute(attributeName = "bloodType")
	private String bloodType;

	@DynamoDBIndexRangeKey(attributeName = "gener", localSecondaryIndexName = "generIndex")
	private String gener;

	public Person(String name, String dateTimeCreation, String cpf, String nickname, String bloodType, String gener) {
		super();
		this.name = name;
		this.dateTimeCreation = dateTimeCreation;
		this.cpf = cpf;
		this.nickname = nickname;
		this.bloodType = bloodType;
		this.gener = gener;
	}

	public Person() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateTimeCreation() {
		return dateTimeCreation;
	}

	public void setDateTimeCreation(String dateTimeCreation) {
		this.dateTimeCreation = dateTimeCreation;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getGener() {
		return gener;
	}

	public void setGener(String gener) {
		this.gener = gener;
	}
	
}
