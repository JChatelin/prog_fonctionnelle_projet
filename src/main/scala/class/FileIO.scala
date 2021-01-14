package `class`

import java.io.{BufferedWriter, File, FileNotFoundException, FileWriter, IOException}

import exception.DonneesIncorrectesException

import scala.io.Source

object FileIO {

  @throws(classOf[FileNotFoundException])
  @throws(classOf[IOException])
  def read(filename : String) : List[String] = {
    try {
      val bufferedSource = Source.fromFile(filename)
      val lines = bufferedSource.getLines.toList
      bufferedSource.close
      lines
    } catch {
      case e: FileNotFoundException => throw new FileNotFoundException("Couldn't find that file. \n"+e)
      case e: IOException => throw new IOException("Couldn't find that file. \n"+e)
    }
  }

  def readFileForInstructions(filename : String) : Grid = {
    var lines : List[String] = read(filename)
    if(lines.size%2!=1){
      throw new DonneesIncorrectesException("The file is not written correctly !")
    }else {
      try {
        val firstLine : String = lines.head
        var lineSplit: Array[String] = firstLine.split(" ")
        val grid : Grid = new Grid(lineSplit(0).toInt, lineSplit(1).toInt)
        lines = lines.slice(1, lines.size)
        for((line, index) <- lines.zipWithIndex){
          if(index%2==0){
            lineSplit = line.split(" ")
            grid.createLawnMower(lineSplit(0).toInt, lineSplit(1).toInt, lineSplit(2).toCharArray()(0))
          } else {
            grid.listOfInstruction(line, index/2)
          }
        }
        grid
      } catch {
        case e : NumberFormatException => throw new NumberFormatException("Expected a number but didn't find one! "+e)
      }
    }
  }

  def writeJsonGrid(grid: Grid) = {
    try {
      val fileWriter = new FileWriter(new File("output.json"))
      val bw = new BufferedWriter(fileWriter)
      bw.write("{\n"+grid.toString+"\n}")
      bw.close()
    } catch {
      case e: IOException => throw new IOException("Error while trying to write in the file. \n"+e)
    }
  }

}
