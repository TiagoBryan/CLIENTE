# ShopMS - Customer Service

Serviço de gerenciamento de clientes.

## Tecnologias
- Java 17
- Spring Boot 3.3.0
- MongoDB

## Como executar
```bash
mvn spring-boot:run
```

## Configuração
- Porta: 8085
- MongoDB: localhost:27017/customerdb

## Endpoints principais
- `GET /api/customers` - Listar clientes (público)
- `GET /api/customers/{id}` - Buscar cliente (público)
- `POST /api/customers` - Criar cliente (público)
- `PUT /api/customers/{id}` - Atualizar cliente (autenticado)
- `DELETE /api/customers/{id}` - Deletar cliente (autenticado)

## Modelo de dados
```json
{
  "name": "João Silva",
  "birthDate": "1990-05-20",
  "address": {
    "street": "Rua A",
    "number": "123",
    "city": "São Paulo",
    "state": "SP"
  }
}
```