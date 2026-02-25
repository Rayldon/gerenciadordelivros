## Estratégia de Tratamento de Erros

### Tipos de Exceção
- Exceções de domínio (regras de negócio)
- Exceções de infraestrutura (banco de dados)

### Centralização
O tratamento de erros é centralizado utilizando `@ControllerAdvice`, garantindo:
- respostas padronizadas
- códigos HTTP adequados
- mensagens claras

---