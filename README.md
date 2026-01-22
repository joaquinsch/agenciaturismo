<h1>Agencia de Turismo</h1>

<h3>Por simplicidad no se usan clases para cada Servicio Turistico, podría implementarse mas adelante pero
habría que asumir muchas cosas</h3>
<h3>Se usa la estrategia de herencia JOINED para productos, servicios y paquetes, es decir que las tablas de servicios 
y de paquetes, tienen como PK a codigo_producto, y esta a su vez, es una FK que referencia un producto de la tabla productos</h3>
<h3>Se hace soft delete de todo, excepto de ventas, que se eliminan por completo. El resto queda con una 
columna ESTADO con el valor ELIMINADO (esto es porque por ejemplo, si se quiere eliminar un cliente asociado
a una venta, no podria eliminarse por estar asociado a la venta).</h3>
<h3>El costo de un paquete se debe pasar al crearlo o editarlo en el body, y debe ser el correcto, si no, da error
(es decir la suma de los costos de los servicios incluidos menos el 10%).</h3>
<h3>Las fechas de una venta o un servicio no pueden ser anteriores a la fecha actual.</h3>
<h3>Se puede editar un servicio y poner una fecha anterior (esto lo asumí pero no tiene mucho sentido).</h3>
