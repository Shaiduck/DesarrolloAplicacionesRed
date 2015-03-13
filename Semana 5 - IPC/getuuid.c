// PARA COMPILAR SE REQUIERE LA SIGUIENTE INSTRUCCION
// gcc -o getuuid getuuid.c -lblkid

#include <stdio.h>
#include <string.h>
#include <err.h>
#include <blkid/blkid.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <errno.h>
#include <stdlib.h>
#include <fcntl.h>
#include <limits.h>
#include <time.h>


int main (int argc, char *argv[]) {


   //blkid_probe pr = blkid_new_probe_from_filename(argv[1]);
   blkid_probe pr = blkid_new_probe_from_filename("/dev/sdb1");
   //if (!pr) {
      //err(1, "Failed to open %s", argv[1]);
   //}

   int fd;
   int len;
   char buf[PIPE_BUF];
   time_t tp;

   printf("Se esta ejecutando %d\n", getpid());

   if((fd = open("fifo1", O_WRONLY))<0)
   {
      perror("popen");
      exit(EXIT_FAILURE);
   }

   if(1)
   {
      const char *uuid;
      blkid_do_probe(pr);
      blkid_probe_lookup_value(pr, "UUID", &uuid, NULL);


      if (strlen(uuid) > 1) {

         len=sprintf(buf, "%s\n", uuid);

         if((write(fd, buf, len+1))<0)
         {
            perror("write");
            close(fd);
            exit(EXIT_FAILURE);
         }
         sleep(3);
      } else {
         len=sprintf(buf, "%s\n has no UUID", argv[1]);
      }
      close(fd);
      exit(EXIT_SUCCESS);
      blkid_free_probe(pr);
   }
   return 0;


}