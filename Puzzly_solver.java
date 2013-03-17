/**
* Solver for 8/15 puzzle :}
*
* @version   $1.0$
*
* @author    Karina Damico
*
* Revisions:
*       $Log$
*/

import java.util.*;

public class Puzzly_solver {

    private Puzzly_board initial;    //
    private List<Puzzly_board> result = new ArrayList<Puzzly_board>();

    private class ITEM{    // length of the path depends on previous positions - thus memorize
        private ITEM prevBoard;  //remember the previous board positioning
        private Puzzly_board board;   // position itself

        private ITEM(ITEM prevBoard, Puzzly_board board) {
            this.prevBoard = prevBoard;
            this.board = board;
        }

        public Puzzly_board getBoard() {
            return board;
        }


    }

    public Puzzly_solver(Puzzly_board initial) {
        this.initial = initial;

        if(!isSolvable()) return;  //  check if combination is solvable (abt half of combinations are not solvable)

        //  Solution is A* algorithm which is based on usage of priority queue (heaps don't lie)
        PriorityQueue<ITEM> priorityQueue = new PriorityQueue<ITEM>(10, new Comparator<ITEM>() {
            @Override
            public int compare(ITEM o1, ITEM o2) {
                return new Integer(measure(o1)).compareTo(new Integer(measure(o2)));
            }
        });

        priorityQueue.add(new ITEM(null, initial));

        while (true){
            ITEM board = priorityQueue.poll(); 

            //  if solution reached - save all the solutions
            if(board.board.isGoal()) {
                itemToList(new ITEM(board, board.board));
                return;
            }

            Iterator iterator = board.board.neighbors().iterator();
            while (iterator.hasNext()){
                Puzzly_board board1 = (Puzzly_board) iterator.next();
                //optimize this and optimize that
                //check if the position was resolved before (neighboring)
                //if yes - don't repeat a move

                if(board1!= null && !containsInPath(board, board1))
                    priorityQueue.add(new ITEM(board, board1));
            }

        }
    }

    // compute f(x)
    private static int measure(ITEM item){
        ITEM item2 = item;
        int c= 0;   // g(x)
        int measure = item.getBoard().h();  // h(x)
        while (true){
            c++;
            item2 = item2.prevBoard;
            if(item2 == null) {
                // g(x) + h(x)
                return measure + c;
            }
        }
    }

    //  save results
    private void itemToList(ITEM item){
        ITEM item2 = item;
        while (true){
            item2 = item2.prevBoard;
            if(item2 == null) {
                Collections.reverse(result);
                return;
            }
            result.add(item2.board);
        }
    }

    // check if the position was previously on the path 
    private boolean containsInPath(ITEM item, Puzzly_board board){
      ITEM item2 = item;
       while (true){
           if(item2.board.equals(board)) return true;
           item2 = item2.prevBoard;
           if(item2 == null) return false;
       }
    }


    public boolean isSolvable() {
       return true;
    }

    public int moves() { 
        if(!isSolvable()) return -1;
        return result.size() - 1;
    }


    // solution method that will return result only
    public Iterable<Puzzly_board> solution() {
        return result;
    }


}