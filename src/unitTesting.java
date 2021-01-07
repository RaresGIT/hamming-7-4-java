import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class unitTesting {


    public String ReadFromFile()
    {   String data = "";
        try {
            File myObj = new File("testinginput.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                data =  data + myReader.nextLine();

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

        return data;
    }

    @Test
    public void SetBitsTesting()
    {
        Hamming tester = new Hamming();
        tester.SetBits("test123");
        //check if output in binary checks out with the output from my logic
        assertEquals("001011101010011011001110001011101000110001001100110011",tester.ShowBits(0,tester.bits.length(),tester.bits));
    }

    @Test
    public void ApplyHamingTesting()
    {
        Hamming tester = new Hamming();
        tester.SetBits("test123");
        tester.ApplyHamming(4);
        assertEquals("01010100110110101101010001100011100011011001010100110110111000000111001101100001110000111000011100"
                ,tester.ShowBits(0,tester.lastIndexHammingBits,tester.hammingbits));
    }


    @Test
    public void AddErrorTesting()
    {
        Hamming tester = new Hamming();
        tester.SetBits("test123");
        tester.ApplyHamming(4);
        assertEquals(tester.hammingbits,tester.AddError());

    }

    @Test
    public void ErrorCorrectionTesting()
    {
        Hamming tester = new Hamming();
        tester.SetBits("test123");
        tester.ApplyHamming(4);
        tester.AddError();
        tester.ErrorCorrection();

        assertNotEquals(tester.hammingbits,
                tester.ShowBits(0,tester.lastIndexErrorBits,tester.errorcorrectedbits));
    }

    @Test
    public void PerfomanceSetBits()
    {
        long startTime = System.currentTimeMillis();
        Hamming tester = new Hamming();
        tester.SetBits("test123");
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
    }

    @Test
    public void PerfomanceHamming()
    {
        long startTime = System.currentTimeMillis();
        Hamming tester = new Hamming();
        tester.SetBits(ReadFromFile());
        System.out.println(ReadFromFile());
        tester.ApplyHamming(4);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
    }

    @Test
    public void PerfomanceAddErrors()
    {
        long startTime = System.currentTimeMillis();
        AddErrorTesting();
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
    }

    @Test
    public void PerfomanceErrorCorrection()
    {
        long startTime = System.currentTimeMillis();
        Hamming tester = new Hamming();
        tester.SetBits(ReadFromFile());
        tester.ApplyHamming(4);
        tester.AddError();
        tester.ErrorCorrection();
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
    }
}
