#    Makefile - Desarrollo de Aplicaciones en Red Servidor
#    all	- Compilar todo
#    clean	- Limpiar archivos compilados
#    login	- Compilar login

BIN = ./
EXECUTABLES = login
# Agregar cabeceras .h
INCLUDES = 
#Usar gcc
GCC = gcc 
#Bibliotecas
LIBS = -lm -lpthread -lpam
#FLAGS =  -std=c99
CFLAGS = -I.

#'make all' creará todos los ejecutables
all: 	$(EXECUTABLES)

#'make clean' eleminar archivos objetos
clean:
	rm -f $(BIN)/login *.o

# Compilar los archivos .c
%.o:%.c $(INCLUDES) 
	$(GCC) $(FLAGS) $(CFLAGS) -c $<

# Generar ejecutables individuales
login: login.o
	$(GCC) $(FLAGS) $(CFLAGS) -o $(BIN)/login login.o $(LIBS)

