package com.soento.core.util;

import com.soento.core.enums.Charset;
import org.springframework.util.FastByteArrayOutputStream;

import java.io.*;

/**
 * @author soento
 */
public class StreamUtil {

    public static void close(Closeable closeable) {
        if (closeable != null) {
            if (closeable instanceof Flushable) {
                try {
                    ((Flushable) closeable).flush();
                } catch (IOException ignored) {
                }
            }

            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }

    public static int copy(InputStream input, OutputStream output) throws IOException {
        final int ioBufferSize = 16384;
        byte[] buffer = new byte[ioBufferSize];
        int count = 0;
        int read;
        while (true) {
            read = input.read(buffer, 0, ioBufferSize);
            if (read == -1) {
                break;
            }
            output.write(buffer, 0, read);
            count += read;
        }
        return count;
    }

    public static int copy(InputStream input, OutputStream output, int byteCount) throws IOException {
        final int ioBufferSize = 16384;
        int bufferSize = (byteCount > ioBufferSize) ? ioBufferSize : byteCount;

        byte[] buffer = new byte[bufferSize];
        int count = 0;
        int read;
        while (byteCount > 0) {
            if (byteCount < bufferSize) {
                read = input.read(buffer, 0, byteCount);
            } else {
                read = input.read(buffer, 0, bufferSize);
            }
            if (read == -1) {
                break;
            }
            byteCount -= read;
            count += read;
            output.write(buffer, 0, read);
        }
        return count;
    }


    public static void copy(InputStream input, Writer output, int byteCount) throws IOException {
        copy(input, output, Charset.UTF_8.value(), byteCount);
    }

    public static void copy(InputStream input, Writer output, String encoding) throws IOException {
        copy(new InputStreamReader(input, encoding), output);
    }

    public static void copy(InputStream input, Writer output, String encoding, int byteCount) throws IOException {
        copy(new InputStreamReader(input, encoding), output, byteCount);
    }

    public static int copy(Reader input, Writer output) throws IOException {
        final int ioBufferSize = 16384;
        char[] buffer = new char[ioBufferSize];
        int count = 0;
        int read;
        while ((read = input.read(buffer, 0, ioBufferSize)) >= 0) {
            output.write(buffer, 0, read);
            count += read;
        }
        output.flush();
        return count;
    }

    public static int copy(Reader input, Writer output, int charCount) throws IOException {
        final int ioBufferSize = 16384;
        int bufferSize = (charCount > ioBufferSize) ? ioBufferSize : charCount;

        char[] buffer = new char[bufferSize];
        int count = 0;
        int read;
        while (charCount > 0) {
            if (charCount < bufferSize) {
                read = input.read(buffer, 0, charCount);
            } else {
                read = input.read(buffer, 0, bufferSize);
            }
            if (read == -1) {
                break;
            }
            charCount -= read;
            count += read;
            output.write(buffer, 0, read);
        }
        return count;
    }


    public static void copy(Reader input, OutputStream output) throws IOException {
        copy(input, output, Charset.UTF_8.value());
    }

    public static void copy(Reader input, OutputStream output, int charCount) throws IOException {
        copy(input, output, Charset.UTF_8.value(), charCount);
    }

    public static void copy(Reader input, OutputStream output, String encoding) throws IOException {
        try (Writer out = new OutputStreamWriter(output, encoding)) {
            copy(input, out);
            out.flush();
        }
    }

    public static void copy(Reader input, OutputStream output, String encoding, int charCount) throws IOException {
        try (Writer out = new OutputStreamWriter(output, encoding)) {
            copy(input, out, charCount);
            out.flush();
        }
    }


    public static byte[] readAvailableBytes(InputStream in) throws IOException {
        int l = in.available();
        byte[] byteArray = new byte[l];
        int i = 0, j;
        while ((i < l) && (j = in.read(byteArray, i, l - i)) >= 0) {
            i += j;
        }
        if (i < l) {
            throw new IOException("Failed to completely read input stream");
        }
        return byteArray;
    }

    public static byte[] readBytes(InputStream input) throws IOException {
        FastByteArrayOutputStream output = new FastByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    public static byte[] readBytes(InputStream input, int byteCount) throws IOException {
        FastByteArrayOutputStream output = new FastByteArrayOutputStream();
        copy(input, output, byteCount);
        return output.toByteArray();
    }

    public static byte[] readBytes(Reader input) throws IOException {
        FastByteArrayOutputStream output = new FastByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    public static byte[] readBytes(Reader input, int byteCount) throws IOException {
        FastByteArrayOutputStream output = new FastByteArrayOutputStream();
        copy(input, output, byteCount);
        return output.toByteArray();
    }

    public static byte[] readBytes(Reader input, String encoding) throws IOException {
        FastByteArrayOutputStream output = new FastByteArrayOutputStream();
        copy(input, output, encoding);
        return output.toByteArray();
    }

    public static byte[] readBytes(Reader input, String encoding, int byteCount) throws IOException {
        FastByteArrayOutputStream output = new FastByteArrayOutputStream();
        copy(input, output, encoding, byteCount);
        return output.toByteArray();
    }

    public static boolean compare(InputStream input1, InputStream input2) throws IOException {
        if (!(input1 instanceof BufferedInputStream)) {
            input1 = new BufferedInputStream(input1);
        }
        if (!(input2 instanceof BufferedInputStream)) {
            input2 = new BufferedInputStream(input2);
        }
        int ch = input1.read();
        while (ch != -1) {
            int ch2 = input2.read();
            if (ch != ch2) {
                return false;
            }
            ch = input1.read();
        }
        int ch2 = input2.read();
        return (ch2 == -1);
    }

    public static boolean compare(Reader input1, Reader input2) throws IOException {
        if (!(input1 instanceof BufferedReader)) {
            input1 = new BufferedReader(input1);
        }
        if (!(input2 instanceof BufferedReader)) {
            input2 = new BufferedReader(input2);
        }

        int ch = input1.read();
        while (ch != -1) {
            int ch2 = input2.read();
            if (ch != ch2) {
                return false;
            }
            ch = input1.read();
        }
        int ch2 = input2.read();
        return (ch2 == -1);
    }

}
