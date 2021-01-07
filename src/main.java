import java.util.BitSet;


class main {

    //Run this to test the algorithm
    public static void main(String[] args) {

        //instance of the class that handles everything
        Hamming hamming = new Hamming();
        //creates a bitset from the string input
        hamming.SetBits("test123");
        System.out.println("Input text in binary : ");
        System.out.println(hamming.bits);
        hamming.ShowBits(0,hamming.bits.length(),hamming.bits);
        System.out.println();

        //applies the Hamming 7/4 algorithm and creates a new bitset with the hamming code
        System.out.println("Hamming Code :");
        hamming.ApplyHamming(4);
        hamming.ShowBits(0,hamming.lastIndexHammingBits,hamming.hammingbits);
        System.out.println();

        //add errors (flip bits) in ~ 5 % of the hamming code
        System.out.println("Hamming Code with errors :");
        hamming.AddError();
        hamming.ShowBits(0,hamming.lastIndexHammingBits,hamming.hammingbits);
        System.out.println();

        //achieve error correction
        System.out.println("Hamming Code error corrected :");
        hamming.ErrorCorrection();
        hamming.ShowBits(0,hamming.lastIndexErrorBits,hamming.errorcorrectedbits);
        System.out.println();




    }

}