#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

#define PORT 6789

struct mensaje
{
	uint8_t id;
	uint32_t xid;
	char buf[80];
	char cierra[80];
};

void llenaMensaje(struct mensaje * mess)
{
	mess -> id = 2; //1 servidor 2 cliente
	mess -> xid = htonl(1000);
	sprintf(mess->buf, "He Respondido al Llamado\n");
	sprintf(mess->cierra, "stop\n");
}

void imprimeMensaje(struct mensaje * mess)
{
	printf("ID \n", mess->id);
	printf("Mensaje : %s\n", mess->buf);
	printf("XID:    \t%i\n", mess->xid);
}

int main(int argc, char *argv[]){


	int sin_len;	
	int socket_descriptor;
	struct sockaddr_in sin;
	printf("Esperando EL LLAMADO DE LA SELVA\n");
	bzero(&sin, sizeof(sin));
 	sin.sin_family = AF_INET;
	sin.sin_addr.s_addr = htonl(INADDR_ANY);
	sin.sin_port = htons(PORT);
	struct mensaje mess;
	bzero(&mess, sizeof(mess));
	sin_len = sizeof(mess);

	//Crea un socket UDP y unirlo al puerto
	socket_descriptor = socket(AF_INET, SOCK_DGRAM, 0);
	bind(socket_descriptor, (struct sockaddr *)&sin, sizeof(sin));
	
	//Ciclo donde se reciben datos
	while(1){
		recvfrom(socket_descriptor, &mess, sizeof(mess), 0, (struct sockaddr *)&sin, &sin_len);
		//printf("Respuesta del Servidor: %s\n", message);
		//imprimeMensaje(&mess);
		if(strncmp(mess.cierra), "sleep", 5) == 0)
		{	
			mess.xid = 0;
			mess.id = 0;
			sprintf(mess->buf, "\n");
			sprintf(mess->cierra, "\n");
			printf("El zombie vuelve a la espera\n");
			close(socket_descriptor);

		}
		else if(strncmp(mess.cierra), "termina", 7)==0)
		{
			printf("El zombie vuelve a la tumba\n");
			close(socket_descriptor);
			break;
		}
		else if (strncmp(mess.cierra, "start", 5) == 0)
		{
			printf("Se ha recibido EL LLAMADO DE LA SELVA\n");
			mess.xid = htonl(1000);
			imprimeMensaje(&mess);
			close(socket_descriptor);

			mess.id = 1;
			sprintf(mess.buf, "He respondido al llamado de la selva");
			sprintf(mess.cierra, "stop\n");
			int on = 1;
			int iter = 0;
			struct sockaddr_in address;
			//Inicia la estructura de direcciones de socket para los protocolos
			memset(&(address.sin_zero), '\0', 8);		
			address.sin_family = AF_INET;
			address.sin_addr.s_addr = inet_addr("255.255.255.255");
			address.sin_port = htons(PORT);
			//Crear socket UDP
			socket_descriptor = socket(AF_INET, SOCK_DGRAM, 0);
		        if (setsockopt(socket_descriptor, SOL_SOCKET, SO_BROADCAST, &on, sizeof(on)) < 0)
		        {
		                perror("Error - BROADCAST setsockopt()");
		                return 1;
		        }
			//Enviar datos 
			//for (iter = 0; iter <= 5; iter++){
				if (sendto(socket_descriptor, &mess, sizeof(mess), 0, (struct sockaddr *) &address, sizeof(address)) == -1)
				{
					perror("Client - sendto");
					return 1;
				}
//			}	
			close(socket_descriptor);
			printf("Mensaje enviado\n");
			//break;

		}
	}
	return 0;
}

