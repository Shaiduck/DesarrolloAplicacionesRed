#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

#define PORT 67
#define DHCP_H
#define DHCP_CLIENT_PORT        68      
#define DHCP_SERVER_PORT        67

#define DHCP_XID_LEN		4
#define DHCP_SECS_LEN		2
#define DHCP_FLAGS_LEN		2

#define DHCP_MAXMAC		6
#define DHCP_CHADDR_LEN         10
#define DHCP_SNAME_LEN          64
#define DHCP_FILE_LEN           128
#define DHCP_OPTIONS_LEN        512
#define DHCP_MIN_OPTIONS_LEN    68
#define DHCP_MAXIP		4

struct dhcp_msg {
  uint8_t op;                       // Message opcode/type
  uint8_t htype;                    // Hardware addr type
  uint8_t hlen;                     // Hardware addr length
  uint8_t hops;                     // Number of relay agent hops from client
  uint32_t xid;         // Transaction ID
  uint16_t secs;                    // Seconds since client started looking
  uint16_t flags;                   // Flag bits
  struct in_addr ciaddr;                  // Client IP address (if already in use)
  struct in_addr yiaddr;                  // Client IP address (if already in use)
  struct in_addr siaddr;                  // Client IP address (if already in use)
  struct in_addr giaddr;                  // Client IP address (if already in use)
  unsigned char chaddr[DHCP_MAXMAC + DHCP_CHADDR_LEN];  // Client hardware address 
  char sname[DHCP_SNAME_LEN];             // Server name
  char file[DHCP_FILE_LEN];               // Boot filename
  char options[0];               // Optional parameters (actual length dependent on MTU).
};

void printNCharToHexStart(char values[], int numbytes, int startindex)
{
	int i;
	printf("0x");
	for (i = startindex; i < numbytes; i++){
		printf("%02x", values[i]);
	}
	printf("\n");
}

void printNCharToHex(char values[], int numbytes)
{
	int i;
	printf("0x");
	for (i = 0; i < numbytes; i++){
		printf("%02x", values[i]);
	}
	printf("\n");
}

void printCharToHex(char values[])
{
	int i;
	printf("0x");
	for (i = 0; i < strlen(values); i++){
		printf("%02x", values[i]);
	}
	printf("\n");
}

void printDHCPMessage(struct dhcp_msg * message)
{
	printf("=========================\n");
    printf("OP:     \t%i\n", message->op);
    printf("HTYPE:  \t%i\n", message->htype);
    printf("HLEN:   \t%i\n", message->hlen);
    printf("HOPS:   \t%i\n", message->hops);
    printf("XID:    \t%i\n", message->xid);
    printf("SECS:   \t%i\n", message->secs);
    printf("FLAGS:  \t%i\n", message->flags);
    //printf("CIADDR: \t%i\n", message->ciaddr);
    //printf("YIADDR: %i\t\n", message->yiaddr);
    //printf("SIADDR: %i\t\n", message->siaddr);
    //printf("GIADDR: %i\t\n", message->giaddr);
    printf("CIADDR: \t%s\n", inet_ntoa(message->ciaddr));
    printf("YIADDR: \t%s\n", inet_ntoa(message->yiaddr));
    printf("SIADDR: \t%s\n", inet_ntoa(message->siaddr));
    printf("GIADDR: \t%s\n", inet_ntoa(message->giaddr));
    printf("MACADD: \t%02x:%02x:%02x:%02x:%02x:%02x\n", message->chaddr[0], 
                                                        message->chaddr[1], 
                                                        message->chaddr[2], 
                                                        message->chaddr[3], 
                                                        message->chaddr[4], 
                                                        message->chaddr[5]);
    printf("=========================\n");
}

void fillDHCP(struct dhcp_msg * message)
{
	message -> op = 1;
	message -> htype = 2;
	message -> hlen = 6;
	message -> hops = 0;
	message -> xid = htonl(1000);
	message -> secs = htons(0);
	message -> flags = htons(0x8000);
	inet_aton("0.0.0.0", &message -> ciaddr);
	inet_aton("192.168.0.2",&message -> yiaddr);
	inet_aton("192.168.0.1",&message -> siaddr);
	inet_aton("0.0.0.0",&message -> giaddr);
	message -> chaddr[0] = 0x00;
	message -> chaddr[1] = 0x1A;
	message -> chaddr[2] = 0x80;
	message -> chaddr[3] = 0x80;
	message -> chaddr[4] = 0x2C;
	message -> chaddr[5] = 0xC3;
	message -> options[0] = 53;
	message -> options[1] = 1;
	message -> options[2] = 1;
	message -> options[3] = 255;
}

int main()
{
	int on = 1;
	struct sockaddr_in servaddr, cliaddr, rservaddr;
	int sockfd, listenfd, connfd;

	bzero(&cliaddr, sizeof(cliaddr));
	cliaddr.sin_port = htons(DHCP_CLIENT_PORT);
	cliaddr.sin_family = AF_INET;
	cliaddr.sin_addr.s_addr = inet_addr("255.255.255.255");
	if((sockfd = socket(AF_INET, SOCK_DGRAM, 0))<0)
		perror("can't create socket");

	bzero(&servaddr, sizeof(servaddr));
	servaddr.sin_port = htons(DHCP_SERVER_PORT);
	servaddr.sin_family = AF_INET;
	servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
	if((listenfd = socket(AF_INET, SOCK_DGRAM, 0))<0)
		perror("can't create socket");

	if(bind(listenfd, (struct sockaddr*)&servaddr, sizeof(servaddr))<0)
		perror("bind");

	struct dhcp_msg message;
	bzero(&message,sizeof(message));
	fillDHCP(&message);

	while(1)
	{
		printf("receiving server data\n");
		struct dhcp_msg receiveMessage;
		bzero(&receiveMessage, sizeof(receiveMessage));
		int serv_len = sizeof(receiveMessage);
		if(recvfrom(listenfd, &receiveMessage, sizeof(receiveMessage),0,(struct sockaddr*)&servaddr, &serv_len)<0)
			perror("can't receive");

		printDHCPMessage(&receiveMessage);

		inet_aton("192.168.1.102", &receiveMessage.yiaddr);
		receiveMessage.op = 1;
		receiveMessage.htype = 1;
		receiveMessage.options[0] = 53;
		receiveMessage.options[1] = 1;
		receiveMessage.options[2] = 5;
		receiveMessage.options[3] = 255;

		printf("Sending data to server\n");

		if(setsockopt(sockfd, SOL_SOCKET, SO_BROADCAST, &on, sizeof(on))<0)
			printf("setsockopt");
		if(sendto(sockfd, &receiveMessage, sizeof(receiveMessage), 0, (struct sockaddr*)&cliaddr, sizeof(cliaddr)<0))
			printf("sendto\n");

		printDHCPMessage(&receiveMessage);
	}
	close(sockfd);
	return 0;
}