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


# Objetos disponibles

Los objetos están disponibles en la siguiente ruta:


```
https://api.notimation.com/api/VERSION/OBJETO
```


Versión actual: `1`


<table>
  <tr>
   <td colspan="2" ><strong>Objeto</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
  </tr>
  <tr>
   <td colspan="2" ><code>

<a href="#heading=h.e1eol2etsjf9">sms</a></code>
   </td>
   <td>Listar, ver y enviar SMS
   </td>
  </tr>
  <tr>
   <td colspan="2" ><code>


<a href="#heading=h.ubhsywfcnhua">phone_number</a></code>
   </td>
   <td>Ver información sobre números telefónicos
   </td>
  </tr>
</table>



# Formato de respuestas

Todas las respuestas vienen en formato JSON, y siguen las mejores prácticas para APIs REST:


<table>
  <tr>
   <td><strong>Variable</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
  </tr>
  <tr>
   <td><code>status</code>
   </td>
   <td>“<code>success</code>” o “<code>error</code>” según corresponda.
<p>
<strong>Esta variable está siempre presente en las respuestas.</strong>
   </td>
  </tr>
  <tr>
   <td><code>data</code>
   </td>
   <td>Información devuelta por el método consultado.
<p>
<strong>Esta variable está siempre presente en las respuestas exitosas.</strong>
   </td>
  </tr>
  <tr>
   <td><code>message</code>
   </td>
   <td>Mensaje descriptivo del error.
<p>
<strong>Esta variable está siempre presente en las respuestas erróneas.</strong>
   </td>
  </tr>
</table>



## Ejemplo de respuesta exitosa


```
{
  status: "success",
  data: {
    id: 1020304050,
    status: "queued",
  },
}
```



## Ejemplo de respuesta errónea


```
{
  status: "error",
  message: "Número de teléfono inválido"
}
```



# Objeto sms


## Resumen de métodos disponibles


<table>
  <tr>
   <td><strong>Método</strong>
   </td>
   <td><strong>Ruta</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
  </tr>
  <tr>
   <td><code>LIST</code>
   </td>
   <td><code>GET /api/v1/sms</code>
   </td>
   <td>Lista los SMS enviados y recibidos en la cuenta
   </td>
  </tr>
  <tr>
   <td><code>CREATE</code>
   </td>
   <td><code>POST /api/v1/sms</code>
   </td>
   <td>Crea [y envía] un SMS
   </td>
  </tr>
  <tr>
   <td><code>SHOW</code>
   </td>
   <td><code>GET /api/v1/sms/ID</code>
   </td>
   <td>Muestra el detalle del mensaje
   </td>
  </tr>
</table>



## Crear y enviar un mensaje (CREATE)

Descripción: Crea y envía un mensaje SMS.


```
POST https://api.notimation.com/api/v1/sms
```



### Parámetros


<table>
  <tr>
   <td><strong>Parámetro</strong>
   </td>
   <td><strong>Obligatorio</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
   <td><strong>Ejemplo</strong>
   </td>
  </tr>
  <tr>
   <td><code>recipient</code>
   </td>
   <td>si
   </td>
   <td>Número de teléfono del destinatario.
   </td>
   <td>1155554444
   </td>
  </tr>
  <tr>
   <td><code>message</code>
   </td>
   <td>si
   </td>
   <td>Contenido del mensaje
   </td>
   <td>Hello, World!
   </td>
  </tr>
</table>



### Respuesta

El payload presente en “data” con información del mensaje creado:


<table>
  <tr>
   <td><strong>Variable</strong>
   </td>
   <td><strong>Tipo</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
  </tr>
  <tr>
   <td><code>sms_id</code>
   </td>
   <td><code>integer</code>
   </td>
   <td>Número interno del mensaje
   </td>
  </tr>
  <tr>
   <td><code>sms_status</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Alguno de los siguientes estados internos: 
<p>
“pending” → Mensaje recién ingresado en la plataforma
<p>
“cancelled” → Mensaje cancelado internamente antes de llegar a la cola de envíos
<p>
“queued” → Mensaje esperando en cola de envíos
<p>
“dispatched” → Mensaje despachado a la Telco
<p>
“sent” → Mensaje enviado
<p>
“error” → Mensaje con error (no pudo ser enviado)
<p>
“received” → Único status para mensajes entrantes
   </td>
  </tr>
