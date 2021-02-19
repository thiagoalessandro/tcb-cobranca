# TCB COBRANCA 

Aplicação API REST responsável por fornecer serviços de integração ao TCB Cobrança.

## Gerando chaves PGP

```shell script
$ gpg --batch --gen-key <<EOF
Key-Type: RSA
Key-Length: 2048
Name-Real: contato.tcb@tfs.totvs.com
Name-comment: TCB-UAT
Name-Email: contato.tcb@tfs.totvs.com
Expire-Date: 2y
%commit
EOF
```

Nomenclatura de chave: 

```
uat-privatekey.asc
uat-publickey.asc
```

**Observação**: As chaves geradas devem ser atualizadas no Google Drive do projeto (TCB COBRANCA) obedecendo a nomeclatura existente. 

## Documentação API (Swagger)

Documentação da API é disponibilizada em http://{hostname}:8084/tcbcobranca/swagger-ui.html

**Importante!** A documentação só ficará disponível quando a aplicação for inicializada com profile **dev** ou **homol**

## Referências

[Standard Payments - Google] (https://github.com/google/standard-payments)

