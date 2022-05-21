import processing.core.PApplet;

/**
 * This program creates a 10 x 10 grid using a 2D array and responds to user mouse input by changing the color of the cells when clicked
 * @author T. Guo
 */

public class Sketch extends PApplet {
	
  // Global variable delcaration and initialization
  public int CELL_WIDTH = 20;
  public int CELL_HEIGHT = 20;
  public int MARGIN = 5;

  public int ROW_COUNT = 10;
  public int COLUMN_COUNT = 10;

  public int SCREEN_WIDTH = CELL_WIDTH * COLUMN_COUNT + MARGIN * (ROW_COUNT + 1);
  public int SCREEN_HEIGHT = CELL_HEIGHT * ROW_COUNT + MARGIN * (ROW_COUNT + 1);

  // A boolean array made more sense than integers since there are only 2 states
  public boolean [][] isOn = new boolean[ROW_COUNT][COLUMN_COUNT];

  // Variables used in the mousePressed method (explained below)
  int column;
  int row;
  int intTotalCounter;
  int intTempCounter;
  int intConsecCounter;
  int intMaxConsecutive;


  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    size(SCREEN_WIDTH, SCREEN_HEIGHT);
  }


  /** 
   * Called once at the beginning of execution.
   */
  public void setup() {
    background(0);
  }


  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // Draws white and green cells according to isOn[][]
	  for (int c = 0; c < COLUMN_COUNT; c++){
      for (int r = 0; r < ROW_COUNT; r++){
        if (isOn[c][r]){
          fill(0, 255, 0);
        }
        else {
          fill(255);
        }
        rect(c * 20 + MARGIN * (c + 1), r * 20 + MARGIN * (r + 1), CELL_WIDTH, CELL_HEIGHT);
      }
    }
  }


    /**
   * Changes the states of cells and outputs data to the terminal when the mouse is pressed
   */
  public void mousePressed(){
    // Determines the row and column clicked
    column = mouseX / (CELL_WIDTH + MARGIN);
    row = mouseY / (CELL_HEIGHT + MARGIN);

    // Counter for the total number of cells "on"
    intTotalCounter = 0;

    // Temporary counter used to count the number of selected cells in each row and column
    intTempCounter = 0;

    // Counter to count consecutive lit cells in a row or column (resets when a cell that is off is reached)
    intConsecCounter = 0;
    // Integer variable to store the length of the maximum chain of consecutive lit cells in a row or column
    intMaxConsecutive = 0;


    // Switches the state of the clicked cell and all adjacent cells
    isOn[column][row] = !isOn[column][row];
    
    if (column != 0){
      isOn[column - 1][row] = !isOn[column - 1][row];
    }
    if (column != COLUMN_COUNT - 1){
      isOn[column + 1][row] = !isOn[column + 1][row];
    }
    if (row != 0){
      isOn[column][row - 1] = !isOn[column][row - 1];
    }
    if (row != ROW_COUNT - 1){
      isOn[column][row + 1] = !isOn[column][row + 1];
    }


    // Counts the total number of lit cells using intTotalCounter
    for(int c = 0; c < COLUMN_COUNT; c++){
      for(int r = 0; r < ROW_COUNT; r++){
        if (isOn[c][r]){
          intTotalCounter++;
        }
      }
    }

    // Outputs the mouse location and the total number of selected cells
    System.out.println("mouse coordinates: (" + mouseX + ", " + mouseY + "); grid coordinates: (row:" + row + ", " + "column: " + column + ")");
    System.out.println("Total of " + intTotalCounter + " cells are selected.");




    /**
     * Loops through each row of the grid, updating intTempCounter for every lit cell and then outputting the result.
     * Also updates intConsecCounter and stores this value in intMaxConsecutive.
     * intConsecCounter is reset to 0 upon reaching a cell that is off, but intMaxConsecutive holds on to its value unless a bigger chain is found.
     * Outputs intMaxConsecutive if a chain longer than length 2 was found in that row.
     */
    for (int r = 0; r < ROW_COUNT; r++){
      for (int c = 0; c < COLUMN_COUNT; c++){
        if (isOn[c][r]){
          intTempCounter++;
          intConsecCounter++;
          if (intConsecCounter > intMaxConsecutive){
            intMaxConsecutive = intConsecCounter;
          }
        }
        else {
          intConsecCounter = 0;
        }
      }
      if (intMaxConsecutive > 2){
        System.out.println("There are " + intMaxConsecutive + " continous blocks selected on row " + r);
      }
      intConsecCounter = 0;
      intMaxConsecutive = 0;
      System.out.println("Row " + r + " has " + intTempCounter + " cells selected.");
      intTempCounter = 0;
    }



    // Same as above, but row and column is switched
    for(int c = 0; c < COLUMN_COUNT; c++){
      for(int r = 0; r < ROW_COUNT; r++){
        if (isOn[c][r]){
          intTempCounter++;
          intConsecCounter++;
          if (intConsecCounter > intMaxConsecutive){
            intMaxConsecutive = intConsecCounter;
          }
        }
        else{
          intConsecCounter = 0;
        }
      }
      if(intMaxConsecutive > 2){
        System.out.println("There are " + intMaxConsecutive + " continous blocks selected on column " + c);
      }
      intConsecCounter = 0;
      intMaxConsecutive = 0;
      System.out.println("Column " + c + " has " + intTempCounter + " cells selected.");
      intTempCounter = 0;
     
    }

  }
}
