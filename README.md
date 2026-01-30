# Agencia de Turismo

- Se puede probar en: https://agenciaturismo.onrender.com
- Por ej: https://agenciaturismo.onrender.com/empleados/1
- La bd está hosteada en railway

## Contexto
- Es un desafío de programación, la consigna está en el repositorio por si se quiere leer.

## Consideraciones

- Por simplicidad no se usan clases para cada Servicio Turistico, podría implementarse mas adelante pero
  habría que asumir muchas cosas

- Se usa la estrategia de herencia JOINED para productos, servicios y paquetes, es decir que las tablas de servicios 
  y de paquetes, tienen como PK a codigo_producto, y esta a su vez, es una FK que referencia un producto de la tabla productos

- Se hace soft delete de todo, excepto de ventas, que se eliminan por completo. El resto queda con una 
  columna ESTADO con el valor ELIMINADO (esto es porque por ejemplo, si se quiere eliminar un cliente asociado
  a una venta, no podria eliminarse por estar asociado a la venta).

- El costo de un paquete se debe pasar al crearlo o editarlo en el body, y debe ser el correcto, si no, da error
  (es decir la suma de los costos de los servicios incluidos menos el 10%).

- Las fechas de una venta o un servicio no pueden ser anteriores a la fecha actual.

- Se puede editar un servicio y poner una fecha anterior (esto lo asumí pero no tiene mucho sentido).
