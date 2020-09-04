public class Cell
{   
    //Instance variables. For state true=alive
    private boolean state;
    private boolean nextState;
    private Cell[] neighbours;
    private int numNeig;        //Variable to keep current number of neighbours in array
    
    //Simple constructor for dead cells
    public Cell(){
        state=false;
        neighbours = new Cell[8];
        numNeig=0;
    }
        
    //Constructor for cells with desired state
    public Cell(boolean st){
        state=st;
        neighbours = new Cell[8];
        numNeig=0;
    }
    
    //Method to change state of current cell
    public void diffState(){
        state=!state;
    }
    
    //Simple method to count number of alive neighbours
    private int getNeig(){
        int temp=0;
        for(int i=0; i<numNeig; i++){
            if(neighbours[i].state){
                temp++;
            }
        }
        return temp;
    }
    
    //Rules of game. Changing next state of cell based on current state and number of neighbours
    public void changeState(){
        int neig=getNeig();
        if(state){              //If cell is alive
            if(neig==2||neig==3){
                nextState=true;
            }
            else{
                nextState=false;
            }
        }   //end of alive cell
        else{                   //If cell is dead
            if(neig==3){
                nextState=true;
            }
            else{
                nextState=false;
            }
        }//End of dead cell
        
    }//End of changeState
    
    //Method to change current state of cell to nextState
    public void goNext(){
        state=nextState;
    }
    
    //Method to return state of cell
    public boolean getSt(){
        return state;
    }
    
    //Method to change state of cell
    public void changeSt(boolean newSt){
        state=newSt;
    }
    
    //Method to add neighbour to array
    public void addNeig(Cell bro){
        if(numNeig<8){
            neighbours[numNeig]=bro;
            numNeig++;
        }
    }
}
