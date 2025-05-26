## Run Develop

# Portal de Acceso:

Levantar la interfaz de angular con:

| ng serve portal-access


Para acceder desde el navegador la url es:

| http://localhost:4200

No obstante para acceder como lo redirige el OmadaController la URL debe ser de la forma:

| http://localhost:4200/?clientMac=B4-6B-FC-D2-7C-5B&clientIp=192.168.1.16&t=1742766560&site=67d41b41fa1be0473141c355&redirectUrl=http%3A%2F%2Fwww.msftconnecttest.com%2Fredirect&apMac=50-91-E3-FF-6F-E2&ssidName=auster_wifi&radioId=0

En caso de querer ingresar desde un telefono o desde una pc externa es necesario hacerun port-forward a la ip de la LAN
Por ejemplo asumiendo que estas en linux, y se tiene la herramienta **socat** instalada se debe ejecutar en una terminal (en el sistema, no en la terminal dentro del VSCode dado que se ejecuta dentro del entorno dockerizado).

| socat TCP-LISTEN:14200,fork,reuseaddr TCP4:localhost:4200

Esto disponibiliza en todas las ip de la maquina en el puerto 14200 una redireccion al puerto 4200 de localhost. Que el VSCode ya hizo una redireccion de ese puerto al puerto 4200 de la imagen de docker donde esta corriendo la aplicacion de angular levantada con ng serve.

Y por ejemplo si la ip del host, dentro de la red local es **192.168.1.13** se puede acceder con la URL:

| http://192.168.1.13:14200/?clientMac=B4-6B-FC-D2-7C-5B&clientIp=192.168.1.16&t=1742766560&site=67d41b41fa1be0473141c355&redirectUrl=http%3A%2F%2Fwww.msftconnecttest.com%2Fredirect&apMac=50-91-E3-FF-6F-E2&ssidName=auster_wifi&radioId=0

# Compilar una version estatica para pasar al backend

Dentro de la terminal de VSCode, hacer un build con el comando:

| ng build --configuration=production portal-access

Luego, desde una terminal del sistema (no la de VSCode) borrar el contenido de la carpeta ***portal-server/src/main/resources/static/*** del código del backend.
Por ejemplo si estamos parados en la carpeta donde tenemos el código de la ui ***portla-ui*** seria:

| rm -rf ../portal-server/src/main/resources/static/*

Y copiar todo el contenido de la ui compilada mediante

| cp -rfp ./dist/portal-access/* ../portal-server/src/main/resources/static/