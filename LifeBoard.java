public class LifeBoard
{
    private static final boolean DEBUG_MOD=false;
    private boolean noNeig;  //Boolean variable, that let us fill neighbours of cells array just one time
    private Cell[][] board;
    
    public LifeBoard(int width, int height){
        if(DEBUG_MOD){
            System.out.println("First constructor working");
        }
        noNeig=true;    //Right after we created LifeBoard object we did not fill the neighbours list
        //Creating board of cell objects
        board = new Cell[width][height];
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                board[i][j]=new Cell();
            }
        }
    }
    
    public LifeBoard(boolean[][] state){
        if(DEBUG_MOD){
            System.out.println("Second constructor working");
        }
        noNeig=true;   //Right after we created LifeBoard object we did not fill the neighbours list
        //Creating board of cell objects AND setting them dead/alive based on corresponding variable of array
        board = new Cell[state.length][state[0].length];        //I know it's not good for cases with not rectangular arrays, but we're not going to have them
        for(int i=0; i<state.length; i++){
            for(int j=0; j<state[i].length; j++){
                board[i][j]=new Cell(state[i][j]);
            }
        }
    }
    
    public void nextGeneration(){
        if(DEBUG_MOD){
            System.out.println("nextGeneration working");
        }
        
        if(noNeig){
            for(int i=0; i<board.length; i++){          //Loop to go through board and fill neighbours array for every cell
                for(int j=0; j<board[i].length; j++){
                    for(int a=-1; a<2; a++){            //Small nested loop to avoid writing coordinates of neighbours manualy
                        for(int b=-1; b<2; b++){
                            if(i+a>0&&i+a<board.length&&j+b>0&&j+b<board[i].length&&(a!=0||b!=0)){ //Defence from adding non-existing cells to an array(on corners and edges)
                                board[i][j].addNeig(board[i+a][j+b]);
                            } //End of if statement
                        }//End of loop with b
                    }//End of loop with a
                }//End of loop with j
            } //End of loop with i
            noNeig=false;   //Changing variable to avoid filling neighbours list more than once
        }
        
        //Calculate next state for every cell on board
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                board[i][j].changeState();                
            }
        }
        
        //Change current state to next
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                board[i][j].goNext();               
            }
        }
        
    }
    
    //Setting state of cells in board array on corresponding value of newState array
    public void setState(boolean[][] newState){
        if(DEBUG_MOD){
            System.out.println("setState working");
        }
        
        for(int i=0; i<newState.length; i++){
            for(int j=0; j<newState[i].length; j++){
                board[i][j].changeSt(newState[i][j]);
            }
        }
    }
    
    //Setting values of currentState array with states of corresponding cells in board array
    public void getState(boolean[][] currentState){
        if(DEBUG_MOD){
            System.out.println("getState working");
        }
        
        for(int i=0; i<currentState.length; i++){
            for(int j=0; j<currentState[i].length; j++){
                currentState[i][j]=board[i][j].getSt();
            }
        }
    }
    
    public void toggleState(int row, int column){
        if(DEBUG_MOD){
            System.out.println("toggleState working");
        }        
        board[row][column].diffState();
    }
}
