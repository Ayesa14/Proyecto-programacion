# Proyecto programación

* Sistema para gestionar los pedidos de un restaurante
* Está desarrollado en Java 
* Para la parte gráfica he utilizado la biblioteca gráfica Swing
* El proyecto está estructurado en un patrón MVC
* La base de datos utilizada es Oracle Database 11g Express Edition

## Cómo empezar
1. Clona el proyecto
2. Necesitas descargar Oracle Database 11g Express Edition, puedes descargarlo [aquí](http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html)
3. Una vez descargado, crea una base de datos vacía
4. Crea tablas en la base de datos, tienes aquí el [script de creacion](https://github.com/Ayesa14/Proyecto-programacion?tab=readme-ov-file#script-creaci%C3%B3n-de-tabla)
5. Inserta datos con el [script de insercción de datos](https://github.com/Ayesa14/Proyecto-programacion?tab=readme-ov-file#script-insercci%C3%B3n-datos)
6. Abre el proyecto con tu IDE y asegúrate de que tienes la librería
7. Configura la conexión de la base de datos editando el archivo database_config.properties
8. La contraseña para el acceso a la administración es 1234
9. Finalmente, corre la main

## Información sobre la base de datos
### Modelo entidad relación

![Modelo entidad relación](./images/entidadRelacion.PNG)

### Modelo relacional de tablas

* CATEGORIA(***id***, nombre)
* PRODUCTO(***id***, nombre, precio, *id_categoria*)
* MESA(***id***, nombre)
* PEDIDO(***id***, precio, en_curso, fecha, *id_mesa*)
* COMANDA(***id***, *id_pedido*)
* COMANDA_PRODUCTO(***id_comanda***, ***id_producto***, cantidad)

### Script creación de tabla

```sql
drop table categoria cascade constraints;
create table categoria(
id number primary key,
nombre varchar2(255) not null unique
);

drop table producto cascade constraints;
create table producto(
id number primary key,
nombre varchar2(255) not null unique,
precio number not null,
id_categoria number,
foreign key(id_categoria) references categoria(id) on delete cascade
);

drop table mesa cascade constraints;
create table mesa(
id number primary key,
nombre varchar2(255) not null unique
);

drop table pedido cascade constraints;
create table pedido(
id number primary key,
precio number not null,
en_curso varchar2(5) not null,
fecha date not null,
id_mesa number,
check(en_curso in('true', 'false')),
foreign key(id_mesa) references mesa(id) on delete cascade
);

drop table comanda cascade constraints;
create table comanda(
id number primary key,
id_pedido number not null,
foreign key(id_pedido) references pedido(id) on delete cascade
);

drop table comanda_producto cascade constraints;
create table comanda_producto(
id_comanda number not null,
id_producto number not null,
cantidad number not null,
primary key(id_comanda, id_producto),
foreign key(id_comanda) references comanda(id) on delete cascade,
foreign key(id_producto) references producto(id) on delete cascade
);
```

### Script insercción datos
```sql

insert into categoria values (1, 'Refrescos');
insert into categoria values (2, 'Cervezas');
insert into categoria values (3, 'Batidos');
insert into categoria values (4, 'Bocatas');
insert into categoria values (5, 'Hamburguesas');


insert into producto values (1, 'CocaCola', 1.5, 1);
insert into producto values (2, 'CocaCola Zero', 1.5, 1);
insert into producto values (3, 'Fanta Naranja', 1.5, 1);
insert into producto values (4, 'Fanta Limon', 1.5, 1);
insert into producto values (5, 'Red Bull', 2.5, 1);
insert into producto values (6, 'Mahou Clásica', 1.25, 2);
insert into producto values (7, 'Mahou Cinco Estrellas', 1.25, 2);
insert into producto values (8, 'Mahou Sin', 1.75, 2);
insert into producto values (9, 'Mahou Negra', 2, 2);
insert into producto values (10, 'Batido Fresa', 2, 3);
insert into producto values (11, 'Batido Chocolate', 2, 3);
insert into producto values (12, 'Batido Vainilla', 2, 3);
insert into producto values (13, 'Bocata Jamon Serrano', 5, 4);
insert into producto values (14, 'Bocata Lomo', 3.5, 4);
insert into producto values (15, 'Bocata Panceta', 3.5, 4);
insert into producto values (16, 'Bocata Tortilla', 4, 4);
insert into producto values (17, 'Hamburguesa de la Casa', 5.5, 5);
insert into producto values (18, 'Hamburguesa Especial', 7, 5);


insert into mesa values (1, 'Mesa 1');
insert into mesa values (2, 'Mesa 2');
insert into mesa values (3, 'Mesa 3');
insert into mesa values (4, 'Mesa 4');
insert into mesa values (5, 'Mesa 5');
insert into mesa values (6, 'Mesa 6');
insert into mesa values (7, 'Mesa 7');
insert into mesa values (8, 'Mesa 8');


insert into pedido values (1, 20, 'false', '03-30-2018', 1);
insert into pedido values (2, 6.5, 'false', '03-31-2018', 2);
insert into pedido values (3, 9.5, 'true', '04-02-2018', 5);


insert into comanda values (1, 1);
insert into comanda values (2, 1);
insert into comanda values (3, 2);
insert into comanda values (4, 3);
insert into comanda values (5, 3);


insert into comanda_producto values (1, 11, 5);
insert into comanda_producto values (2, 10, 2);
insert into comanda_producto values (2, 12, 2);
insert into comanda_producto values (2, 11, 1);
insert into comanda_producto values (3, 1, 1);
insert into comanda_producto values (3, 13, 1);
insert into comanda_producto values (4, 18, 1);
insert into comanda_producto values (5, 5, 1);
```

## Capturas
![add_productos](./images/add_productos.PNG)
![finalizar](./images/finalizar.PNG)
![gestion](./images/gestion.PNG)
![historial](./images/historia.PNG)
![mesas](./images/mesas.PNG)


+---------------+
|  Model       |
+---------------+
| - datos: ArrayList |
+---------------+
| + getData(): ArrayList |
| + setData(datos: ArrayList) |
+---------------+

+---------------+
|  View        |
+---------------+
| - texto: String |
+---------------+
| + display(texto: String) |
| + getText(): String |
+---------------+

+---------------+
|  Controller  |
+---------------+
| - model: Model |
| - view: View |
+---------------+
| + init() |
| + handleRequest(request: String) |
| + updateView() |
+---------------+
Sequence Diagram:

```mermaid
sequenceDiagram
    Controller->>Model: setData(datos: ArrayList)
    Model-->>View: display(texto: String)
    User->>View: getText(): String
    View-->>Controller: handleRequest(request: String)
    Controller->>Model: getData(): ArrayList
    Controller-->>Controller: updateView()
    Controller-->>View: display(texto: String)
```

