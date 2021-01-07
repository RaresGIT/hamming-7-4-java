import java.util.BitSet;
import java.util.Random;

class Hamming{
    public BitSet bits;
    public BitSet hammingbits;
    public BitSet errorcorrectedbits;
    public int lastIndexHammingBits=0;
    public int lastIndexErrorBits=0;

    public BitSet getBits() {
        return bits;
    }

    public BitSet getHammingbits() {
        return hammingbits;
    }

    public void setHammingbits(BitSet hammingbits) {
        this.hammingbits = hammingbits;
    }

    //initialize
    public Hamming() {
        bits = new BitSet();
        hammingbits = new BitSet();
        errorcorrectedbits = new BitSet();
    }

    public BitSet SetBits(String text)
    {
        bits = BitSet.valueOf(text.getBytes()); //O(n)
        return BitSet.valueOf(text.getBytes());
    }//O(n)

    public void ApplyHamming(int hammingtype)
    {
        for(int i=0;i< bits.length();i=i+hammingtype) //O(n)
        {
            if(i+hammingtype < bits.length())
            hamming(bits.get(i,i+hammingtype)); //O(1) since it's a hashmap
            else
                hamming(bits.get(i,i + bits.length()-i)); //O(1) since it's a hashmap


        }

    }//O(n)


    //util function
    public String ShowBits(int startindex,int stopindex,BitSet bitset)
    {
        StringBuilder s = new StringBuilder();
        for(int i=startindex;i<stopindex;i++) {
            s.append(bitset.get(i) == true ? 1:0);
        }
        System.out.println(s);

        return s.toString();
    }


    //used in apply hamming, so it is unit tested with it
    public void hamming(BitSet group)
    {   BitSet appliedhamming = new BitSet(7);

        int bit1=0,bit2=0,bit3=0,bit4=0;

        if(group.get(0)) {
            appliedhamming.set(2);
            bit1=1;
        }

        if(group.get(1)) {
            appliedhamming.set(4);
            bit2 = 1;
        }

        if(group.get(2)) {
            appliedhamming.set(5);
            bit3 = 1;
        }

        if(group.get(3)) {
            appliedhamming.set(6);
            bit4 = 1;
        }

        //calculate parity bits
        if((bit1 ^ bit2 ^ bit4) == 1)
            appliedhamming.set(0);

        if((bit1^bit2^bit3) ==1)
            appliedhamming.set(1);

        if((bit2^bit3^bit4)==1)
            appliedhamming.set(3);

        //add hammingcodes to a new bitset
        for(int i=0;i<7;i++)
        {
            if(appliedhamming.get(i))
                hammingbits.set(lastIndexHammingBits + i); //O(1)

        }
        lastIndexHammingBits += 7;
        //ShowBits(0,6,appliedhamming);


    }


    public BitSet AddError()
    {
        //calculate how many errors 5% is
        int errorcount = hammingbits.length() * 5/100;

        //add at least one error
        if(errorcount == 0)
            errorcount++;

        //generate errors
        Random rand = new Random();
        int upperbound = hammingbits.length();

        //logs the errors added
        StringBuilder s = new StringBuilder();

        while(errorcount > 0) //O(n)
        {
            int index = rand.nextInt(upperbound);
            s.append(index + " ");
            hammingbits.flip(index); //O(1)

            errorcount--;
        }
        System.out.println("Errors added at these indexes: " + s);

        //returns the bitset with added errors
        return hammingbits;
    }//O(n)

    public void ErrorCorrection()
    {
        BitSet checkerrors = new BitSet(7);

        for(int i=0;i<hammingbits.length();i=i+7) //O(n)
        {
            checkerrors = hammingbits.get(i,i+7); //O(1)


            int[] bits = {0,0,0,0,0,0,0};
            int parity1=0,parity2=0,parity3=0;
            for(int j=0;j<7;j++)
                if(checkerrors.get(j))
                    bits[j]=1;

            if((bits[3] ^ bits[4] ^ bits[5] ^ bits[6]) == 1)
                parity3=1;

            if((bits[1] ^ bits[2] ^ bits[5] ^ bits[6]) == 1)
                parity2=1;

            if((bits[0] ^ bits[2] ^ bits[4] ^ bits[6]) ==1)
                parity1=1;


            double errorindex = parity1 + parity2 * Math.pow(2,2) + parity3 * Math.pow(2,3);
            if(errorindex > 0) {
                checkerrors.flip((int) errorindex);

            }

            for(int k=0;k<7;k++)
            {
                if(checkerrors.get(k))
                    errorcorrectedbits.set(lastIndexErrorBits + k);

            }
            lastIndexErrorBits += 7;
        }




    }//O(n)



}