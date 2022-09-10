<!--![Notimation](https://vigil.com.ar/share/noti_logo.png "Notimation")-->

<p align="center">
  <img src="https://vigil.com.ar/share/noti_logo.png" alt="Notimation image"/>
</p>


# API documentation

Status: DRAFT

Version: 1.2


# Introducción

 \
La arquitectura de nuestra API es REST.

Todos los objetos están protegidos por una llave privada, que será provista en el momento de darse de alta, y podrá regenerarse desde el [panel de control](https://panel.notimation.com).

La llave podrá ser pasada a los métodos como uno de los parámetros GET/POST de la solicitud (según corresponda) con el nombre "api_key", o **preferiblemente como Bearer Token dentro del header**.

Todas las respuestas vienen en formato JSON.

El horario de uso de la API es de  9 a 21 horas.


