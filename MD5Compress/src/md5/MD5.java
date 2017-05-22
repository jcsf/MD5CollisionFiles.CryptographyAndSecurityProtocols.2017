package md5;

public class MD5 {

    private static final int[] INIT_HV = {(int)0x67452301, (int)0xEFCDAB89L, (int)0x98BADCFEL, (int)0x10325476};

    private static final int[] r = {
            7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
            5,  9, 14, 20, 5,  9, 14, 20, 5,  9, 14, 20, 5,  9, 14, 20,
            4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
            6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21
    };

    private static final int[] k = new int[64];
    static
    {
        for (int i = 0; i < 64; i++)
            k[i] = (int)(long)(Math.abs(Math.sin(i + 1)) * (1L << 32));
    }

    public static String md5Hash_NoPadding(byte[] data) {
        if(data.length%64 != 0){
            System.err.println("Data length must be 0 mod 64!");
            System.exit(0);
        }

        int[] md5Array = null;


        for(int b = 0; b < data.length; b += 64) {
            md5Array = md5Block(md5Array, convertByteArrayToIntBlock(data, b));
        }

        return toHexString(hVariablesToByteArray(md5Array));
    }

    public static int[] md5Block(int ihv[], int block[]) {

        if(ihv == null) {
            ihv = new int[4];
            ihv[0] = INIT_HV[0];
            ihv[1] = INIT_HV[1];
            ihv[2] = INIT_HV[2];
            ihv[3] = INIT_HV[3];
        }

        int a = ihv[0], b = ihv[1], c = ihv[2], d = ihv[3];


        for (int i = 0; i < 64; i++) {
            int f, g;

            if(i < 16) {
                f = (b & c) | (~b & d);
                g = i;
            } else if (i < 32) {
                f = (d & b) | (~d & c);
                g = (5*i + 1) & 0x0F; // "& 0x0F" MOD 16
            } else if (i < 48) {
                f = b ^ c ^ d;
                g = (3*i + 5) & 0x0F; // "& 0x0F" MOD 16
            } else {
                f = c ^ (b | (~d));
                g = (7*i) & 0x0F; // "& 0x0F" MOD 16
            }

            int temp = d;
            d = c;
            c = b;
            b = b + Integer.rotateLeft(a + f + k[i] + block[g], r[i]);
            a = temp;
        }

        ihv[0] += a;
        ihv[1] += b;
        ihv[2] += c;
        ihv[3] += d;

        return ihv;
    }

    private static byte[] hVariablesToByteArray(int[] ihv) {
        byte[] bArray = new byte[16];

        int count = 0;
        for (int i = 0; i < 4; i++) {
            int n = ihv[i];
            for (int j = 0; j < 4; j++) {
                bArray[count++] = (byte)n;
                n >>>= 8;
            }
        }

        return bArray;
    }

    private static int[] convertByteArrayToIntBlock(byte[] data, int index) {
        int[] block = new int[16];

        for (int j = 0; j < 64; j++, index++) {
            block[j >>> 2] = ((int)(data[index]) << 24) | (block[j >>> 2] >>> 8);
        }

        return block;
    }

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++)
        {
            sb.append(String.format("%02X", b[i] & 0xFF));
        }
        return sb.toString();
    }
}
