package com.adenon.utils.binary.file;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.adenon.commons.log.ILogger;
import com.adenon.commons.log.LogManager;
import com.adenon.utils.buffer.common.ByteBufferWrapper;

public class BinaryFile {

    private static final ILogger logger               = LogManager.getLogger(BinaryFile.class);

    private final String         fileName;
    private final String         indexFileName;
    private final String         posFileName;

    private SeekableByteChannel  byteChannelForFile;
    private SeekableByteChannel  byteChannelForIndex;
    private SeekableByteChannel  readIndexChannel;
    private SeekableByteChannel  positionChannel;
    private SeekableByteChannel  readFile;

    private int                  currentPosition      = 0;
    private int                  totalItemCount       = 0;

    private final ByteBuffer     indexWriteByteBuffer = ByteBuffer.allocateDirect(32);
    private final ByteBuffer     indexReadByteBuffer  = ByteBuffer.allocateDirect(32);
    private final ByteBuffer     posByteBuffer        = ByteBuffer.allocateDirect(32);

    private final Object         lockObject           = new Object();

    private Path                 path;
    private Path                 indexPath;
    private Path                 posPath;

    private final String         folder;

    public BinaryFile(final String folder,
                      final String fileName) {
        this.folder = folder;
        this.fileName = fileName + ".bin";
        this.indexFileName = fileName + ".inx";
        this.posFileName = fileName + ".pos";

    }

    public void initialize() throws IOException {
        this.openFiles();
    }

    public void deInitialize() {
        if (this.byteChannelForFile != null) {
            try {
                this.byteChannelForFile.close();
            } catch (final IOException e) {
            }
        }
        if (this.byteChannelForIndex != null) {
            try {
                this.byteChannelForIndex.close();
            } catch (final IOException e) {
            }
        }
        if (this.readIndexChannel != null) {
            try {
                this.readIndexChannel.close();
            } catch (final IOException e) {
            }
        }
        if (this.positionChannel != null) {
            try {
                this.positionChannel.close();
            } catch (final IOException e) {
            }
        }
        if (this.readFile != null) {
            try {
                this.readFile.close();
            } catch (final IOException e) {
            }
        }
    }

    private void openFiles() throws IOException {
        synchronized (this.lockObject) {
            this.path = Paths.get(this.folder,
                                  this.fileName);
            final File file = this.path.toFile();

            this.indexPath = Paths.get(this.folder,
                                       this.indexFileName);
            final File indexFile = this.indexPath.toFile();

            this.posPath = Paths.get(this.folder,
                                     this.posFileName);
            final File posFile = this.posPath.toFile();

            if (!file.exists() && !indexFile.exists() && !posFile.exists()) {
                this.byteChannelForFile = Files.newByteChannel(this.path,
                                                               StandardOpenOption.CREATE,
                                                               StandardOpenOption.CREATE_NEW,
                                                               StandardOpenOption.WRITE);
                this.byteChannelForIndex = Files.newByteChannel(this.indexPath,
                                                                StandardOpenOption.CREATE,
                                                                StandardOpenOption.CREATE_NEW,
                                                                StandardOpenOption.WRITE);
                this.positionChannel = Files.newByteChannel(this.posPath,
                                                            StandardOpenOption.CREATE,
                                                            StandardOpenOption.CREATE_NEW,
                                                            StandardOpenOption.WRITE);
                this.writePosition();
            } else {
                if (!file.exists() || !file.exists() || !posFile.exists()) {
                    try {
                        file.renameTo(new File(this.fileName + "_" + System.currentTimeMillis()));
                        indexFile.renameTo(new File(this.indexFileName + "_" + System.currentTimeMillis()));
                        posFile.renameTo(new File(this.posFileName + "_" + System.currentTimeMillis()));
                    } catch (final Exception e) {
                    }
                    this.byteChannelForFile = Files.newByteChannel(this.path,
                                                                   StandardOpenOption.CREATE,
                                                                   StandardOpenOption.CREATE_NEW,
                                                                   StandardOpenOption.WRITE);
                    this.byteChannelForIndex = Files.newByteChannel(this.indexPath,
                                                                    StandardOpenOption.CREATE,
                                                                    StandardOpenOption.CREATE_NEW,
                                                                    StandardOpenOption.WRITE);
                    this.positionChannel = Files.newByteChannel(this.posPath,
                                                                StandardOpenOption.CREATE,
                                                                StandardOpenOption.CREATE_NEW,
                                                                StandardOpenOption.WRITE);
                    this.writePosition();
                } else {
                    this.byteChannelForFile = Files.newByteChannel(this.path,
                                                                   StandardOpenOption.APPEND);
                    this.byteChannelForIndex = Files.newByteChannel(this.indexPath,
                                                                    StandardOpenOption.APPEND);
                    this.positionChannel = Files.newByteChannel(this.posPath,
                                                                StandardOpenOption.READ,
                                                                StandardOpenOption.WRITE);
                    if (this.positionChannel.size() > 0) {
                        this.posByteBuffer.clear();
                        this.posByteBuffer.limit(4);
                        this.posByteBuffer.position(0);
                        if (this.positionChannel.read(this.posByteBuffer) != 4) {
                            throw new IOException("Position should be 4 bytes");
                        }
                        this.posByteBuffer.position(0);
                        this.currentPosition = this.posByteBuffer.getInt();
                        this.totalItemCount = ((int) this.byteChannelForIndex.size() / 16);
                    }
                }
            }

            this.readIndexChannel = Files.newByteChannel(this.indexPath,
                                                         StandardOpenOption.READ);
            this.readFile = Files.newByteChannel(this.path,
                                                 StandardOpenOption.READ);
        }
    }

