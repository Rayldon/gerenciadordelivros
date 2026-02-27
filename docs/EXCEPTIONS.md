## Tratamento de erros

A API centraliza erros em `GlobalExceptionHandler` (`@RestControllerAdvice`).

## Tipos tratados
- `RegraNegocioException` -> HTTP 400
- `HttpMessageNotReadableException` (JSON invalido) -> HTTP 400
- `Exception` generica -> HTTP 500

## Formato de resposta de erro
`ErrorResponse`:
- `timestamp`
- `status`
- `error`
- `message`
- `path`

## Motivacao
- manter resposta padrao para o cliente
- separar regra de dominio de regra de transporte HTTP
- facilitar observabilidade e debug
