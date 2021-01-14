import `class`.{FileIO, Grid}
import exception.DonneesIncorrectesException

object Main extends App {
  val filename : String = "src/instructions.txt"

  val grid : Grid = FileIO.readFileForInstructions(filename)
  FileIO.writeJsonGrid(grid)


}
