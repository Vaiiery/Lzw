import java.util.Arrays;

public class ByteArray {
    
    private byte[] interno;
    
    public ByteArray() {
        this.interno = new byte[0];
    }
    
    public ByteArray(final ByteArray another) {
        this.interno = another.interno.clone();
    }
    
    public ByteArray(final byte[] array) {
        this.interno = array.clone();
    }
    
    public ByteArray(final byte b1, final byte... bytes) {
        final int bytessize = (bytes != null) ? bytes.length : 0;
        (this.interno = new byte[bytessize + 1])[0] = b1;
        for (int i = 1; i < this.interno.length; ++i) {
            this.interno[i] = bytes[i - 1];
        }
    }
    
    public int size() {
        return this.interno.length;
    }
    
    public byte get(final int index) {
        return this.interno[index];
    }
    
    public void set(final int index, final byte value) {
        this.interno[index] = value;
    }
    
}