# Proyecto de Microservicios con Spring Boot

Este proyecto implementa un sistema de microservicios utilizando tecnologías como Docker, Docker Compose, Kafka, Spring Cloud Gateway, Keycloak, Open API, Grafana y JavaMailSender para construir una arquitectura robusta y escalable. A continuación se detalla la estructura y los servicios incluidos.

---

## Tabla de Contenidos

- [Arquitectura](#arquitectura)
- [Servicios](#servicios)
    - [Product Service](#product-service)
    - [Order Service](#order-service)
    - [Inventory Service](#inventory-service)
    - [API Gateway](#api-gateway)
    - [Notification Service](#notification-service)
- [Implementación de Gateway API](#implementación-de-gateway-api)
- [Seguridad de Microservicios](#seguridad-de-microservicios)
- [Documentación de la API REST](#documentación-de-la-api-rest)
- [Tolerancia a Fallos](#tolerancia-a-fallos)
- [Arquitectura de Eventos con Kafka](#arquitectura-de-eventos-con-kafka)
- [Observabilidad con Grafana Stack](#observabilidad-con-grafana-stack)
- [Envío de Correos con JavaMailSender](#envío-de-correos-con-javamailsender)

---

## Arquitectura

### Arquitectura de Alto Nivel
Se proporciona una descripción de la arquitectura de microservicios, incluyendo los componentes y cómo interactúan entre sí.

### Arquitectura Lógica
La arquitectura lógica describe los detalles técnicos de cada servicio y sus relaciones.

---

## Servicios

### Product Service
Implementación del servicio de productos, que gestiona la información sobre los productos en el sistema.

### Order Service
Implementación del servicio de órdenes, que se encarga de procesar y manejar las órdenes de compra.

### Inventory Service
Implementación del servicio de inventario, responsable de la gestión del inventario de productos.

### API Gateway
El API Gateway usa Spring Cloud Gateway para enrutar y balancear las solicitudes entrantes hacia los diferentes microservicios. Además, emplea Resilience4j para implementar tolerancia a fallos y rutas configuradas para la disponibilidad y resiliencia del sistema.

### Notification Service
El servicio de notificaciones (`Notification Service`) consume mensajes de los tópicos de Kafka y utiliza JavaMailSender para enviar correos electrónicos automáticos como notificaciones del sistema, proporcionando una capa de comunicación efectiva para los eventos críticos.

---

## Implementación de Gateway API

Se utiliza Spring Cloud Gateway para la gestión de API en los microservicios, facilitando la comunicación entre ellos y permitiendo un acceso centralizado.

---

## Seguridad de Microservicios

Los microservicios se aseguran usando Keycloak y el protocolo OAuth2 para manejar la autenticación y autorización, protegiendo el acceso a los recursos del sistema.

---

## Documentación de la API REST

La API REST se documenta usando Open API, facilitando el acceso a la documentación para los desarrolladores y clientes.

### Agregación y Refactorización de la Documentación
Se realiza una agregación de la documentación de los microservicios en un solo lugar y se refactoriza el código para mantener un código limpio y manejable.

---

## Tolerancia a Fallos

Implementación de un Circuit Breaker para mejorar la resiliencia de la arquitectura ante fallos, garantizando la continuidad del servicio en caso de interrupciones.

---

## Arquitectura de Eventos con Kafka

Implementación de una arquitectura orientada a eventos usando Kafka para manejar la comunicación asíncrona entre microservicios, facilitando la escalabilidad y la consistencia eventual de los datos.

---

## Observabilidad con Grafana Stack

Implementación de la observabilidad utilizando Grafana y otras herramientas del Grafana Stack para monitoreo, análisis en tiempo real y rastreo de eventos en los microservicios.

---

## Envío de Correos con JavaMailSender

Se utiliza JavaMailSender para enviar correos electrónicos desde el sistema, facilitando notificaciones automáticas desde los microservicios.


