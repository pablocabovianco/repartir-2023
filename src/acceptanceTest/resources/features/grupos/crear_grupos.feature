# language: es

Característica: Crear Grupo para repartir gastos

  Regla: Los grupos tienen un nombre que los identifica

    Escenario: Crea un grupo con nombre
      Dado que el usuario inició Repartir
      Cuando el usuario crea un grupo indicando el nombre 'Regalo de navidad'
      Entonces debería visualizar dentro del listado el grupo con el nombre indicado

    @pendiente
    Escenario: No puede crear un grupo sin nombre
      Dado que el usuario inició Repartir
      Cuando el usuario intenta crear un grupo sin indicar su nombre
      Entonces no debería crear el grupo sin nombre
      Y debería ser informado que no puede crear un grupo sin nombre

  Regla: Los grupos están compuestos por al menos dos miembros distintos

    Escenario: Crea un grupo con dos miembros distintos
      Dado que el usuario inició Repartir
      Cuando el usuario crea un grupo indicando que sus miembros son 'mariano' y 'juancruz'
      Entonces visualiza dentro del listado el grupo con los miembros indicados

    Escenario: No puedo crear un grupo con un único miembro
      Dado que el usuario inició Repartir
      Cuando el usuario intenta crear un grupo indicando un único miembro
      Entonces no debería crear el grupo con un único miembro
      Y debería ser informado que no se pudo guardar el grupo

    Escenario: No puedo crear un grupo con dos miembros iguales
      Dado que el usuario inició Repartir
      Cuando el usuario crea un grupo indicando que sus miembros son 'pedro' y 'pedro'
      Entonces no debería crear el grupo con dos miembros iguales
      Y debería ser informado que no se pudo guardar el grupo

  Regla: Los grupos tienen un estado inicial

    Escenario: El total inicial del grupo es $ 0
      Dado que el usuario inició Repartir
      Cuando el usuario crea un grupo
      Entonces debería visualiza dentro del listado el grupo creado con total '$  0,00'