    public void checkChannels() throws IOException {
        if (!this.byteChannelForFile.isOpen()) {
            this.byteChannelForFile = Files.newByteChannel(this.path,
                                                           StandardOpenOption.APPEND);
        }
        if (!this.byteChannelForIndex.isOpen()) {
            this.byteChannelForIndex = Files.newByteChannel(this.indexPath,
                                                            StandardOpenOption.APPEND);
        }
        if (!this.readIndexChannel.isOpen()) {
            this.readIndexChannel = Files.newByteChannel(this.indexPath,
                                                         StandardOpenOption.READ);
        }
        if (!this.positionChannel.isOpen()) {
            this.positionChannel = Files.newByteChannel(this.posPath,
                                                        StandardOpenOption.READ,
                                                        StandardOpenOption.WRITE);
        }
        if (!this.readFile.isOpen()) {
            this.readFile = Files.newByteChannel(this.path,
                                                 StandardOpenOption.READ);
        }

    }

    public ByteBufferWrapper seek(final int index) throws IOException {
        synchronized (this.lockObject) {
            this.checkChannels();
            final long calcPosition = index * 16;
            if (this.readIndexChannel.position() != calcPosition) {
                this.readIndexChannel.position(calcPosition);
            }
            this.indexReadByteBuffer.clear();
            this.indexReadByteBuffer.limit(16);
            if (this.readIndexChannel.position() == this.readIndexChannel.size()) {
                return null;
            }
            if (this.readIndexChannel.read(this.indexReadByteBuffer) != 16) {
                throw new IOException("Index file has not 16 bytes . Probably corrupted");
            }
            this.indexReadByteBuffer.position(0);
            final long start = this.indexReadByteBuffer.getLong();
            final long end = this.indexReadByteBuffer.getLong();
            final long size = end - start;
            if (this.readFile.position() != start) {
                this.readFile.position(start);
            }
            final ByteBufferWrapper bufferWrapper = new ByteBufferWrapper((int) (size));
            final ByteBuffer[] internalBuffers = bufferWrapper.getInternalBuffers();
            for (int i = 0; i < internalBuffers.length; i++) {
                if (this.readFile.read(internalBuffers[i]) != size) {
                    throw new IOException("Couldn't read expected size from file !!");
                }
            }
            bufferWrapper.limit((int) size);
            bufferWrapper.position(0);
            return bufferWrapper;
        }

    }