</table>



### Ejemplo


#### Solicitud usando cURL


```
curl \
  -H 'Accept:application/json' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer BEARER_TOKEN' \
  -d '{"recipient":1155554444,"message":"Hello!"}' \
  -X POST \
  'https://api.notimation.com/api/v1/sms'
```



#### Respuesta formateada


```
{
  status: "success",
  data: [
    {
      sms_id: 15548745265,
      sms_status: "queued"
    }
  ]
}
```



## Lista de mensajes (LIST)

Descripción: Lista los mensajes SMS enviados y recibidos.


```
GET https://api.notimation.com/api/v1/sms
```



### Parámetros


<table>
  <tr>
   <td><strong>Parámetro</strong>
   </td>
   <td><strong>Obligatorio</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
   <td><strong>Ejemplo</strong>
   </td>
  </tr>
  <tr>
   <td><code>direction</code>
   </td>
   <td>no
   </td>
   <td>Filtra mensajes según dirección mensaje. 
<p>
Opciones: "outbound" para mensajes enviados desde la plataforma, o "inbound" para los recibidos.
   </td>
   <td>"outbound" para buscar los mensajes enviados.
   </td>
  </tr>
  <tr>
   <td><code>status</code>
   </td>
   <td>no
   </td>
   <td>Filtra mensajes según el estado. Los distintos estados filtrables son:
<p>
"pending": mensajes ingresados en la plataforma no procesados.
<p>
"cancelled": mensajes cancelados por la plataforma (ejemplo: el número es número inválido)
<p>
"queued": el mensaje está en cola esperando a ser despachado.
<p>
"dispatched": el mensaje ya fue despachado, pero todavía no se sabe el resultado.
<p>
"sent": el mensaje fue enviado con éxito.
<p>
"error": el mensaje no pudo ser enviado por un desperfecto que surgió durante el envío.
<p>
"received": el mensaje fue recibido por el destinatario (no es 100% fiable).
   </td>
   <td>"error", para listar los mensajes entrantes que no pudieron enviarse.
   </td>
  </tr>
  <tr>
   <td><code>take</code>
   </td>
   <td>no
   </td>
   <td>Cantidad de registros que buscará en la base de datos, <em>por defecto 50</em>.
   </td>
   <td>"100", para buscar 100 registros.
   </td>
  </tr>
  <tr>
   <td><code>skip</code>
   </td>
   <td>no
   </td>
   <td>Cantidad de registros que va a saltear antes de buscar los registros de la consulta, por defecto 0 (busca el último primero). 
   </td>
   <td>"50" para empezar a mostrar desde el 51 hasta el 100 (segunda página de resultados).
   </td>
  </tr>
</table>



### Respuesta

El payload presente en “data” es un array de mensajes a listar, cada uno con esta información:


<table>
  <tr>
   <td><strong>Variable</strong>
   </td>
   <td><strong>Tipo</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
  </tr>
  <tr>
   <td><code>id</code>
   </td>
   <td><code>integer</code>
   </td>
   <td>Número interno del mensaje
   </td>
  </tr>
  <tr>
   <td><code>phone_number</code>
   </td>
   <td><code>

<a href="#heading=h.ubhsywfcnhua">phone_number object</a></code>
   </td>
   <td>Información completa del número telefónico, en formato del objeto phone_number (

<a href="#heading=h.ubhsywfcnhua">ver documentación</a>)
   </td>
  </tr>
  <tr>
   <td><code>direction</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Dirección del mensaje:
<p>
“outbound” → SMS saliente
<p>
“inbound” → SMS entrante
   </td>
  </tr>
  <tr>
   <td><code>content</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Contenido del mensaje
   </td>
  </tr>
  <tr>
   <td><code>status</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Alguno de los siguientes estados internos: 
