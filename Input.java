import java.io.IOException;
import java.io.InputStream;

public class Input {
    private InputStream in;
    private int codeWordLength;
    private int mask;
    private long buf;
    private int bufUsageBits;
    private int bufUsageBytes;
    private int bufUsageSymbols;
    private int bufferedCodes;
    private boolean eof;
    
    public static long GCD(long a, long b) {
        while (b != 0L) {
            final long tmp = b;
            b = a % b;
            a = tmp;
        }
        return Math.abs(a);
    }
    
    public static long LCM(final long a, final long b) {
        return Math.abs(a * b / GCD(a, b));
    }
    
    public Input(final InputStream in, final int codeWordLength) {
        this.in = in;
        this.codeWordLength = codeWordLength;
        this.bufferedCodes = 0;
        this.buf = 0L;
        this.bufUsageBits = (int)LCM(8L, codeWordLength);
        this.bufUsageBytes = this.bufUsageBits / 8;
        this.bufUsageSymbols = this.bufUsageBits / codeWordLength;
        this.mask = (1 << codeWordLength) - 1;
    }
    public int read() throws IOException {
        if (this.bufferedCodes <= 0 && !this.eof) {
            this.buf = 0L;
            for (int i = 0; i < this.bufUsageBytes; ++i) {
                int read = this.in.read();
                if (-1 == read) {
                    this.eof = true;
                }
                read &= 0xFF;
                read <<= i * 8;
                this.buf |= read;
            }
            this.bufferedCodes = this.bufUsageSymbols;
        }
        if (this.bufferedCodes <= 0) {
            return -1;
        }
        final int code = (int)(this.buf & (long)this.mask);
        this.buf >>= this.codeWordLength;
        --this.bufferedCodes;
        if (code < (1 << this.codeWordLength) - 1) {
            return code;
        }
        return -1;
    }
    
    public void close() throws IOException {
        this.in.close();
    }

}