    public ByteBufferWrapper getNext() throws IOException {
        synchronized (this.lockObject) {
            this.checkChannels();
            final long calcPosition = this.currentPosition * 16;
            if (this.readIndexChannel.position() != calcPosition) {
                this.readIndexChannel.position(calcPosition);
            }
            this.indexReadByteBuffer.clear();
            this.indexReadByteBuffer.limit(16);
            if (this.readIndexChannel.position() == this.readIndexChannel.size()) {
                return null;
            }
            if (this.readIndexChannel.read(this.indexReadByteBuffer) != 16) {
                throw new IOException("Index file has not 16 bytes . Probably corrupted");
            }
            this.indexReadByteBuffer.position(0);
            final long start = this.indexReadByteBuffer.getLong();
            final long end = this.indexReadByteBuffer.getLong();
            final long size = end - start;
            if (this.readFile.position() != start) {
                this.readFile.position(start);
            }
            final ByteBufferWrapper bufferWrapper = new ByteBufferWrapper((int) (size));
            final ByteBuffer[] internalBuffers = bufferWrapper.getInternalBuffers();
            for (int i = 0; i < internalBuffers.length; i++) {
                if (this.readFile.read(internalBuffers[i]) != size) {
                    throw new IOException("Couldn't read expected size from file !!");
                }
            }
            this.currentPosition += 1;
            this.writePosition();
            bufferWrapper.limit((int) size);
            bufferWrapper.position(0);
            return bufferWrapper;
        }
    }

    private void writePosition() throws IOException {
        synchronized (this.lockObject) {
            this.posByteBuffer.clear();
            this.posByteBuffer.putInt(this.currentPosition);
            this.posByteBuffer.flip();
            this.positionChannel.position(0);
            this.positionChannel.write(this.posByteBuffer);
            // System.out.println("Writing [POS] : " + this.currentPosition);
        }

    }

    public int write(final ByteBufferWrapper bufferWrapper) throws IOException {
        synchronized (this.lockObject) {
            this.checkChannels();
            this.indexWriteByteBuffer.clear();
            final long start = this.byteChannelForFile.position();
            final long end = start + bufferWrapper.position();
            this.indexWriteByteBuffer.putLong(start);
            this.indexWriteByteBuffer.putLong(end);
            this.indexWriteByteBuffer.flip();
            this.byteChannelForIndex.write(this.indexWriteByteBuffer);

            final ByteBuffer[] internalBuffers = bufferWrapper.getInternalBuffers();
            for (int i = 0; i < internalBuffers.length; i++) {
                internalBuffers[i].flip();
                this.byteChannelForFile.write(internalBuffers[i]);
            }
            this.totalItemCount++;
            return this.totalItemCount;
        }
    }

    public void closeFile() {

    }

    public void increasePosition() throws IOException {
        synchronized (this.lockObject) {
            this.checkChannels();
            if (this.totalItemCount > this.currentPosition) {
                this.currentPosition++;
                this.writePosition();
            }
        }
    }

    public static void main(final String[] args) {
        try {
            final BinaryFile binaryFile = new BinaryFile("/osman",
                                                         "osman");
            binaryFile.initialize();
            System.out.println(binaryFile.getCurrentPosition());
            final ByteBufferWrapper bufferWrapper = new ByteBufferWrapper();
            bufferWrapper.putString("Osman yaycioglu");
            for (int i = 0; i < 10000; i++) {
                binaryFile.write(bufferWrapper);
            }
            ByteBufferWrapper next = null;
            while ((next = binaryFile.getNext()) != null) {
                next.position(0);
                final String string = next.getString(15);
                System.out.println(string);
                System.out.println(binaryFile.getCurrentPosition() + " - " + binaryFile.getTotalItemCount());
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public void setCurrentPosition(final int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getTotalItemCount() {
        return this.totalItemCount;
    }

}