<p>
“pending” → Mensaje recién ingresado en la plataforma
<p>
“cancelled” → Mensaje cancelado internamente antes de llegar a la cola de envíos
<p>
“queued” → Mensaje esperando en cola de envíos
<p>
“dispatched” → Mensaje despachado a la Telco
<p>
“sent” → Mensaje enviado
<p>
“error” → Mensaje con error (no pudo ser enviado)
<p>
“received” → Único status para mensajes entrantes
   </td>
  </tr>
  <tr>
   <td><code>status_message</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Comentario adicional explicativo para algunos status
   </td>
  </tr>
  <tr>
   <td><code>created_at</code>
   </td>
   <td><code>date</code>
   </td>
   <td>Fecha de creación interna
   </td>
  </tr>
  <tr>
   <td><code>programmed_at</code>
   </td>
   <td><code>date</code>
   </td>
   <td>Fecha en que el envío fue programado
   </td>
  </tr>
  <tr>
   <td><code>sent_at</code>
   </td>
   <td><code>date</code>
   </td>
   <td>Fecha en que se realizó efectivamente el envío
   </td>
  </tr>
</table>



### Ejemplo


#### Solicitud usando cURL


```
curl \
  -H 'Accept:application/json' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer BEARER_TOKEN' \
  'https://api.notimation.com/api/v1/sms'
```



#### Respuesta formateada


```
{
  status: "success",
  data: [
    {
      id: 123456789,
      phone_number: {
        number: "115554444",
        formatted_number: "(11) 5555-4444",
        international_number: "+5491155554444",
        is_mobile: true,
        is_invalid: false,
        invalid_reason: null,
        operator: "TELECOM ARGENTINA SOCIEDAD ANONIMA",
        city: "AMBA"
      },
      direction: "outbound",
      content: "Mensaje de ejemplo",
      status: "sent",
      status_message: null,
      created_at: "2019-07-26T00:48:03.000000Z",
      programmed_at: "2019-07-26T00:48:03.000000Z",
      sent_at: "2019-07-26T00:48:03.000000Z"
    },
    {...}
  ]
}
```



## Ver un mensaje (SHOW)

Descripción: Muestra el detalle de un mensaje


```
GET https://api.notimation.com/api/v1/sms/ID
```



### Parámetros

Los parámetros se pasan dentro de la URL


<table>
  <tr>
   <td><strong>Parámetro</strong>
   </td>
   <td><strong>Obligatorio</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
   <td><strong>Ejemplo</strong>
   </td>
  </tr>
  <tr>
   <td><code>ID</code>
   </td>
   <td>si
   </td>
   <td>ID interno del mensaje.
   </td>
   <td>1
   </td>
  </tr>
</table>



### Respuesta

El payload presente en “data” es el mensaje solicitado, con esta información:


<table>
  <tr>
   <td><strong>Variable</strong>
   </td>
   <td><strong>Tipo</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
  </tr>
  <tr>
   <td><code>id</code>
   </td>
   <td><code>integer</code>
   </td>
   <td>Número interno del mensaje
   </td>
  </tr>
  <tr>
   <td><code>phone_number</code>
   </td>
   <td><code>

<a href="#heading=h.ubhsywfcnhua">phone_number object</a></code>
   </td>
   <td>Información completa del número telefónico, en formato del objeto phone_number (

<a href="#heading=h.ubhsywfcnhua">ver documentación</a>)
   </td>
  </tr>
  <tr>
   <td><code>direction</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Dirección del mensaje:
<p>
“outbound” → SMS saliente
<p>
“inbound” → SMS entrante
   </td>
  </tr>
  <tr>
   <td><code>content</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Contenido del mensaje
   </td>
  </tr>
  <tr>
   <td><code>status</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Alguno de los siguientes estados internos: 
