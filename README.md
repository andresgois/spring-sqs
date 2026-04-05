## Configuração AWS

### 🧰 1. Instalar o AWS CLI (oficial)
✔️ Baixar e instalar
```bash
sudo apt update
sudo apt install unzip curl -y

curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
✔️ Verificar instalação
aws --version
```

Saída esperada:
```bash
aws-cli/2.x.x Python/3.x ...
```

✔️ Configurar (fake credentials para LocalStack)
```bash
aws configure
```

Preencha assim:
```bash
AWS Access Key ID: test
AWS Secret Access Key: test
Default region name: us-east-1
Default output format: json
```

🐳 2. Instalar Docker (se ainda não tiver)
```bash
sudo apt install docker.io -y
sudo systemctl enable docker
sudo systemctl start docker
```

Opcional (rodar sem sudo):
```bash
sudo usermod -aG docker $USER
```

👉 Depois faça logout/login

📦 3. Subir o LocalStack com Docker
✔️ Criar docker-compose.yml
```bash
version: '3.8'

services:
  localstack:
    image: localstack/localstack:latest
    container_name: localstack
    ports:
      - "4566:4566"
    environment:
      - SERVICES=s3,sqs
      - DEBUG=1
      - AWS_DEFAULT_REGION=us-east-1
    volumes:
      - "/var/run/docker.sock:/var/run/docker.sock"
```

✔️ Subir o container
```bash
docker-compose up -d
```
✔️ Verificar
```bash
docker ps
```

Deve aparecer:
```bash
localstack
```

🔗 4. Testar conexão com LocalStack

⚠️ Sempre use o endpoint:

```bash
--endpoint-url=http://localhost:4566
```

✔️ Criar bucket S3
```bash
aws --endpoint-url=http://localhost:4566 s3 mb s3://meu-bucket
```

✔️ Listar buckets
```bash
aws --endpoint-url=http://localhost:4566 s3 ls
```

✔️ Criar fila SQS
```bash
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name minha-fila
```

lista filas sqs
awslocal sqs list-queues

detalhes da fila
awslocal sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/minha-fila --attribute-names All

deletar fila
awslocal sqs delete-queue --queue-url http://localhost:4566/000000000000/minha-fila

📬 Ver a mensagem (receber)
awslocal sqs receive-message \
  --queue-url http://localhost:4566/000000000000/minha-fila
  
envio de mensagem pra fila
awslocal sqs send-message \
  --queue-url http://localhost:4566/000000000000/minha-fila \
  --message-body '{"nome":"Ana","email":"ana@email.com"}'
 

⚡ 5. (Opcional, mas MUITO útil) instalar awslocal

Isso evita ficar digitando --endpoint-url toda hora.
```bash
pip install awscli-local
```

✔️ Usar assim:
```bash
awslocal s3 mb s3://meu-bucket
awslocal s3 ls
```

🧠 Como funciona
AWS CLI → cliente
LocalStack → simula AWS localmente
Docker → roda tudo isolado
🎯 Dica importante pra Spring Boot

Se você for integrar com Spring:

- aws.endpoint=http://localhost:4566
- aws.region=us-east-1
- aws.accessKey=test
- aws.secretKey=test

🚀 Resumo

Você agora tem:

- ✅ AWS CLI instalado
- ✅ Docker rodando
- ✅ LocalStack funcionando
- ✅ S3 e SQS simulados localmente