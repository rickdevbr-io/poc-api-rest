# Sobre :blue_book:
Esta API serve para demonstrar como construir um aplicativo no AWS Serverless Envinronment usando o AWS SAM, Amazon API Gateway, AWS Lambda e Amazon DynamoDB. Ele também usa a estrutura ORM do DynamoDBMapper para mapear itens de estudo em uma tabela do DynamoDB para uma API RESTful.

# Tech Stag :clipboard:
- AWS CLI already configured with at least PowerUser permission
- [Java SE Development Kit 8 installed](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Docker installed](https://www.docker.com/community-edition)
- [Maven](https://maven.apache.org/install.html)
- [SAM CLI](https://github.com/awslabs/aws-sam-cli)

# Executando o projeto :computer:
1. Inicie o DynamoDB Local em um contêiner do Docker (Comando Windows). `docker run -p 8000:8000 amazon/dynamodb-local`

2. Crie a tabela DynamoDB (Comando Windows). `aws dynamodb create-table --table-name person --attribute-definitions AttributeName=name,AttributeType=S AttributeName=dateTimeCreation,AttributeType=S AttributeName=cpf,AttributeType=S AttributeName=nickname,AttributeType=S AttributeName=bloodType,AttributeType=S AttributeName=gender,AttributeType=S --key-schema AttributeName=name,KeyType=HASH AttributeName=dateTimeCreation,KeyType=RANGE --local-secondary-indexes "IndexName=cpfIndex,KeySchema=[{AttributeName=name,KeyType=HASH},{AttributeName=cpf,KeyType=RANGE}],Projection={ProjectionType=ALL}" "IndexName=genderIndex,KeySchema=[{AttributeName=name,KeyType=HASH},{AttributeName=gender,KeyType=RANGE}],Projection={ProjectionType=ALL}" "IndexName=nicknameIndex,KeySchema=[{AttributeName=name,KeyType=HASH},{AttributeName=nickname,KeyType=RANGE}],Projection={ProjectionType=ALL}" "IndexName=bloodTypeIndex,KeySchema=[{AttributeName=name,KeyType=HASH},{AttributeName=bloodType,KeyType=RANGE}],Projection={ProjectionType=ALL}" --billing-mode PAY_PER_REQUEST --endpoint-url http://localhost:8000`

Se a tabela já existe, você pode excluir: `aws dynamodb delete-table --table-name person --endpoint-url http://localhost:8000`

3. Inicie a API local do SAM.
 - Mac: `sam local start-api --env-vars src/test/resources/test_environment_mac.json`
 - Windows: `sam local start-api --env-vars src/test/resources/test_environment_windows.json`
 - Linux: `sam local start-api --env-vars src/test/resources/test_environment_linux.json`
 
 OBS: Se você já tem o container localmente (no seu caso o java8), então você pode usar --skip-pull-image to remove the download

Se o comando anterior foi executado com sucesso, agora você deve ser capaz de atingir o seguinte endpoint local para
invocar as funções enraizadas em `http://localhost:3000/person/{name}?starts=2020-01-02&ends=2020-02-02`.