<p>
“pending” → Mensaje recién ingresado en la plataforma
<p>
“cancelled” → Mensaje cancelado internamente antes de llegar a la cola de envíos
<p>
“queued” → Mensaje esperando en cola de envíos
<p>
“dispatched” → Mensaje despachado a la Telco
<p>
“sent” → Mensaje enviado
<p>
“error” → Mensaje con error (no pudo ser enviado)
<p>
“received” → Único status para mensajes entrantes
   </td>
  </tr>
  <tr>
   <td><code>status_message</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Comentario adicional explicativo para algunos status
   </td>
  </tr>
  <tr>
   <td><code>created_at</code>
   </td>
   <td><code>date</code>
   </td>
   <td>Fecha de creación interna
   </td>
  </tr>
  <tr>
   <td><code>programmed_at</code>
   </td>
   <td><code>date</code>
   </td>
   <td>Fecha en que el envío fue programado
   </td>
  </tr>
  <tr>
   <td><code>sent_at</code>
   </td>
   <td><code>date</code>
   </td>
   <td>Fecha en que se realizó efectivamente el envío
   </td>
  </tr>
</table>



### Ejemplo


#### Solicitud usando cURL


```
curl \
  -H 'Accept:application/json' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer BEARER_TOKEN' \
  'https://api.notimation.com/api/v1/sms/ID_MENSAJE'
```



#### Respuesta formateada


```
{
  status: "success",
  data: {
    id: 123456789,
    phone_number: {
      number: "115554444",
      formatted_number: "(11) 5555-4444",
      international_number: "+5491155554444",
      is_mobile: true,
      is_invalid: false,
      invalid_reason: null,
      operator: "TELECOM ARGENTINA SOCIEDAD ANONIMA",
      city: "AMBA"
    },
    direction: "outbound",
    content: "Mensaje de ejemplo",
    status: "sent",
    status_message: null,
    created_at: "2019-07-26T00:48:03.000000Z",
    programmed_at: "2019-07-26T00:48:03.000000Z",
    sent_at: "2019-07-26T00:48:03.000000Z"
}
```



# Objeto phone_number

El objeto **phone_number **permite obtener información detallada sobre números telefónicos. Fue diseñada para ofrecer a nuestros clientes la posibilidad de validar números telefónicos desde cualquier aplicación.


## Ver un teléfono (SHOW)


```
GET https://api.notimation.com/api/v1/phone_number/COUNTRY_CODE/NUMBER
```



### Parámetros

Los parámetros se pasan dentro de la URL


<table>
  <tr>
   <td><strong>Parámetro</strong>
   </td>
   <td><strong>Obligatorio</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
   <td><strong>Ejemplo</strong>
   </td>
  </tr>
  <tr>
   <td><code>COUNTRY_CODE</code>
   </td>
   <td>si
   </td>
   <td>Código de país de 2 caracteres
   </td>
   <td>“ar”
   </td>
  </tr>
  <tr>
   <td><code>ID</code>
   </td>
   <td>si
   </td>
   <td>Número telefónico a consultar
   </td>
   <td>1155554444
   </td>
  </tr>
</table>



### Respuesta

El payload presente en “data” es el mensaje solicitado, con esta información:


<table>
  <tr>
   <td><strong>Variable</strong>
   </td>
   <td><strong>Tipo</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
  </tr>
  <tr>
   <td><code>number</code>
   </td>
   <td><code>integer</code>
   </td>
   <td>Número telefónico
   </td>
  </tr>
  <tr>
   <td><code>formatted_number</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Número telefónico con formato
   </td>
  </tr>
  <tr>
   <td><code>international_number</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Representación internacional del número telefónico
   </td>
  </tr>
  <tr>
   <td><code>is_mobile</code>
   </td>
   <td><code>boolean</code>
   </td>
   <td>Si es móvil
   </td>
  </tr>
  <tr>
   <td><code>is_invalid</code>
   </td>
   <td><code>boolean</code>
   </td>
   <td>Si es un teléfono inválido
   </td>
  </tr>
  <tr>
   <td><code>invalid_reason</code>
   </td>
   <td><code>string</code>
   </td>
   <td>En caso de ser inválido, cuál es el motivo
   </td>
  </tr>
  <tr>
   <td><code>operator</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Razón social que opera dicho número
   </td>
  </tr>
  <tr>
   <td><code>city</code>
   </td>
   <td><code>string</code>
   </td>
   <td>Ciudad geográfica del número
   </td>
  </tr>
</table>



### Ejemplo


#### Solicitud usando cURL


