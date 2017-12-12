package com.adenon.smpp.server.core;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.adenon.api.smpp.core.IOReactorStorage;
import com.adenon.commons.log.ILogger;
import com.adenon.smpp.server.managers.ServerLogManager;

public class ServerIOReactorAccept extends Thread {

    private final ILogger             logger;
    private ServerSocket              smppServerSocket;
    private int                       index    = 0;
    private final ServerApiProperties apiProperties;
    private final IOReactorStorage    smppIOReactorStorage;
    private final String              serverName;
    private final ServerApiDelegator  serverApiDelegator;
    private boolean                   shutdown = false;
    private ServerSocketChannel       ssChannel;

    public ServerIOReactorAccept(final String serverName,
                                 final ServerApiDelegator serverApiDelegator,
                                 final ServerApiProperties apiProperties,
                                 final ILogger logger,
                                 final IOReactorStorage smppIOReactorStorage) throws Exception {
        super("ServerIOReactorAccept");
        this.serverName = serverName;
        this.serverApiDelegator = serverApiDelegator;
        this.apiProperties = apiProperties;
        this.logger = logger;
        this.smppIOReactorStorage = smppIOReactorStorage;
    }

    @Override
    public void run() {
        this.ssChannel = null;
        try {
            this.ssChannel = ServerSocketChannel.open();
            this.ssChannel.configureBlocking(true);
            this.ssChannel.socket().bind(new InetSocketAddress(this.getApiProperties().getPort()));
        } catch (final Exception e) {
            this.logger.error("ServerIOReactorAccept",
                              "run",
                              0,
                              null,
                              " : Error : " + e.getMessage(),
                              e);
            System.exit(0);
        }
        if (this.ssChannel == null) {
            System.exit(0);
        }
        while (true) {
            try {
                final SocketChannel socketChannel = this.ssChannel.accept();
                if (this.shutdown) {
                    return;
                }
                if (socketChannel != null) {
                    this.index++;
                    if (this.logger.isInfoEnabled()) {
                        this.logger.info("ServerIOReactorAccept",
                                         "run",
                                         0,
                                         null,
                                         "New connection : " + this.index + " from : " + socketChannel.socket().getInetAddress());
                    }
                    socketChannel.socket().setKeepAlive(true);
                    Socket clientSocket = socketChannel.socket();
                    try {
                        final ServerLogManager logManager = this.serverApiDelegator.getLogManager();
                        ILogger clogger;
                        switch (logManager.getLogType()) {
                            case LogAllInOneFile:
                                clogger = logManager.getLogger();
                                break;
                            case LogGroupSeparetly:
                                clogger = logManager.getLogControler().getLogger(this.serverApiDelegator.getServerName());
                                break;
                            case LogAllSeparetly:
                                clogger = logManager.getLogControler().getLogger(this.serverApiDelegator.getServerName());
                                break;
                            default:
                                clogger = this.logger;
                                break;
                        }
                        final String ipAddr = this.removeChars(socketChannel.socket().getInetAddress().toString());
                        final ServerIOReactor serverIOReactor = new ServerIOReactor(clogger,
                                                                                    this.serverName,
                                                                                    this.serverApiDelegator,
                                                                                    socketChannel,
                                                                                    ipAddr,
                                                                                    this.getApiProperties().getPort());
                        serverIOReactor.initialize();
                        this.smppIOReactorStorage.addSmppIOReactor(serverIOReactor);
                    } catch (final OutOfMemoryError error) {
                        this.logger.error("ServerIOReactorAccept",
                                          "run",
                                          0,
                                          null,
                                          " : Error : " + error.getMessage(),
                                          error);
                        if (clientSocket != null) {
                            clientSocket.close();
                            clientSocket = null;
                        }
                    }
                }
            } catch (final Exception e) {
                this.logger.error("ServerIOReactorAccept",
                                  "run",
                                  0,
                                  null,
                                  " : Error : " + e.getMessage(),
                                  e);
            }
        }
    }

    private String removeChars(final String string) {
        final StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            final char charAt = string.charAt(i);
            if ((charAt == '.') || ((charAt >= '0') && (charAt <= '9'))) {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    public void shutdown() {
        try {
            this.smppServerSocket.close();
        } catch (final Exception exc) {
            exc.printStackTrace();
        }
    }

    public ServerApiProperties getApiProperties() {
        return this.apiProperties;
    }

    public boolean isShutdown() {
        return this.shutdown;
    }

    public void setShutdown(final boolean shutdown) {
        this.shutdown = shutdown;
    }

    public ServerSocketChannel getSsChannel() {
        return this.ssChannel;
    }

    public void setSsChannel(final ServerSocketChannel ssChannel) {
        this.ssChannel = ssChannel;
    }
}