import java.io.IOException;
import java.io.OutputStream;


public class Output
{
    private OutputStream out;
    int codeWordLength;
    private int mask;
    private long buff;
    private int written;
    private int buffBits;
    private int buffBytes;
    private int buffSymbols;
    
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
    
    public Output(final OutputStream out, final int codeWordLength) {
        this.out = out;
        this.codeWordLength = codeWordLength;
        this.written = 0;
        this.buff = 0L;
        this.buffBits = (int)LCM(8L, codeWordLength);
        this.buffBytes = this.buffBits / 8;
        this.buffSymbols = this.buffBits / codeWordLength;
        this.mask = (1 << codeWordLength) - 1;
    }
    
    public void write(int code) throws IOException {
        code = (code & this.mask) << this.written * this.codeWordLength;
        this.buff |= code;
        ++this.written;
        if (this.written >= this.buffSymbols) {
            for (int i = 0; i < this.buffBytes; ++i) {
                this.out.write((int)(this.buff & 0xFFL));
                this.buff >>= 8;
            }
            this.written = 0;
            this.buff = 0L;
        }
    }
    
    public void flush() throws IOException {
        while (this.written < this.buffSymbols && this.written != 0) {
            this.write(-1);
        }
        this.out.flush();
    }
    
    public void close() throws IOException {
        this.out.close();
    }
}
