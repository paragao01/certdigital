# Etapa 1: Build do projeto com Ant
FROM openjdk:8-jdk AS builder

WORKDIR /app
COPY fontes/ /app/fontes/
WORKDIR /app/fontes/ant
RUN apt-get update && apt-get install -y ant && \
    ant -buildfile build.xml

# Etapa 2: Deploy no Tomcat
FROM tomcat:9-jdk8

# Copia o WAR gerado para o Tomcat
COPY --from=builder /app/fontes/war/certdigital.war /usr/local/tomcat/webapps/certdigital.war

# Copia os JARs extras (se necessário)
COPY fontes/lib/*.jar /usr/local/tomcat/lib/
COPY fontes/configuracoes/drivers/lib/*.jar /usr/local/tomcat/lib/

# Configuração do timezone e variáveis de ambiente
ENV TZ=America/Sao_Paulo 