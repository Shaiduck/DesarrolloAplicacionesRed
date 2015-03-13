#include <stdio.h>
#include <security/pam_appl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <errno.h>
#include <fcntl.h>
#include <limits.h>

struct pam_response *reply;

int null_conv(int num_msg, const struct pam_message **msg, struct pam_response **resp, void *appdata_ptr) {

        *resp = reply;
        return PAM_SUCCESS;

}

char *rdfifo()
{
        int fd;
        int len;
        char *buf=malloc(PIPE_BUF+1);
        mode_t mode= 0666;

        if ((mkfifo("fifo1", mode))<0)
        {
                remove("fifo1");
                mkfifo("fifo1", mode);
                //perror("mkfifo");
                //exit(EXIT_FAILURE);
        }

        if((fd = open ("fifo1", O_RDONLY))<0)
        {
                perror("open");
                exit(EXIT_FAILURE);
        }

        //while((len = read(fd, buf, PIPE_BUF - 1))>0)
                //printf("rdfifo read: %s", buf);
        read(fd, buf, PIPE_BUF-1);
        close(fd);

        return buf;
}

void creaArchivo(char *user, char *uuid)
{
        char direccion[100];
        strcpy(direccion, "./");
        strcat(direccion, user);
        //strcat(direccion, ".txt");

        if(access(direccion, F_OK) == -1) //ARCHIVO NO EXISTE
        {
                FILE *archivo;

                
                if(uuid[0] != '\0'){
                        archivo = fopen(direccion, "w+");
                        fputs(uuid, archivo);
                        fclose(archivo);
                        printf("Su usuario se ha enlazado con el UUID exitosamente\n");
                }
                else
                {
                        printf("NO HA INSERTADO UNA USB, INTENTE DE NUEVO");
                }
        }
        else
        {
                FILE *archivo;
                char buff[255];

                archivo = fopen(direccion, "r");
                fgets(buff, 255, (FILE*)archivo);
                if (strcmp(uuid, buff) == 0)
                {
                        fprintf(stdout, "El usuario y la UUID son correctos. AUTENTICADO\n");
                }
                else
                {
                        fprintf(stdout, "El usuario y la UUID no son correctos. No Autenticado\n");
                }
        }
}

static struct pam_conv conv = { null_conv, NULL };

int main(int argc, char *argv[]) {

        int retval;
        char *user, *pass, uuid[100];   

        if(argc == 3) {

                user = argv[1];
                pass = strdup(argv[2]);

        } else { 

                fprintf(stderr, "Usage: login [username] [password]\n");
                exit(1);

        }

        strcpy(uuid, rdfifo());
        //printf("%s", uuid);
        int x = authenticate("system-auth", user, pass);
        if(x!=1)
                creaArchivo(user, uuid);
        //return authenticate("system-auth", user, pass);
        return 0;

}   

int authenticate(char *service, char *user, char *pass) {

        pam_handle_t *pamh = NULL;
        int retval = pam_start(service, user, &conv, &pamh);

        if (retval == PAM_SUCCESS) {

                reply = (struct pam_response *)malloc(sizeof(struct pam_response));
                reply[0].resp = pass;
                reply[0].resp_retcode = 0;

                retval = pam_authenticate(pamh, 0);

                if (retval == PAM_SUCCESS)
                        fprintf(stdout, "El usuario y contrasena coinciden\n");

                else
                        fprintf(stdout, "El usuario y contrasena no coinciden\n");

                pam_end(pamh, PAM_SUCCESS);

                return ( retval == PAM_SUCCESS ? 0:1 );

        }

        return ( retval == PAM_SUCCESS ? 0:1 );
}

