import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class LZW {

    private int codeWordLength;
    private Map<ByteArray, Integer> codeTable;
    private List<ByteArray> decodeTable;
    
    public LZW() {
        this.codeWordLength = 12;
    }
    
    public void compress(final InputStream in, final OutputStream out) throws IOException {
        this.init();
        int code = 256;
        final InputStream bufferedIn = new BufferedInputStream(in);
        final Output compressedOutput = new Output(new BufferedOutputStream(out), this.codeWordLength);
        final int firstByte = bufferedIn.read();
        ByteArray w = new ByteArray((byte)firstByte, new byte[0]);
        int K;
        while ((K = bufferedIn.read()) != -1) {
            final ByteArray wK = new ByteArray(w).append((byte)K, new byte[0]);
            if (this.codeTable.containsKey(wK)) {
                w = wK;
            }
            else {
                compressedOutput.write(this.codeTable.get(w));
                if (code < (1 << this.codeWordLength) - 1) {
                    this.codeTable.put(wK, code++);
                }
                w = new ByteArray((byte)K, new byte[0]);
            }
        }
        compressedOutput.write(this.codeTable.get(w));
        compressedOutput.flush();
        compressedOutput.close();
    }
    
    public void decompress(final InputStream in, final OutputStream out) throws IOException {
        this.init();
        final Input compressedInput = new Input(new BufferedInputStream(in), this.codeWordLength);
        final OutputStream bufferedOut = new BufferedOutputStream(out);
        int oldCode = compressedInput.read();
        bufferedOut.write(oldCode);
        int character = oldCode;
        int newCode;
        while ((newCode = compressedInput.read()) != -1) {
            ByteArray string;
            if (newCode >= this.decodeTable.size()) {
                string = new ByteArray(this.decodeTable.get(oldCode));
                string.append((byte)character, new byte[0]);
            }
            else {
                string = this.decodeTable.get(newCode);
            }
            for (int i = 0; i < string.size(); ++i) {
                bufferedOut.write(string.get(i));
            }
            character = string.get(0);
            this.decodeTable.add(new ByteArray(this.decodeTable.get(oldCode)).append((byte)character, new byte[0]));
            oldCode = newCode;
        }
        bufferedOut.flush();
        bufferedOut.close();
    }
    
    private void init() {
        this.codeTable = new HashMap<ByteArray, Integer>();
        this.decodeTable = new ArrayList<ByteArray>();
        for (int i = 0; i < 256; ++i) {
            this.codeTable.put(new ByteArray((byte)i, new byte[0]), i);
            this.decodeTable.add(new ByteArray((byte)i, new byte[0]));
        }
    }
}

