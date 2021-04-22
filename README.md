
# API Administración de Venta y productos
EL siguiente entregable contiene la api requerida segun el documento (https://github.com/JosephCastro/SegurosFalabellaTest).

## Elementos entregados
- Código Fuente
- Dockerfile para instalación
- Colección Postman

## Instalación la aplicación

### Tecnologías
- Spring Boot
- Base de datos H2

### Correr la aplicación
1. Descargar Todos los archivos del repositorio  
(https://github.com/renzopizarro/testFalabella).

2. Crear imagen docker
```
    docker build -t "spring-boot-docker" .
```
3. Correr imagen docker
```
   docker run --name spring-boot-docker -p 8080:8080
```
4. Testear API
```
   curl -v http://localhost:8080/api/sales  
```
## APIs Expuestas
|Api        | Ejemplo                   |   
|----------|----------------------------|
|Listar productos vendidos|`http://[LOCAL_URL]:8080/api/products`|
|Listar ventas realizadas|`http://[LOCAL_URL]:8080/api/sales`|
|Vender producto por nombre|`http://[LOCAL_URL]:8080/api/sale?productName=FULL COBERTURA`
|Evaluar productos disponibles|`http://[LOCAL_URL]:8080/api/evaluateProducts/5`
 
 ## Consideraciones en la entrega
1. Para realizar la actualización diaria de sellIn y precio de cada producto al final del dia, 
se construyó un Timer programado para ejecutarse todos los dias a las 11HH:59MM:59SS
2. La base de datos tiene cargado registros de los productos disponibles para vender. Se realizó la carga según el sgte archivo:
ProductsApi/src/main/resources/data.sql
3. Para vender un producto sólo se envia el nombre del producto ya que internamente se calcula el precio según el SellIn.




