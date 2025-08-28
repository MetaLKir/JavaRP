import java.io.*;

public class DataStreamAppl {
    static final String dataFile = "invoice";
    static final double[] prices = {25.99, 9.99, 15.99, 3.99, 4.99};
    static final int[] units = {10, 12, 7, 5, 16};
    static final String[] items = {"item1", "item2", "item3", "item4", "item5"};

    public static void main(String[] args) {
        // write to file
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {
            for (int i = 0; i < prices.length; i++) {
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(items[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read from file
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
            while (true) {
                double price = in.readDouble();
                int unit = in.readInt();
                String item = in.readUTF();
                System.out.printf("We order %d units of %s at %.2f USD\n", unit, item, price);
            }
        } catch (EOFException e) {
            // this is how we close DataStream (yeah, through EOFException exception)
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