```
curl \
  -H 'Accept:application/json' \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer BEARER_TOKEN' \
  'https://api.notimation.com/api/v1/phone_number/COUNTRY/NUMBER'
```



#### Respuesta formateada


```
{
  status: "success",
  data: {
    number: "115554444",
    formatted_number: "(11) 5555-4444",
    international_number: "+5491155554444",
    is_mobile: true,
    is_invalid: false,
    invalid_reason: null,
    operator: "TELECOM ARGENTINA SOCIEDAD ANONIMA",
    city: "AMBA"
}
```



# Webhooks

El sistema cuenta con Webhooks para la notificación de eventos. Los usos más habituales son: conocer el estado de un mensaje despachado, y recibir los mensajes entrantes.

Los webhooks se configuran desde el panel de control, y pueden asignarse a un evento específico o para todos los eventos.


## Eventos disponibles

Se puede configurar una URL para cada tipo de evento, o para cualquier evento usando el tipo especial *, que hará que cualquier cambio de estado de mensajes enviados, o cualquier mensaje recibido sea notificado al mismo endpoint.


<table>
  <tr>
   <td colspan="2" ><strong>Evento</strong>
   </td>
   <td><strong>Descripción</strong>
   </td>
  </tr>
  <tr>
   <td colspan="2" ><code>*</code>
   </td>
   <td>Ejecutar el webhook ante cualquier evento
   </td>
  </tr>
  <tr>
   <td colspan="2" ><code>sms_cancelled</code>
   </td>
   <td>El mensaje fue cancelado antes de ser enviado
   </td>
  </tr>
  <tr>
   <td colspan="2" ><code>sms_queued</code>
   </td>
   <td>El mensaje fue agregado a la cola de envíos
   </td>
  </tr>
  <tr>
   <td colspan="2" ><code>sms_dispatched</code>
   </td>
   <td>El mensaje fue despachado
   </td>
  </tr>
  <tr>
   <td colspan="2" ><code>sms_sent</code>
   </td>
   <td>El mensaje fue enviado positivamente
   </td>
  </tr>
  <tr>
   <td colspan="2" ><code>sms_error</code>
   </td>
   <td>Ocurrió un error al enviar el mensaje
   </td>
  </tr>
  <tr>
   <td colspan="2" ><code>sms_received</code>
   </td>
   <td>Mensaje entrante recibido
   </td>
  </tr>
</table>



## Ejemplo de solicitud

La información se envía en requests con método POST y Content-Type "application/json". Con lo cual, el payload llega como parte del body del post, en formato JSON:


```
{
  sms_id: 9123852312,
  event: "sms_received",
  message: null,
  data: 
  {
    id: 9123852312,
    phone_number: 
    {
      number: "1144445555",
      formatted_number: "(11) 4444-5555",
      international_number: "+5491144445555",
      is_mobile: true,
      is_invalid: false,
      invalid_reason: null,
      operator: "COMPAÑIA DE RADIOCOMUNICACIONES MOVILES SA",
      city: "AMBA"
    },
    direction: "inbound",
    content: "Este es el contenido del mensaje entrante",
    status: "received",
    status_message: null,
    created_at: "2019-08-06T16:08:03.000000Z",
    programmed_at: null,
    sent_at: null
  }
}
```



## Ejemplo para recibir webhooks usando PHP

Suponiendo que este webhook se encuentra disponible en [https://example.org/test.php](https://example.org/test.php), y que fue configurado para recibir todos los eventos (`*`), cada vez que Notimation envíe información al mismo escribirá una línea en el archivo webhook.log, con el `sms_id` del evento recibido:


```
<?php

// en esta línea leemos el contenido en el body del request
$payload = json_decode(file_get_contents('php://input'));

// aquí agregamos una línea en el archivo webhook.log con el ID del mensaje
file_put_contents("webhook.log", "Nuevo evento recibido para el mensaje # ". $payload->sms_id . "\n", FILE_APPEND);

```


## Respuesta esperada por el servidor y notificaciones

Notimation espera que el servidor devuelva **<code>código 200</code></strong>. En caso de recibir cualquier otro código, la ejecución de ese evento será marcada con error, y se enviará un e-mail al administrador para notificarlo. 
