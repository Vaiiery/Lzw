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
    
    public ByteArray append(final ByteArray another) {
        final int size = this.size();
        final int anotherSize = another.size();
        final int nuevoSize = size + anotherSize;
        final byte[] nuevoBuff = new byte[nuevoSize];
        for (int i = 0; i < size; ++i) {
            nuevoBuff[i] = this.get(i);
        }
        for (int i = 0; i < anotherSize; ++i) {
            nuevoBuff[i + size] = another.get(i);
        }
        this.interno = nuevoBuff;
        return this;
    }
    
    public ByteArray append(final byte[] array) {
        return this.append(new ByteArray(array));
    }
    
    public ByteArray append(final byte b1, final byte... bytes) {
        return this.append(new ByteArray(b1, bytes));
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final ByteArray other = (ByteArray)obj;
        return Arrays.equals(this.interno, other.interno);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + Arrays.hashCode(this.interno);
        return result;
    }
    
    @Override
    public String toString() {
        return "ByteArray [interno=" + Arrays.toString(this.interno) + "]";
    }

}