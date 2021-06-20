# Simple Microservice's architecture behind Spring Cloud Gateway

All services uses **Hashicorp Consul** as discovery service to help manage web traffic with dynamic load balancing.
To facilitate implementation when it is necessary **Spring Cloud OpenFeign** is called. 


## OAuth2.0 Resource Servers (in-memory)
- **basket-service** - This service is responsible for collecting and holding client basket. 
  Service use local Cache and **resilience4j** to proceed order with **order-service**
- **client-service** -  This service is responsible for delivering customers and their discounts
- **order-service** - This service is responsible for generating orders. It communicates with **client-service** 
  and **basket-service**
  
## Gateway
- **gateway** - Provides proxy to hide services

## Menu
- **menu-service** - Not secured endpoints to check and choose dishes from menu


## Run app

```
$ docker-compose up -d
```

## OIDC provider
- **Keycloak** - to properly use service you should be familiar with this tool, after starting the container, 
  a new realm and client must be created.    

