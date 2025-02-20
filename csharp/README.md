# NLW Connect - Trilha C#

> Evento da RocketSeat de 17 a 20 de fevereiro de 2025

## Tecnologias

- C#
- .Net
- SQLite
- Docker
- Autenticação e segurança com JWT

## Guia de comandos

- dotnet new sln -n TechLibrary
- dotnet new webapi -n TechLibrary.Api
- dotnet sln add TechLibrary.Api/TechLibrary.Api.csproj
- TechLibrary.Api: dotnet add package Swashbuckle.AspNetCore
- dotnet new classlib -n TechLibrary.Communication
- dotnet sln add TechLibrary.Communication/TechLibrary.Communication.csproj
- dotnet add TechLibrary.Api/TechLibrary.Api.csproj reference TechLibrary.Communication/TechLibrary.Communication.csproj
- TechLibrary.Api: dotnet add package FluentValidation
- dotnet new classlib -n TechLibrary.Exception
- dotnet sln add TechLibrary.Exception/TechLibrary.Exception.csproj
- dotnet add TechLibrary.Api/TechLibrary.Api.csproj reference TechLibrary.Exception/TechLibrary.Exception.csproj
- TechLibrary.Api: dotnet add package Microsoft.EntityFrameworkCore
- TechLibrary.Api: dotnet add package Microsoft.EntityFrameworkCore.Sqlite
- TechLibrary.Api: dotnet add package BCrypt.Net-Next
- TechLibrary.Api: dotnet add package Microsoft.IdentityModel.Tokens
- TechLibrary.Api: dotnet add package System.IdentityModel.Tokens.Jwt

## Links úteis:

- [Notion](https://efficient-sloth-d85.notion.site/NLW-Connect-337b47bcef1640fc9a536f66dd45d8f1)

- [Api Swagger UI](http://localhost:5253/swagger/index.html)
