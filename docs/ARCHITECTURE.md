## Visão Arquitetural
A aplicação segue o padrão **Arquitetura Hexagonal (Ports and Adapters)**.

### Princípios
- O **Domínio** não depende de frameworks
- Casos de uso orquestram regras de negócio
- Adapters conectam o mundo externo (REST, banco, relatórios)
- Dependências sempre apontam para o centro (domínio)

### Camadas

```
Domain  <- Application <- Adapters
```

### Estrutura de Pacotes
- `domain`
    - entidades
    - exceções de negócio
    - portas (interfaces)
- `application`
    - casos de uso
- `adapter.in`
    - controllers REST
- `adapter.out`
    - persistência JPA
    - relatórios

---