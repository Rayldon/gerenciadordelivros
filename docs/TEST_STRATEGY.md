## Estrategia de testes
O projeto aplica uma piramide de testes.

## 1) Testes unitarios
Cobrem dominio, use cases e mapeamentos com foco em regra de negocio.

Exemplos:
- `LivroTest`, `AutorTest`, `AssuntoTest`
- `CriarLivroUseCaseTest` e demais use cases

## 2) Testes de integracao de persistencia
- `@DataJpaTest` para validar JPA, mapeamento e adapters
- uso de scripts SQL de teste quando necessario (`test-data.sql`)

## 3) Testes web (slice)
- `MockMvc` com `@WebMvcTest`
- validam contrato HTTP dos controllers
- cobrem cenarios de sucesso e erro (400 para regras e JSON invalido)

## 4) Teste e2e
- `LivroE2ETest` com `@SpringBootTest` + `@AutoConfigureMockMvc`
- profile `e2e` com banco H2 isolado por contexto
- limpeza com `clean.sql` antes de cada metodo

## Comando
```powershell
.\mvnw test
```
