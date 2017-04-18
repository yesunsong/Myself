﻿#ifndef __NH_HNSocket_H__
#define __NH_HNSocket_H__

#include "cocos2d.h"

USING_NS_CC;

#if (CC_TARGET_PLATFORM == CC_PLATFORM_WIN32)
#include <winsock2.h>
typedef int socklen_t;
#define ioctl ioctlsocket
#define Errno GetLastError()
enum {
    SHUT_RD = 0,
#define SHUT_RD			SHUT_RD
    SHUT_WR,
#define SHUT_WR			SHUT_WR
    SHUT_RDWR
#define SHUT_RDWR		SHUT_RDWR
};
#else
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <fcntl.h>
typedef int SOCKET;
#define INVALID_SOCKET		-1
#define SOCKET_ERROR		-1
#define BLOCKREADWRITE      MSG_WAITALL
#define NONBLOCKREADWRITE   MSG_DONTWAIT
#define SENDNOSIGNAL        MSG_NOSIGNAL
#define Errno errno
#endif


#include "HNBaseType.h"
#include "globel/HNCommonMarco.h"

#define TCP_CONNECT_OUTTIME		-1
#define TCP_CONNECT_ERROR		-2

namespace HN {

const char *strError(int x) ;

class HNSocket {
public:
    static std::vector<std::string> getIpAddress(const char *host);
    static struct sockaddr* getSockaddr();
    static int getLength();
    static bool isValidIP(const std::string& host);
    static bool isIPV6Net(const std::string& domainStr);
    static int convertDomainToIpAddress(const char* szName);
    static std::string domainToIP(const char* pDomain);

public:
    HNSocket(void);
    virtual ~HNSocket(void);

public:
    bool create();
    INT connect();
    INT connect(const CHAR* ip, INT port);
    INT connectWithHost(const CHAR* host, INT port);
    INT send(const CHAR* data, INT dataSize);
    INT recv(CHAR* buffer, INT size);
    bool close();
    bool shutdown();
    INT select(timeval* timeOut);
    INT isConnected();

public:
    bool setNonblocking(bool bNb);
    bool setSoRcvtimeo(struct timeval& tv);
    bool setSoSndtimeo(struct timeval& tv);
    bool setSoRcvbuf(int x);
    bool soRcvbuf();
    bool setSoSendbuf(int x);
    bool soSendbuf();
    unsigned short getSockPort();

public:
    HN_PROPERTY(std::string, IP, ip)

    HN_PROPERTY(std::string, Host, host)

    HN_PROPERTY(INT, Port, port)

    HN_PROPERTY_READONLY(SOCKET, Socket, socket)

private:
    std::vector<std::string>		_ips;

};

};

#endif	//__NH_HNSocket_H__
