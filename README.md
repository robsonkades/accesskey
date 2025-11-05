# 📌 Access Key – Geração e Validação de Chave de Acesso de Documentos Fiscais

Biblioteca Java de alto desempenho para **geração, formatação e validação da Chave de Acesso** de documentos fiscais eletrônicos (**NFe, NFCe, CTe e MDFe**), conforme a **Nota Técnica 2025.001**.

---

## 🚀 Recursos

✅ Geração completa da Chave de Acesso (44 dígitos)  
✅ Validação do dígito verificador (DV)  
✅ Suporte a CNPJ numérico e alfanumérico (quando aplicável)  
✅ Componentes imutáveis e thread-safe  
✅ Leve, sem dependências externas complexas  
✅ Compatível com Java 8+  
✅ Licença **LGPL v3** (uso comercial permitido)

---

## 📦 Instalação

Em breve disponível no **Maven Central**.  
Por enquanto, você pode instalar localmente:

```bash
<dependency>
    <groupId>io.github.robsonkades</groupId>
    <artifactId>accesskey</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 🛠️ Exemplo de Uso

```java
import io.github.robsonkades.accesskey.AccessKey;

AccessKeyBuilder builder = new AccessKeyBuilder()
        .state(State.SP)
        .yearMonth(YearMonth.of(2024, 10))
        .cnpj("KSP416L8000109")
        .model(Model.NFE)
        .series(1)
        .number(123)
        .issueMode(IssueMode.NORMAL)
        .code(99999999);

AccessKey accessKey = builder.build();

System.out.println(accessKey.generate());   // 352410KSP416L8000109550010000001231999999993

AccessKey accessKey = AccessKey.from("352410KSP416L8000109550010000001231999999993");

```
## ✅ Testes

```bash
mvn test
```

## 📄 Licença
Este projeto está licenciado sob a LGPL v3 (GNU Lesser General Public License).

Isso significa que você pode usar em sistemas comerciais e proprietários, desde que modificações na biblioteca sejam disponibilizadas sob a mesma licença.

## 🤝 Contribuindo

Contribuições são bem-vindas! Para colaborar:
1. Faça um fork do projeto
2. Crie uma branch: `git checkout -b minha-feature`
3. Faça o commit: `git commit -m 'Minha nova feature'`
4. Envie o PR: `git push origin minha-feature`

## 📬 Contato

Se quiser tirar dúvidas, sugerir melhorias ou reportar bugs, abra uma issue no repositório.

Feito com ❤️ e café ☕