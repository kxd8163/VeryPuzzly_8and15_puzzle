/**
* Runner for 8/15 puzzle (no GUI for now) - VeryPuzzly :}
*
* @version   $1.0$
*
* @author    Karina Damico
*
* Revisions:
*       $Log$
*/

import java.io.*;
import java.util.ArrayList;

public class VeryPuzzly{
    public static int[][] getInput(int quant){
        int[] seq = new int[]{1, 2, 3, 0, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 4};
        int dimention = 4;
        try
        {
            BufferedReader buffy = new BufferedReader(new InputStreamReader(System.in));
            StringBuffer input = new StringBuffer();
            String[] data;
            String str;

            if (quant == 8){ 
                System.out.println("Input the combination you want to solve.\nExample:\n1 2 3\n4 0 5\n7 8 6\nwhere 0 defines an empty cell");
                dimention = 3;
                seq = new int[9];
            }
            else {
                System.out.println("Input the combination you want to solve.\nExample:\n1 2 3 0\n5 6 7 8\n9 10 11 12\n12 14 15 4\nwhere 0 defines an empty cell");
                dimention = 4;
                seq = new int[16];
            }
            for (int i = 0; i < dimention; i++)
            {
                str = buffy.readLine();
                data = str.split(" ");
                for (int j = i*dimention, k = 0; j < dimention*i + dimention; j++, k++) 
                    seq[j] = Integer.parseInt(data[k]);
            }
         }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        int[][] blocks = new int[dimention][dimention];
        int count = 0;
        for (int i = 0; i < dimention; i++)
            for (int j = 0; j < dimention; j++)
                blocks[i][j] = seq[count];
                count++;
        return blocks;

    }


	public static void main(String args[]) {

        int[][] blocks;
        if (args.length == 1){
            if (args[0].equals("8")){
                blocks = getInput(8);
                Puzzly_board initial = new Puzzly_board(blocks);
                Puzzly_solver solver = new Puzzly_solver(initial);
                System.out.println("Minimum number of moves to solve given combination is " + solver.moves());
                for (Puzzly_board board : solver.solution())
                    System.out.println(board);
            }
            else if (args[0].equals("15")) {
                blocks = getInput(15);
                Puzzly_board initial = new Puzzly_board(blocks);
                Puzzly_solver solver = new Puzzly_solver(initial);
                System.out.println("Minimum number of moves to solve given combination is " + solver.moves());
                for (Puzzly_board board : solver.solution())
                    System.out.println(board);
            }

            else
                System.out.println("Argument must be 8 or 15");
        }
        else
            Syste.out.println("Usage: [java] VeryPuzzly [8|15]");
    

	}
}
